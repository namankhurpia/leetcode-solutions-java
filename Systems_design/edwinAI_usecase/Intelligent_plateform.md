# System Design: Agentic AIOps Incident Intelligence Platform (Edwin AI style)

A full system design walkthrough, structured the way you would present it in a 45 to 60 minute system design interview.

---

## 1. Problem Statement: Understanding the Problem

Enterprise IT environments are hybrid (on-prem, multi-cloud, SaaS) and instrumented by dozens of monitoring tools. Each tool fires alerts independently. A single failure, say a storage array degrading, cascades into hundreds of alerts across the network layer, the VM layer, the database layer, and the application layer.

The pain, stated precisely:

1. **Alert noise**: A mid-size enterprise sees 50k to 1M+ raw monitoring events per day. 90 to 95 percent are duplicates, flapping signals, or downstream symptoms of one root issue.
2. **Slow triage**: Humans manually decide which alerts are related, which team owns them, and what the root cause is. This dominates MTTR (mean time to resolve).
3. **Fragmented context**: The data needed to diagnose an incident lives in five places: metrics/logs (observability), asset relationships (CMDB), dependency maps (topology), tickets (ITSM), and change records (deploy history). No human holds all of it in their head at 3 AM.
4. **Remediation is tribal knowledge**: Fixes live in runbooks, wikis, and senior engineers' heads. Junior on-call engineers escalate instead of resolving.

**Goal**: Build a platform that ingests events from any tool, compresses them into a small number of high-quality incidents ("insights"), explains the root cause in plain language, and recommends or executes remediation within governance boundaries, while keeping a human in the loop.

**Explicitly out of scope** (state this in the interview): building the monitoring agents/collectors themselves, and fully autonomous remediation without approval gates (v1 is human-in-the-loop).

---

## 2. Functional Requirements: Core Requirements

**P0 (must have)**

- **FR1 Ingest**: Accept events from 3rd-party tools (observability, APM, security, CMDB, ITSM) via connectors, webhooks, and REST APIs. Normalize everything to a Common Event Format (CEF).
- **FR2 Deduplicate**: Collapse repeat/flapping events into a single alert with an occurrence count and a time window.
- **FR3 Correlate**: Group related alerts across domains into one "insight" (incident) using ML correlation models, with adaptive re-clustering when a better grouping is found later.
- **FR4 Enrich**: Attach CMDB attributes, topology neighbors, ownership, and related change/deploy records to every insight.
- **FR5 Root cause analysis**: Rank probable root causes using logs, metrics, topology position, recent deployments, and historically similar incidents. Output must be explainable (evidence cited).
- **FR6 Summarize**: Generate a plain-language incident summary (what broke, what is affected, severity, suggested next step).
- **FR7 Conversational agent**: Let engineers query an insight in natural language ("what changed in the last hour on this service?").
- **FR8 Remediation**: Retrieve or generate the relevant playbook, recommend it with confidence, and execute it through an automation engine gated by approvals, RBAC, and audit logging.
- **FR9 ITSM sync**: Bi-directional integration with ServiceNow-style systems: auto-create tickets from insights, keep status in sync both ways.
- **FR10 Feedback loop**: Capture engineer feedback (correct/incorrect grouping, RCA accepted/rejected) to retrain correlation models and improve retrieval.

**P1 (nice to have)**

- Predictive/anomaly insights before hard failures.
- Custom user-defined correlation models per tenant.
- Auto-routing of incidents to owning teams.

---

## 3. Non-Functional Requirements

- **Scalability**: Handle bursty alert storms. A major outage can spike event volume 50 to 100x within a minute. The pipeline must absorb this without dropping events.
- **Latency**:
  - Event ingestion to alert visibility: < 5 seconds (near real time).
  - Alert to correlated insight: < 30 to 60 seconds (correlation needs a small time window to gather related signals).
  - LLM summary/RCA: < 10 to 15 seconds after insight creation (async, non-blocking).
- **Availability**: 99.9 percent+ for the ingestion path. Ironically, this system must be more reliable than the systems it monitors. Degradation mode: if the AI layer is down, raw correlation and alerting must still function.
- **Multi-tenancy**: Hard isolation of tenant data (each customer's telemetry, CMDB, and incidents). Per-tenant correlation models.
- **Explainability and auditability**: Every AI output must cite its evidence. Every automated action must be logged immutably (who/what approved, what ran, what changed).
- **Security and governance**: RBAC on every surface. Remediation actions run under scoped credentials with least privilege. Approval workflows are policy-driven, not hardcoded.
- **Extensibility**: Adding a new tool integration should be config plus mapping, not new code (connector framework with payload mapping to CEF).
- **Cost control**: LLM calls are expensive. The design must bound LLM spend by only invoking generation post-compression (on insights, never on raw events).
- **Durability**: No event loss. At-least-once delivery with idempotent processing.

---

## 4. Scale Estimations

Back-of-envelope, stated with assumptions:

**Assumptions**
- 500 enterprise tenants.
- Average tenant: 100k raw events/day. Large tenant: 1M+/day.
- Total: ~50M events/day.

**Throughput**
- 50M / 86,400 sec ≈ **~600 events/sec average**.
- Alert storms: plan for **50 to 100x burst = 30k to 60k events/sec peak**. This alone justifies a durable message queue (Kafka) as the ingestion backbone.

**Compression funnel** (this is the key insight of the whole system)
- 50M raw events/day
- → deduplication (~90 percent reduction) → ~5M alerts/day
- → correlation/grouping (~95 percent total compression from raw) → **~100k to 250k insights/day** across all tenants
- LLM generation only happens at the insight level: ~100k to 250k LLM-involved requests/day ≈ 1 to 3 requests/sec average. Completely tractable and cost-bounded.

**Storage**
- ~1 KB per normalized event → ~50 GB/day raw event data → ~18 TB/year. Hot store 30 days, then tiered to object storage.
- Insights, entities, and graph data are tiny by comparison (GBs, not TBs).

**Knowledge graph size**
- Large tenant: 50k CIs (configuration items), ~5 to 10 edges each → ~500k edges per tenant. Millions of nodes/edges platform-wide. Fits comfortably in a graph store or even a well-indexed relational model per tenant.

---

## 5. User Interactions

**Personas**
1. **NOC / on-call engineer (primary)**: Lives in the insight queue. Opens an insight, reads the AI summary, asks the agent follow-up questions, approves or runs the recommended fix.
2. **SRE / senior engineer**: Tunes correlation models, writes/approves playbooks, reviews RCA quality, investigates the incidents the AI could not resolve.
3. **IT manager**: Dashboards. MTTR trends, noise reduction, ticket volume, automation adoption.
4. **Platform admin**: Manages connectors, RBAC, approval policies, tenant configuration.

**Primary flow (happy path)**
1. Storage array degrades → 800 raw events fire across four tools.
2. Engineer sees **one insight** appear: "Checkout service degraded, probable cause: storage latency on host-42. 14 affected services." A ServiceNow ticket already exists, routed to the storage team.
3. Engineer opens the insight, asks the agent: "any deploys in the last 2 hours?" Agent answers with cited evidence.
4. Agent recommends playbook "Failover storage path (last used 12 days ago, 96 percent success)". Engineer clicks approve. Automation runs, verifies health, closes the loop, updates the ticket.
5. Engineer thumbs-up the RCA. That feedback flows into the eval/retraining pipeline.

---

## 6. The Setup: Defining Core Entities

- **Event**: Raw normalized signal. `{event_id, tenant_id, source_tool, ci_ref, severity, type, payload, fingerprint, timestamp}`.
- **Alert**: Deduplicated event stream. `{alert_id, fingerprint, first_seen, last_seen, count, status, ci_id, enrichment}`.
- **Insight (Incident)**: The unit engineers work with. `{insight_id, alert_ids[], priority, status, summary, probable_root_cause, affected_cis[], owning_team, itsm_ticket_ref, correlation_model_id, confidence}`.
- **CI (Configuration Item)**: A node in the environment. `{ci_id, type, attributes, owner, criticality}`. Sourced from CMDB.
- **TopologyEdge**: `{src_ci, dst_ci, relation}` (runs_on, depends_on, connects_to). CIs + edges form the **knowledge graph**.
- **ChangeRecord**: Deploys/config changes. `{change_id, ci_ids[], timestamp, description}`. Critical RCA signal.
- **CorrelationModel**: Per-tenant clustering config. `{model_id, features[], time_window, similarity_thresholds, custom_rules}`.
- **Playbook**: A remediation unit. `{playbook_id, trigger_conditions, steps[], required_approvals, blast_radius_class, success_stats}`.
- **RemediationAction**: An execution instance. `{action_id, insight_id, playbook_id, approver, status, execution_log, rollback_ref}`.
- **Feedback**: `{feedback_id, insight_id, aspect (grouping|rca|summary|playbook), verdict, free_text}`.

---

## 7. API / System Interface

**Ingestion (machine-facing)**
- `POST /v1/events` — bulk event ingestion (webhook target for connectors). Auth: per-connector API key. Returns 202 immediately (async processing).
- `POST /v1/connectors` / `PUT /v1/connectors/{id}/mapping` — register integration, define payload-to-CEF field mapping.

**Insights (human/UI-facing)**
- `GET /v1/insights?status=open&priority=P1` — the queue.
- `GET /v1/insights/{id}` — full insight: alerts, enrichment, RCA, evidence, recommendations.
- `POST /v1/insights/{id}/feedback` — grouping/RCA verdicts.

**Agent**
- `POST /v1/insights/{id}/chat` — `{message, conversation_id}` → streamed response with citations. The agent has tool access scoped to that insight's tenant and CIs.

**Remediation**
- `GET /v1/insights/{id}/recommendations` — ranked playbooks with confidence.
- `POST /v1/actions` — request execution `{insight_id, playbook_id, params}`. Creates a pending action.
- `POST /v1/actions/{id}/approve` — RBAC-gated approval; execution starts only when the approval policy is satisfied.
- `GET /v1/actions/{id}` — status, logs, rollback handle.

**ITSM sync**
- Outbound: platform calls ServiceNow API on insight creation/update.
- Inbound: `POST /v1/itsm/webhook` — ticket state changes flow back and update the insight.

Design notes worth saying out loud: ingestion is async and returns 202 (never block the sender), everything else is standard REST + RBAC, agent responses are streamed (SSE), and all writes are idempotent via client-supplied idempotency keys.

---

## 8. High-Level Design: Step by Step

```
 Tools (obs/APM/security/CMDB/ITSM)
        │  webhooks / connectors
        ▼
 [1] Ingestion Gateway ──► Kafka (raw events, partitioned by tenant)
        ▼
 [2] Normalizer (CEF mapping, validation)
        ▼
 [3] Dedup Service (fingerprint, count, flap suppression)
        ▼
 [4] Correlation Engine (streaming clustering, adaptive re-cluster)
        ▼
 [5] Enrichment Service ◄── Knowledge Graph (CMDB + topology + changes)
        ▼
 [6] Insight Store (system of record)
        ├──► [7] AI Layer (RCA agent + summarizer, on Bedrock-style gateway)
        ├──► [8] ITSM Sync Service (bi-directional)
        └──► [9] Automation Engine (playbooks, approvals, execution, audit)
                        ▲
 [10] Feedback + Eval Pipeline ◄── all human verdicts and outcomes
```

Step-by-step responsibilities:

1. **Ingestion Gateway**: Stateless, horizontally scaled. Authenticates connectors, rate-limits per tenant, writes to Kafka, returns 202. Kafka absorbs alert storms; consumers scale independently.
2. **Normalizer**: Applies per-connector payload mapping to CEF. Invalid events go to a DLQ with observability on mapping failures.
3. **Dedup**: Computes a fingerprint (hash of stable fields: CI + event type + key attributes). Repeats increment a counter on the existing alert instead of creating new ones. Flap detection suppresses oscillating signals.
4. **Correlation Engine**: The heart. Streaming clustering over a sliding time window using features: topological distance between CIs, temporal proximity, semantic similarity of alert text (embeddings), and tenant-defined rules. Emits insights; can re-cluster when a better grouping appears (with churn controls, see LLD).
5. **Enrichment**: Joins the insight against the knowledge graph: CI attributes, dependency neighbors, owning team, related change records in the incident window.
6. **Insight Store**: Postgres (or similar) as the transactional system of record; search index (OpenSearch) for the queue UI; events themselves stay in a columnar/TSDB store.
7. **AI Layer**: Consumes new-insight events. Runs a single batched LLM call per insight for summary + ranked RCA hypotheses + recommended next actions, grounded in retrieved evidence (RAG over logs/metrics/changes/similar past incidents). Also backs the conversational agent with scoped tools.
8. **ITSM Sync**: Creates/updates tickets, ingests inbound status changes. Retry with backoff, idempotent by insight_id.
9. **Automation Engine**: Playbook registry, policy-driven approval workflow, executor with scoped credentials, immutable audit log, health verification post-run, rollback hooks.
10. **Feedback/Eval Pipeline**: Every human verdict and every action outcome becomes labeled data: correlation model tuning, RCA eval sets, playbook success statistics.

---

## 9. Low-Level Design

Pick two or three of these to go deep on in the interview; here are the ones that matter most.

### 9.1 Dedup fingerprinting
`fingerprint = hash(tenant_id, ci_id, event_class, normalize(key_fields))`. Store `fingerprint → alert_id` in Redis with TTL equal to the dedup window (e.g., 30 min sliding). On hit: `INCR count`, update `last_seen`. On miss: create alert. Idempotency: event_id set membership prevents double-processing on Kafka redelivery. Flap suppression: if an alert clears and refires N times within window, mark `flapping=true` and hold it out of correlation until stable.

### 9.2 Streaming correlation and adaptive re-clustering
- Maintain per-tenant "open clusters" in memory (backed by a state store, e.g., Flink state or Redis).
- For each new alert, compute affinity to each open cluster:
  `score = w1 * temporal_proximity + w2 * (1 / topo_distance) + w3 * embedding_similarity(alert_text) + w4 * rule_matches`
  Topological distance comes from a bounded BFS (k ≤ 3 hops) over the knowledge graph.
- score > threshold → join best cluster; else start a new cluster. Cluster closes after quiet period T.
- **Adaptive re-clustering**: periodically re-evaluate open clusters; if merging two clusters improves intra-cluster affinity beyond a margin, merge them. Churn control: never split a cluster that already has an ITSM ticket; merges update the surviving ticket and close the other with a link. Re-clustering is capped (e.g., max 2 regroupings per insight) to avoid confusing engineers.

### 9.3 Knowledge graph
- Nodes: CIs. Edges: typed relations with timestamps. Ingested from CMDB sync plus discovered topology.
- Storage: per-tenant adjacency in a graph DB or Postgres adjacency tables with recursive CTEs; k-hop queries are bounded so either works. Cache hot neighborhoods (CIs currently involved in insights) in Redis.
- Change records are attached to CI nodes so "what changed near this failure in the last 2 hours" is a single graph query.

### 9.4 The single batched LLM call (grounding pattern)
Per new insight, one structured call:
- **Context assembly (deterministic, pre-LLM)**: top-N alerts by severity, CI attributes, k-hop topology summary, change records in window, top-3 similar past incidents (vector search over historical insight embeddings), relevant log excerpts (retrieved, truncated by token budget).
- **Prompt contract**: model must return strict JSON: `{summary, root_cause_hypotheses: [{cause, confidence, evidence_refs[]}], affected_scope, recommended_playbooks[]}`. Every hypothesis must cite evidence_refs pointing to real retrieved artifacts; hypotheses with no citations are dropped by the post-validator.
- **Post-validation**: JSON schema check, citation existence check, confidence calibration clamp, PII/secret scrubbing. Failures fall back to a template-based non-LLM summary so the pipeline never blocks on the model.
- Why one batched call: latency (one round trip), cost (bounded at ~1 to 3 calls/sec platform-wide), and consistency (the summary and RCA can't contradict each other).

### 9.5 Automation guardrails
- Playbooks carry a `blast_radius_class` (read-only / single-CI / service-level / environment-level). Approval policy is a function of class + environment + confidence: read-only diagnostics can auto-run; service-level fixes need one human approval; environment-level needs two.
- Executor runs with per-tenant scoped credentials, every step idempotent, dry-run mode mandatory for new playbooks, post-execution health verification against the same metrics that triggered the insight, and automatic rollback hook if verification fails.
- Immutable audit log (append-only) of recommendation → approval → execution → verification.

### 9.6 Eval pipeline
- **Correlation quality**: sampled insights labeled by engineers (grouping correct?) → precision/recall of clustering; feedback tunes per-tenant weights w1..w4.
- **RCA quality**: LLM-as-judge over (insight evidence, model hypothesis, engineer's final resolution) plus human verdicts; track top-1/top-3 root-cause hit rate.
- **Regression gates**: golden set of historical incidents replayed on every model/prompt change; a prompt update ships only if it does not regress hit rate or citation validity.

---

## 10. The Five Grilling Questions (answered at Senior Staff level)

### Q1. "How do you stop the LLM from hallucinating a root cause and an engineer acting on it at 3 AM?"

I treat this as a systems problem, not a prompting problem. Three layers. First, the LLM never free-generates over the whole environment; context assembly is deterministic code that retrieves real artifacts (alerts, changes, topology, similar incidents), and the model's job is narrowed to ranking and explaining hypotheses over that evidence. Second, the output contract requires every hypothesis to cite evidence_refs that resolve to actual retrieved artifacts; a post-validator drops any uncited hypothesis before a human ever sees it. Third, the UI presents root cause as ranked hypotheses with confidence and clickable evidence, never as a verdict, and the action layer is gated separately: acting on an RCA requires a playbook whose approval policy scales with blast radius. So even a wrong hypothesis costs an engineer a few minutes of reading, not an outage. And we measure it: the eval pipeline tracks top-1/top-3 root cause hit rate against engineers' final resolutions, and citation-validity rate is a shipped SLO for the AI layer. If hit rate degrades after a model or prompt change, the regression gate blocks the rollout.

### Q2. "Your correlation engine regroups alerts after a ticket is already open. How do you re-cluster without whiplashing the humans and the ITSM system?"

The tension is accuracy versus stability, and I resolve it by making stability a first-class constraint rather than an afterthought. Mechanically: clusters are mutable only while "open," merges are allowed but splits of ticketed insights are not, because a split invalidates work a human has already claimed. On merge, the surviving insight is the one with the ticket (or the older ticket), the other is closed with a cross-link, and the ITSM sync posts a single explanatory work note rather than a flurry of updates. I cap regroupings per insight (say two) and apply hysteresis: a merge only happens if the affinity improvement exceeds a margin, not on marginal score changes. There is also a time dimension: most bad groupings surface within the first few minutes as more alerts arrive, so I bias re-clustering aggressiveness to the insight's first N minutes and freeze afterwards. Finally I would track a "grouping churn" metric per tenant as a product health signal; if churn is high, that is a correlation model tuning problem, and I would rather ship slightly under-merged clusters (two tickets for one incident) than over-merged ones (one ticket hiding two incidents), because the failure modes are asymmetric: under-merge wastes some effort, over-merge hides an outage.

### Q3. "Walk me through what happens during a 100x alert storm. Where does this system break first, and what did you design for it?"

The storm is the design case, not the edge case, because the system's value is highest exactly when everything is on fire. The ingestion gateway is stateless and returns 202 after writing to Kafka, so the front door does not fall over; Kafka is the shock absorber and is provisioned for 50 to 100x average with per-tenant partitioning so one tenant's storm cannot starve others (that is also my noisy-neighbor answer). The first thing to saturate is the correlation engine, because its work is superlinear-ish: more alerts means more affinity computations and more graph lookups. Mitigations: dedup runs before correlation and storms are extremely duplicate-heavy, so the compression funnel does most of the work; graph neighborhoods for hot CIs are cached; and under backpressure the engine degrades gracefully by widening batching windows and falling back to cheaper features (temporal + rules, skipping embeddings) rather than dropping events. The AI layer is asynchronous and rate-limited by design, so at worst summaries lag by minutes while alerts and insights stay real time; if the LLM gateway is down entirely, template summaries take over. Consumer lag, per-stage p99, and DLQ depth are the operational SLIs, and the explicit degradation ladder (skip embeddings → widen windows → defer enrichment → defer LLM) is documented and tested with replayed historical storms.

### Q4. "How do you evaluate this system when there is no ground truth for 'correct incident grouping' or 'correct root cause'?"

I build the ground truth as a byproduct of normal operation, and I am honest that it is proxy truth. Three sources. First, explicit feedback: lightweight verdicts in the UI (grouping correct? RCA accepted?) which are sparse but high quality. Second, implicit outcomes: what the engineer actually did resolves ambiguity, so if they manually merged two insights, that is a correlation false-negative label; if the resolution notes name a different root cause than our top hypothesis, that is an RCA miss; if a recommended playbook ran and health verification passed, that is a strong positive. Third, curated golden sets: replayable historical incidents with known resolutions, which become regression gates for any model, prompt, or weight change. On top of that I use LLM-as-judge for scalable scoring of summaries and RCA explanations, but calibrated against a human-labeled sample every cycle so judge drift is caught, since an uncalibrated judge is just a second opinion, not an eval. The metrics I would actually report: clustering precision/recall on labeled samples, top-1/top-3 RCA hit rate, citation validity rate, playbook recommendation acceptance rate, and the business proxy, MTTR delta and ticket volume delta per tenant. One Staff-level caveat I would volunteer: these metrics are per-tenant, not global, because a correlation model tuned on one tenant's topology can be wrong for another, so evaluation and tuning are tenant-scoped with global priors.

### Q5. "You claim 'autonomous remediation.' Convince me this cannot take down production."

By construction it cannot act beyond a bounded, pre-approved surface. Every playbook is classified by blast radius, and the approval policy is a monotonic function of that class: read-only diagnostics can auto-run, anything mutating a shared service requires human approval, anything environment-wide requires two. Autonomy is therefore earned per playbook, not granted to the agent: a playbook becomes auto-runnable only after a track record (N supervised successes, success rate above threshold, rollback verified), and that promotion is itself a human decision. The executor enforces the rest: least-privilege scoped credentials per tenant so the agent literally lacks permissions outside its lane, idempotent steps, concurrency locks so two insights cannot trigger conflicting fixes on the same CI, mandatory post-run health verification against the very signals that fired the insight, and automatic rollback on verification failure. Everything is written to an append-only audit log, and there is a global kill switch per tenant that pauses all automation instantly. The framing I would give the interviewer: the LLM recommends, the policy engine decides what is allowed, deterministic code executes. The generative layer never holds the credentials, so the worst case for a hallucination is a bad recommendation that a policy gate or a human catches, not a bad command that runs.

---

## Quick delivery tips for the interview

- Lead with the compression funnel (50M events → 100k insights → LLM only at the top). It is the single idea that makes everything else (cost, latency, scale) tractable, and interviewers reward candidates who find the leverage point.
- When you draw the HLD, draw Kafka early and justify it with the storm math, not with "we need a queue."
- Volunteer the degradation ladder unprompted. Staff-level candidates talk about how systems fail, not just how they work.
- For anything LLM: contract-constrained output, evidence citations, post-validation, non-LLM fallback, and a regression-gated eval loop. That five-part pattern answers most AI grilling questions.