# AI Engineer System Design: LLM-Orchestrated RCA Pipeline over Unknown Tenant Logs

This version is designed for an AI Engineer interview. The infrastructure is background. The star of the show is the AI layer: where the LLM sits, what each LLM call does, what goes into it, what comes out of it, and how every result is collated into the next stage.

---

## 1. Reanalyzing the Problem: Intent and Purpose

Restate the interviewer's problem in your own words first. This is the exact framing to say out loud:

"I have many tenants. Each tenant runs services I know nothing about in advance. One log line might come from an EC2 instance, the next from a network switch, the next from some custom app. I am ingesting all of these logs blindly. I need to build an AI system that reads these logs, figures out on its own what the environment even is, notices when something is wrong, tells me the correct root cause, and tells me how to fix it."

Now decompose the intent. Hidden inside that one sentence are four separate AI problems. Naming them upfront is what makes the rest of the design easy to follow:

1. **Understanding problem**: I do not know what services exist. The AI must discover the environment from the logs themselves. I call this "establishing the truth" about each tenant.
2. **Detection problem**: Among millions of normal log lines, which ones signal that something is wrong?
3. **Correlation problem**: A single failure produces symptoms in many services at once. Which abnormal signals belong to the same underlying incident?
4. **Reasoning problem**: Given one incident and its evidence, what is the root cause, and what is the fix?

One sentence that wins the interview early: "The LLM is not one big box that eats all the logs. It is a set of small, targeted brains placed at exactly the points where language understanding is needed, connected by cheap deterministic plumbing." Then you show where each brain sits.

Why not just feed all logs to an LLM? Say this unprompted: a tenant produces millions of log lines a day. Even a 1M-token context cannot hold one busy hour, and at API prices it would cost thousands of dollars per tenant per day. So the design principle is: **compress with cheap methods first, reason with the LLM last.** Every stage below exists to serve that principle.

---

## 2. The Truth Ledger: What the AI Must Learn on Its Own

Because we know nothing about the tenant, the system must build and maintain three pieces of knowledge per tenant. Together I call them the Truth Ledger. Every later stage reads from it.

- **Truth 1. What exists (the Entity Map)**: the list of services, hosts, and devices, discovered from the logs. Example entries: "payments-api (an application, logs in JSON)", "sw-core-01 (a network switch, logs in syslog)".
- **Truth 2. How they relate (the Dependency Graph)**: which entities talk to which. Discovered from evidence inside logs: IP A appears in the logs of service B, request IDs flow from service X to service Y, timestamps of errors line up.
- **Truth 3. What normal looks like (the Baseline)**: for every entity, the normal mix of log patterns and their normal rates. "payments-api emits template T17 about 50 times a minute" is baseline knowledge.

The Truth Ledger is not written by hand. It is produced by the pipeline itself, mostly by the LLM, in the discovery stages below. It grows and self-corrects with feedback.

---

## 3. The Whole Pipeline in One Picture

Seven stages. Stages 1 to 3 build understanding. Stage 4 detects. Stage 5 correlates. Stage 6 reasons. Stage 7 learns.

```
 raw logs (all tenants, all formats)
      |
 [1] PARSE        logs -> structured events        LLM writes the parsers (offline, once per format)
      |
 [2] TEMPLATE     events -> pattern IDs            Drain algorithm (no LLM), LLM labels each pattern once
      |
 [3] DISCOVER     patterns -> Truth Ledger         LLM extracts entities + relations, builds entity cards
      |
 [4] DETECT       live stream -> anomaly signals   statistics + embeddings (no LLM per event)
      |
 [5] CORRELATE    signals -> one incident          scoring formula groups, LLM verifies the group
      |
 [5.5] NORMALIZE  incident -> Incident State JSON  code + small-LLM classifiers fill an ontology-governed document
      |
 [6] REASON       incident JSON -> RCA + fix       tiered: retrieval -> small LLM -> big-model agent for the hard tail
      |
 [7] LEARN        human verdicts -> better system  feedback into Truth Ledger, ontology, memory, and evals
```

The compression funnel to say out loud: millions of log lines per day become thousands of anomaly signals, become tens of incidents, and the expensive LLM reasoning runs only on those tens. That is why this is affordable and fast.

Now each stage broken down to its lowest form.

---

## 4. The Entire Pipeline with Data Shapes: What the Data Looks Like After Every Step

This is the one diagram to draw on the whiteboard. Left side: the stage and who does the work (code, small LLM, or big LLM). Right side: the exact shape of the data that exists after that stage. If you can narrate this diagram top to bottom, you can answer almost any follow-up, because every question is really "what happens between row N and row N+1."

```
STEP 0. RAW LOG LINES                                  [volume: millions/day/tenant]
  "Jul 13 14:01:47 sw-core-01 %LINK-3-UPDOWN: Interface Gi1/0/24 changed state to down"
  "2026-07-13T14:01:47Z payments-api ERROR conn timeout to db-primary req_id=9f3a"
        |
        |  STAGE 1. PARSE  (deterministic rules; LLM wrote each rule once per format)
        v
STEP 1. STRUCTURED EVENTS                              [same volume, now uniform]
  {ts: "14:01:47", entity: "sw-core-01", severity: "ERROR",
   message: "Interface Gi1/0/24 changed state to down"}
        |
        |  STAGE 2. TEMPLATE MINING  (Drain, no LLM; LLM labels each NEW template once)
        v
STEP 2. TEMPLATE TUPLES + TEMPLATE DICTIONARY          [stream shrinks to IDs]
  live stream:  (sw-core-01, T88, 14:01:47)
  dictionary:   T88 -> pattern "Interface <PORT> changed state to down"
                    -> label {meaning: "network port went down",
                              severity_hint: "critical", category: "network"}
                    -> embedding [0.12, -0.83, ...]
        |
        |  STAGE 3. DISCOVER  (stats propose, small LLM classifies and reviews)
        v
STEP 3. TRUTH LEDGER  (per tenant, always-on background product)
  entity card:  {id: "sw-core-01", type: "network_device",
                 desc: "core switch, syslog format, 48 ports"}
  graph edge:   {src: "payments-api", rel: "reads_writes",
                 dst: "db-primary", confidence: 0.94}
  baseline:     {entity: "payments-api", template: "T17",
                 normal_rate: "50/min", variance: "..."}
        |
        |  STAGE 4. DETECT  (pure statistics vs baseline, zero LLM)
        v
STEP 4. ANOMALY SIGNALS                                [thousands/day, not millions]
  {entity: "vol-0abc", template: "T88", detector: "rate_spike",
   strength: "60x baseline", window: "14:01-14:02"}
        |
        |  STAGE 5. CORRELATE  (4-term affinity score groups; one small-LLM
        |                       call referees each cluster)
        v
STEP 5. INCIDENT (verified cluster of signal IDs)      [tens/day]
  {incident_id: "inc-2091", signals: [S1..S40],
   entities: ["vol-0abc", "db-primary", "payments-api"]}
        |
        |  STAGE 5.5. NORMALIZE  (code + small-LLM classifiers fill the
        |                         Incident State JSON against the Ontology)
        v
STEP 5.5. INCIDENT STATE JSON  (the contract; see the Ontology section)
  {entities_involved, relations, symptoms (typed, enum-valued),
   evidence (typed, with raw_refs), timeline_of_onset, hypotheses: []}
        |
        |  STAGE 6. REASON  (tiered: retrieval -> small LLM -> big LLM escalation)
        v
STEP 6. RCA VERDICT + FIX RECOMMENDATION               [one JSON per incident]
  {root_cause: {statement: "storage volume vol-0abc saturated",
                confidence: 0.81, mechanism: "...", evidence: ["E7","E3"]},
   alternatives: [...],
   fix: {source: "tenant_memory", steps: [...], past_success_rate: 0.96}}
        |
        |  STAGE 7. LEARN  (human verdict captured)
        v
STEP 7. CONFIRMED MEMORY TUPLE  (feeds retrieval, weights, and the Truth Ledger)
  {incident_summary, confirmed_root_cause, fix_applied, outcome: "resolved",
   embedding -> stored in tenant incident memory}
```

Two things to point at while narrating:
- **Volume collapses top to bottom**: millions of lines -> thousands of signals -> tens of incidents -> one JSON each. LLM cost follows the same curve, which is why the expensive calls at the bottom are affordable.
- **Meaning accumulates top to bottom**: a raw line has no meaning; a template tuple has vocabulary; the Truth Ledger has context; the Incident State has typed claims; the verdict has causality. Each stage adds exactly one kind of understanding.

---

## 5. Stage 1. Parse: LLM as Parser-Writer, Not Parser

**The problem in its lowest form**: a syslog line from a switch, a JSON blob from an app, and a stack trace from Java all look completely different. Before anything smart can happen, every line must become the same shape: `{timestamp, source_entity, severity, message}`.

**The naive AI answer (and why it is wrong)**: call the LLM on every log line to extract fields. At millions of lines per day this is absurd on cost and latency.

**The AI engineer answer**: use the LLM as a compiler, not a runtime.

1. New log lines arrive from an unknown source. A cheap fingerprint (first tokens, punctuation shape) says "this format has never been seen."
2. Sample 50 to 100 lines of the unknown format. Send them to the LLM once with the instruction: "Write a parsing rule (regex or JSON path mapping) that extracts timestamp, source, severity, and message from lines like these. Return only the rule."
3. Validate the generated rule by running it against a held-out sample of the same format. If it parses cleanly above a threshold (say 98 percent), register it. If not, retry with the failures included in the prompt (self-correction loop, max 3 attempts). If it still fails, flag for a human.
4. From then on, that format is parsed by the deterministic rule at full line rate, at zero LLM cost.

**What is collated out of this stage**: a Parser Registry (format fingerprint -> parsing rule -> which entities use it) and a uniform event stream. The LLM touched each log format once, not each log line.

Interview soundbite: "The LLM writes the parser; the parser runs at wire speed. One LLM call per format, not per line."

---

## 6. Stage 2. Template Mining: Turning Millions of Lines into Hundreds of Patterns

**The problem in its lowest form**: these three lines

```
Connection timeout to 10.0.1.5 after 30s
Connection timeout to 10.0.2.9 after 12s
Connection timeout to 10.0.3.1 after 45s
```

are really one pattern: `Connection timeout to <IP> after <N>s`. A tenant with millions of daily lines typically has only a few hundred to a few thousand distinct patterns.

**How it works, lowest form**:
1. Run each parsed message through Drain (a classic streaming log-templating algorithm, a simple prefix-tree, no ML). It replaces variable parts with placeholders and assigns a stable template ID, like T42.
2. The first time a new template appears, make exactly one LLM call: "Here is a log pattern and 3 example lines. Return JSON: {meaning: one sentence, severity_hint: info|warn|critical, category: network|storage|app|auth|hardware}." Store that label forever.
3. Compute one embedding vector per template (not per line) and store it in a vector index.

**Why this stage is the whole ballgame**: after this, the live stream is no longer text. It is a stream of (entity, template_id, timestamp) tuples. Counting, baselining, and similarity all become trivially cheap. And the LLM's semantic understanding of each pattern was bought once and cached.

**What is collated**: the Template Dictionary per tenant: template ID -> pattern -> LLM label -> embedding. A few thousand rows. This is the vocabulary of the tenant's environment.

---

## 7. Stage 3. Discover: The LLM Builds the Truth Ledger

This is where "you know nothing about the tenant" gets solved. Three sub-steps.

**7.1 Entity extraction.** From the parsed events, collect all distinct source identifiers (hostnames, IPs, service names, device names). For each new entity, gather its top 20 templates and ask the LLM once: "Based on these log patterns, what is this thing? Return JSON: {entity_type: e.g. web service | database | network switch | load balancer, runtime_guess, one_line_description}." A switch talking spanning-tree protocol and port flaps is unmistakable to an LLM even though no human ever labeled it. The output is an **Entity Card**.

**7.2 Relationship inference (how the dependency graph is built with zero config).** Three evidence sources, in increasing strength:
- **Mentions**: entity A's logs contain entity B's IP or hostname. Weak but broad signal.
- **Trace continuity**: the same request ID or session ID appears in A's logs then B's logs. Strong signal, gives direction (A calls B).
- **Temporal coupling**: when A starts erroring, B starts erroring within seconds, repeatedly across history. Statistical signal (lagged cross-correlation of error rates).

Each evidence type adds a weighted edge. Edges above a confidence threshold enter the Dependency Graph. The LLM's role here is small but valuable: given a proposed edge and its evidence, sanity-check it ("A load balancer depending on a printer? Reject."). LLM as reviewer of statistically proposed structure.

**7.3 Baseline learning (what normal looks like).** Pure statistics, no LLM. For every (entity, template) pair, keep a rolling profile: mean rate, variance, hour-of-day seasonality. Cheap counters. This is Truth 3.

**What is collated**: the Truth Ledger = Entity Cards + Dependency Graph + Baselines. Say this in the interview: "Before my system ever answers an RCA question, it has already read the tenant's logs and written its own documentation of the environment. That documentation is what makes the later LLM calls grounded instead of guessy."

---

## 8. Stage 4. Detect: Cheap Filters Decide What Deserves Attention

**The problem in its lowest form**: 99.99 percent of log lines are normal. Something must throw those away without paying LLM prices.

Four detectors, all deterministic, all per (entity, template):
1. **New template**: a pattern never seen before just appeared. Novelty is inherently suspicious.
2. **Rate spike**: template T42 normally fires 5 times per minute, now 500. Compare to baseline with a z-score.
3. **Severity shift**: templates the LLM labeled critical (back in Stage 2) get a lower trigger bar. This is where the one-time LLM labels pay rent.
4. **Sequence break**: entity normally emits T1 -> T2 -> T3 during a job; today T3 never came. A simple learned transition table catches it.

Each firing produces an **Anomaly Signal**: `{entity, template_id, detector, strength, window}`. Millions of lines per day become perhaps a few thousand signals per day.

Say this: "Notice the LLM is absent here. Detection is a counting problem, and you should never use a language model for a counting problem. The LLM's fingerprints are here anyway, through the severity labels it wrote once in Stage 2."

---

## 9. Stage 5. Correlate: The Correlation Analysis, Fully Unpacked

This is the stage the interviewer will probe hardest, so break it to its atoms.

**The problem in its lowest form**: a storage array slows down. Within 60 seconds: the database logs slow-query warnings, three app services log timeouts, the load balancer logs unhealthy backends, the switch logs nothing (it is fine). That is 40 anomaly signals from 5 entities. They are ONE incident. Meanwhile an unrelated cert-expiry warning fired on another host. That must NOT be pulled in.

**Step 1. Candidate grouping by time.** Open a sliding window (say 5 minutes). All signals inside the window are candidates for grouping. Time is the coarsest filter: things that happen together might belong together.

**Step 2. Pairwise affinity scoring.** For every pair of signals in the window, compute one number:

```
affinity(a, b) =
    w1 * time_closeness(a, b)        happened within seconds of each other?
  + w2 * graph_closeness(a, b)       are their entities neighbors in the Dependency Graph?
  + w3 * semantic_closeness(a, b)    cosine similarity of their template embeddings
  + w4 * shared_identifiers(a, b)    same request ID, same IP, same volume name in the raw lines?
```

Walk the interviewer through each term with the storage example:
- time_closeness: the DB warning and app timeouts fired 20 seconds apart. High.
- graph_closeness: Stage 3 already learned that app -> DB -> storage. Distance 1 or 2 hops. High.
- semantic_closeness: "slow query" and "connection timeout" embed near each other, both are latency-flavored. Medium-high.
- shared_identifiers: the volume name vol-0abc appears in both the storage and DB lines. Very high, and this term is the most precise of the four.
- the cert-expiry signal scores near zero on all four terms against everything in this cluster. It stays out.

**Step 3. Clustering.** Build a graph where signals are nodes and edges are pairs with affinity above a threshold. Connected components are incident candidates. This is simple, explainable, and streamable.

**Step 4. LLM verification of the cluster (the AI twist).** The math can over-merge. So before an incident is born, one LLM call: "Here are 8 anomaly signals with their entity cards and example lines. Do they describe one incident or several? Return JSON: {clusters: [[signal_ids]], reasoning}." The LLM is the semantic referee that pure vector similarity cannot be, because it reads the actual content. It runs on tens of signals, not millions, so it is cheap here.

**Step 5. Incident creation.** The verified cluster becomes an Incident object with a stable ID. Late-arriving signals can join (affinity against the cluster centroid); the cluster freezes after a quiet period.

**Where the weights come from**: start with hand-set weights, then tune per tenant from feedback (Stage 7). When an engineer says "this grouping was wrong," that is a labeled pair, and the weights move.

---

## 10. Stage 5.5. The Ontology JSON and the Incident State: The Contract Between Perception and Reasoning

This is the bridge between "signals grouped into an incident" and "an LLM reasons about it." The idea: maintain one normalized, structured JSON view of everything known about an incident so far, and make that document, never raw logs, the only thing the RCA layer reads. In classical AI this is a blackboard architecture: many specialist writers append typed evidence to one shared state; reasoners read from that state. It solves three problems at once:

1. **Token economy**: the reasoner reads one compact document. This is what makes small models viable.
2. **Decoupling**: every writer (detector, correlator, human) and every reader (RCA agent, ticket generator, UI) speaks one contract. Models can be swapped without touching the pipeline.
3. **Auditability**: the JSON at any timestamp IS the system's belief. It can be diffed, replayed, and evaluated against.

### 10.1 Two artifacts, not one: the ontology vs the instance

A distinction to make explicitly in the interview, because it sharpens the whole pitch.

**Artifact 1: The Ontology (the schema, versioned, mostly static).** It defines the vocabulary the entire system speaks. Every field is a closed vocabulary, an enum, never free text:

```json
{
  "ontology_version": "1.3",
  "entity_types": ["compute_instance", "network_device", "database",
                   "storage_volume", "load_balancer", "application_service", "unknown"],
  "relation_types": ["calls", "runs_on", "routes_to", "reads_writes", "colocated_with"],
  "symptom_classes": ["latency_degradation", "error_rate_spike", "resource_saturation",
                      "connectivity_loss", "restart_loop", "config_change_detected",
                      "novel_pattern"],
  "evidence_types": ["log_anomaly", "rate_deviation", "sequence_break",
                     "topology_inference", "change_event", "historical_match"],
  "hypothesis_states": ["proposed", "supported", "contradicted", "confirmed"]
}
```

**Artifact 2: The Incident State JSON (the instance, one per incident, evolving).** The living evidence document. Every entry conforms to the ontology's vocabulary:

```json
{
  "incident_id": "inc-2091",
  "ontology_version": "1.3",
  "status": "investigating",
  "entities_involved": [
    {"id": "payments-api", "type": "application_service", "baseline_deviation": "40x_error_rate"},
    {"id": "db-primary",   "type": "database",            "baseline_deviation": "12x_slow_queries"},
    {"id": "vol-0abc",     "type": "storage_volume",      "baseline_deviation": "novel_pattern"}
  ],
  "relations": [
    {"src": "payments-api", "rel": "reads_writes", "dst": "db-primary", "confidence": 0.94},
    {"src": "db-primary",   "rel": "runs_on",      "dst": "vol-0abc",  "confidence": 0.88}
  ],
  "symptoms": [
    {"sid": "S1", "class": "latency_degradation", "entity": "db-primary",   "onset": "14:02:11", "evidence_refs": ["E3", "E4"]},
    {"sid": "S2", "class": "error_rate_spike",    "entity": "payments-api", "onset": "14:02:39", "evidence_refs": ["E1"]},
    {"sid": "S3", "class": "resource_saturation", "entity": "vol-0abc",     "onset": "14:01:47", "evidence_refs": ["E7"]}
  ],
  "evidence": [
    {"eid": "E7", "type": "log_anomaly", "entity": "vol-0abc",
     "summary": "io_latency template T88 at 60x baseline",
     "raw_ref": "logstore://tenant42/vol-0abc/14:01/T88"}
  ],
  "timeline_of_onset": ["vol-0abc", "db-primary", "payments-api"],
  "hypotheses": [
    {"hid": "H1", "cause_entity": "vol-0abc", "cause_class": "resource_saturation",
     "state": "supported", "explains": ["S1", "S2", "S3"], "confidence": 0.81}
  ]
}
```

Raw log text never lives in this document, only typed claims plus pointers (`raw_ref`). That single choice keeps it small enough for small models.

### 10.2 How it gets built: every writer is a classifier

Each pipeline stage appends to a specific section, and each append is a closed-vocabulary classification task, which is exactly what small models are good at:

1. **Detectors (no LLM)** write raw facts into `evidence[]`: entity, template ID, deviation magnitude, timestamp. Pure code.
2. **A small LLM normalizes each evidence item into a symptom.** Prompt shape: "Here is one anomaly with its template label and entity card. Classify into exactly one of these 7 symptom_classes. Return one token." A 7-way classification with the choices in the prompt. An 8B-class model does this near-perfectly, and it is verifiable: if the output falls outside the enum, code rejects and retries.
3. **The correlation stage (code)** writes `entities_involved`, `relations` (copied from the Truth Ledger subgraph), and `timeline_of_onset`. Zero LLM.
4. **A small LLM does symptom-level dedup**: "S4 and S9 are both error_rate_spike on payments-api within 30 seconds. Same symptom? yes/no." Binary classification.
5. **Code validates every write** against the ontology schema. Invalid writes never land.

**When is the JSON "ready" for RCA?** Three deterministic conditions, checked by code, not by a model's opinion: every evidence item has been classified into a symptom, every involved entity has an onset entry, and no new writes for a quiet period (say 60 seconds). Readiness is a code check.

**The deep principle, said out loud**: normalization converts open-ended reasoning into closed-set classification, and closed-set classification is where small models match big ones. The ontology is the device that performs that conversion.

### 10.3 The payoff: tiered RCA that squeezes out the big model

Because the Incident State JSON is normalized, root cause analysis becomes a cascade:

**Tier 0, no LLM.** Embed the symptom set plus topology shape and search resolved-incident memory. If a past incident matches strongly ("storage saturation under a database, apps timing out downstream"), the confirmed root cause pattern and fix are retrieved, not generated. As memory grows, a large fraction of incidents resolve here.

**Tier 1, small model, structured reasoning over the JSON.** The document already contains the two strongest causal signals in machine-readable form: `timeline_of_onset` (vol-0abc degraded first) and `relations` (it sits at the bottom of the dependency chain). Prompt: "Given this incident JSON, which entity is the most likely origin? Rule: earlier onset upstream of later symptoms suggests cause. Return {cause_entity, cause_class, explains: [sids], confidence}." This is no longer open-ended reasoning over messy logs; it is reading a pre-digested causal puzzle. A small model handles the common topologies.

**Tier 2, big model, escalation only.** Escalation triggers, all detected by code: the small model's answer fails validation (claims to explain symptoms it does not cover), confidence below threshold, multiple hypotheses tie, simultaneous onsets (ambiguous timeline), or the incident spans disconnected subgraphs. The big model receives the same JSON plus the read-only tool loop from Stage 6. Expect roughly 10 to 20 percent of incidents here, one incident-level call each.

**Confidence gating between tiers is code, not self-grading**: schema validation, coverage check (does the hypothesis explain all symptoms?), and self-consistency (run the small model 3 times at nonzero temperature; disagreement means escalate).

### 10.4 Two pitfalls to preempt before the interviewer finds them

**Ontology drift.** What happens when reality does not fit the enums? Every enum includes an `unknown` / `novel_pattern` escape hatch; those instances queue for human review; recurring unknowns become new ontology terms in a versioned release with backward-compatible migration. The ontology is maintained like code. Adding a new symptom class is a registry entry, not a pipeline change.

**Lossy compression.** Normalization throws information away by design, and sometimes the discarded detail was the clue. Mitigation: the `raw_ref` pointers. The Tier 2 agent's tools can always dereference back to raw lines. Compression is the default path; decompression is available on escalation. Volunteering this weakness unprompted is a Staff-level move.

**One-line pitch for this whole section**: "The ontology JSON is the contract between perception and reasoning. Perception, mostly code plus small classifiers, fills it; reasoning reads it. Because the contract uses closed vocabularies, every LLM task collapses to classification or one bounded reasoning call, which is why the pipeline runs on small models with a big model reserved for the ambiguous tail."

---

## 11. Stage 6. Reason: The RCA Agent, Every Gear Visible

This is the centerpiece for an AI engineer interview. With the tiered cascade from Section 10.3 in place, this full agent is the **Tier 2 escalation path**: it runs only on the ambiguous 10 to 20 percent of incidents that retrieval (Tier 0) and the small-model structured pass (Tier 1) could not settle. It is an orchestrated agent (LangGraph-style state machine), not a single prompt. Five nodes.

```
 [A. Collate evidence] -> [B. Hypothesize] -> [C. Investigate (tools, loop)] -> [D. Verdict] -> [E. Recommend fix]
                                   ^                    |
                                   +---- refine <-------+   (max 3 loops)
```

**Node A. Collate evidence (deterministic code, no LLM).** This answers "how are you collating each and every result." The agent does not go fishing in raw logs. The starting point is the Incident State JSON from Section 10; code expands it into a fixed-shape **Evidence Pack** by dereferencing the strongest `raw_ref` pointers and attaching retrieval results, with a hard token budget per section:

1. The incident's anomaly signals, ranked by strength (top 15).
2. For each involved entity: its Entity Card and its baseline deviation ("payments-api is at 40x its normal error rate").
3. The Dependency Graph neighborhood: just the subgraph of involved entities plus one hop, rendered as text ("app-1 -> db-1 -> storage-vol-0abc").
4. A timeline: first abnormal signal per entity, in order. Order of onset is the single strongest root cause clue, so it gets its own section.
5. Recent change events if any exist in the logs (deploy markers, config-change templates).
6. Top 3 similar past incidents, retrieved by embedding the incident summary-so-far against a vector store of previously resolved incidents, with their confirmed root causes.
7. Raw log excerpts: only the 20 to 30 lines behind the strongest signals, each with a citation tag like [E7].

Every item carries a citation tag. Budget rule: if a section overflows, summarize that section with a cheap model first (map-reduce), then insert the summary. The pack is typically 4k to 8k tokens. Deterministic in, deterministic shape out.

**Node B. Hypothesize (LLM call 1).** Prompt: "You are diagnosing an incident. Here is the Evidence Pack. Propose up to 3 root cause hypotheses. For each: {cause, mechanism: how it explains EVERY symptom, evidence: [citation tags], what_would_confirm: a checkable question}. Return strict JSON." Forcing "mechanism must explain every symptom" is the anti-hallucination trick: a wrong hypothesis usually explains some symptoms and the model must confront the leftovers. Forcing "what_would_confirm" sets up the next node.

**Node C. Investigate (the agentic loop, LLM + tools).** The model asked its own confirmation questions; now it gets tools to answer them:
- `search_logs(entity, query, time_range)`: templated search over the parsed store. Returns matching lines with citations.
- `get_rate(entity, template_id, time_range)`: pull the actual counter series.
- `graph_neighbors(entity)`: read the Dependency Graph.
- `get_baseline(entity, template_id)`: what normal looks like.
- `similar_incidents(text)`: vector search over resolved history.

The loop: the orchestrator executes the tool calls, appends results to state, and re-invokes the LLM: "Given the new evidence, confirm, reject, or refine each hypothesis." Hard limits: max 3 iterations, max 10 tool calls, then forced to conclude. Every tool is read-only. Say that sentence in the interview: "the reasoning agent has no write permissions anywhere; it can only look."

**Node D. Verdict (LLM call, then validation code).** Output contract: `{root_cause: {statement, confidence, mechanism, evidence: [tags]}, alternatives: [...], affected_scope, summary_for_humans}`. Then a validator (pure code) checks: JSON parses, every evidence tag exists in the pack or tool results, confidence within bounds, no entity named that is not in the incident. Any hypothesis with missing citations is dropped. If everything is dropped, output "insufficient evidence" honestly, which is itself a feature: a system that can say "I do not know" is trustworthy.

**Node E. Recommend fix (RAG first, generate second).**
1. Embed the confirmed root cause and search two corpora: the tenant's own resolved-incident memory ("last time vol-0abc saturated, the fix was migrating the DB volume") and a general runbook library.
2. If a strong match exists, recommend it with its historical success rate.
3. If not, the LLM drafts a fix plan as ordered steps, each marked read-only or mutating, with a stated risk. Clearly labeled "generated, unverified."
4. Everything is a recommendation to a human. Execution, if built at all, sits behind approvals outside the AI layer.

**Model routing (say this, it screams AI engineer)**: small cheap model for Stage 1 parser-writing retries, Stage 2 labeling, and map-reduce summarization; strong model only for Nodes B to D. Roughly 90 percent of calls on the cheap model, and the expensive model runs perhaps tens of times per day per tenant.

---

## 12. Stage 7. Learn: Establishing Truth Over Time

"Establish this truth" has a second meaning: the system must get MORE correct with use.

1. **Verdict capture**: every RCA is shown with accept / reject / correct buttons. An engineer's correction ("root cause was actually the failed NIC, not the storage") is gold.
2. **Incident memory**: the final, human-confirmed (incident summary, root cause, fix, outcome) tuple is embedded and stored per tenant. This is the retrieval corpus for Node E and Evidence Pack section 6. The system literally gains experience.
3. **Weight tuning**: grouping corrections retrain the affinity weights w1..w4 per tenant.
4. **Truth Ledger repair**: if the RCA revealed a dependency the graph missed, propose the edge; if an Entity Card was wrong, re-run classification with the new evidence.
5. **Eval harness (the AI engineer's regression suite)**: keep a golden set of resolved incidents per tenant. On any prompt, model, or weight change, replay them and score: top-1 root cause hit rate, citation validity rate, grouping precision/recall, and judge-scored summary quality with an LLM-as-judge that is itself spot-checked against human labels monthly. No change ships if the golden set regresses. This mirrors an eval-gated pipeline: same discipline, applied to RCA.

---

## 13. One-Breath Summary to Open or Close the Interview

"Logs from unknown services flow in. An LLM writes a parser once per format, so parsing is free at runtime. Drain compresses millions of lines into a few thousand templates, and the LLM labels each template once, so the system has a cached semantic understanding of the tenant's entire vocabulary. From entities and co-occurrence the system writes its own map of the environment: what exists, what depends on what, what normal looks like. Cheap statistics watch the live stream and raise anomaly signals. A four-term affinity score groups signals into incidents, and a small LLM referees the grouping. Everything known about an incident is then normalized into an ontology-governed Incident State JSON: typed symptoms, typed evidence with pointers back to raw logs, the dependency subgraph, and the onset timeline. Because that document uses closed vocabularies, most of the AI work collapses into small-model classification, and RCA itself runs as a cascade: retrieval from confirmed incident memory first, a small model reasoning over the structured JSON second, and a big-model agent with read-only tools only for the ambiguous tail. The verdict must cite its evidence or it is dropped by code. Every human verdict feeds back into the weights, the map, the ontology, and the memory, so the system's truth about each tenant compounds. The LLM never sees raw volume and never holds write access; it sits exactly where language understanding is needed and nowhere else."