# Python Data Structures & Algorithms — LeetCode Interview Guide

---

## 1. Arrays / Lists

Python lists are dynamic arrays backed by a contiguous block of memory.

### Core Operations

```python
arr = []                    # init empty
arr = [0] * n               # init with size n, all zeros
arr = [x for x in range(n)] # list comprehension

# --- Access / Update --- O(1)
arr[i]                      # access by index
arr[-1]                     # last element
arr[i] = val                # update

# --- Append / Pop (end) --- O(1) amortized
arr.append(val)
arr.pop()                   # removes & returns last

# --- Insert / Delete (arbitrary) --- O(n)
arr.insert(i, val)          # insert val at index i
arr.pop(i)                  # remove & return at index i
arr.remove(val)             # remove first occurrence of val
del arr[i]                  # delete at index i

# --- Search --- O(n)
val in arr                  # membership check
arr.index(val)              # first index of val (raises ValueError if absent)
arr.count(val)              # count occurrences

# --- Slicing --- O(k)
arr[start:stop]             # elements from start to stop-1
arr[start:stop:step]        # with step
arr[::-1]                   # reversed copy
arr[:]                      # shallow copy

# --- Sorting --- O(n log n)
arr.sort()                  # in-place, ascending
arr.sort(reverse=True)      # in-place, descending
arr.sort(key=lambda x: x[1])# sort by custom key
sorted(arr)                 # returns new sorted list (original unchanged)

# --- Other Useful ---
len(arr)                    # length                O(1)
min(arr), max(arr)          # min/max               O(n)
sum(arr)                    # sum                   O(n)
arr.reverse()               # in-place reverse      O(n)
arr.extend(other)           # append all from other  O(k)
arr + other                 # concatenate (new list) O(n+k)
```

### Common Patterns

```python
# Two pointers (sorted array)
left, right = 0, len(arr) - 1
while left < right:
    # process arr[left], arr[right]
    pass

# Sliding window
left = 0
for right in range(len(arr)):
    # expand window: add arr[right]
    while window_is_invalid:
        # shrink window: remove arr[left]
        left += 1
    # update answer

# Prefix sum
prefix = [0] * (len(arr) + 1)
for i in range(len(arr)):
    prefix[i + 1] = prefix[i] + arr[i]
# sum of arr[l:r] = prefix[r] - prefix[l]

# Kadane's algorithm (max subarray sum)
max_sum = cur_sum = arr[0]
for num in arr[1:]:
    cur_sum = max(num, cur_sum + num)
    max_sum = max(max_sum, cur_sum)
```

---

## 2. Strings

Python strings are **immutable** sequences of Unicode characters.

### Core Operations

```python
s = "hello"

# --- Access --- O(1)
s[i]                        # char at index
s[-1]                       # last char

# --- Slicing --- O(k)
s[start:stop]
s[::-1]                     # reversed string

# --- Search --- O(n)
s.find("ll")                # first index, -1 if not found
s.index("ll")               # first index, raises ValueError if absent
s.rfind("ll")               # last index
s.count("l")                # count occurrences
"ell" in s                  # membership check

# --- Checks --- O(n)
s.startswith("he")
s.endswith("lo")
s.isalpha()                 # all alphabetic
s.isdigit()                 # all digits
s.isalnum()                 # all alphanumeric
s.islower() / s.isupper()

# --- Transform (returns new string) --- O(n)
s.lower() / s.upper()
s.strip()                   # remove leading/trailing whitespace
s.lstrip() / s.rstrip()
s.replace("l", "r")         # replace all occurrences
s.split(" ")                # split into list by delimiter
" ".join(["a", "b", "c"])   # join list into string
s.capitalize() / s.title()

# --- Building strings efficiently ---
parts = []
for ch in some_source:
    parts.append(ch)
result = "".join(parts)      # O(n) — much better than s += ch in a loop

# --- Char ↔ Int ---
ord('a')                     # 97
chr(97)                      # 'a'
ord(ch) - ord('a')           # 0-based alphabet index
```

### Common Patterns

```python
# Frequency counter
from collections import Counter
freq = Counter(s)            # {'h':1, 'e':1, 'l':2, 'o':1}

# Check anagram
Counter(s1) == Counter(s2)
# or
sorted(s1) == sorted(s2)

# Palindrome check
s == s[::-1]
# or two-pointer
def is_palindrome(s):
    l, r = 0, len(s) - 1
    while l < r:
        if s[l] != s[r]:
            return False
        l += 1
        r -= 1
    return True
```

---

## 3. Hash Maps (dict) & Hash Sets (set)

### dict — Core Operations

```python
d = {}                          # empty dict
d = {"a": 1, "b": 2}
d = dict.fromkeys(["a","b"], 0) # {"a": 0, "b": 0}

# --- Access / Update --- O(1) average
d["a"]                          # access (KeyError if missing)
d.get("a", default)             # access with default (no error)
d["c"] = 3                      # insert / update
del d["a"]                      # delete key
d.pop("a", default)             # remove & return value

# --- Membership --- O(1)
"a" in d                        # check key existence

# --- Iteration --- O(n)
d.keys()                        # view of keys
d.values()                      # view of values
d.items()                       # view of (key, value) pairs
for k, v in d.items(): ...

# --- Useful Methods ---
d.setdefault("x", []).append(1) # insert default if missing, then return value
d.update(other_dict)            # merge another dict
len(d)                          # number of keys
```

### defaultdict & Counter

```python
from collections import defaultdict, Counter

# defaultdict — auto-initializes missing keys
graph = defaultdict(list)       # missing key → []
freq = defaultdict(int)         # missing key → 0
freq["a"] += 1

# Counter
c = Counter("aabbc")            # Counter({'a':2, 'b':2, 'c':1})
c.most_common(2)                # [('a',2), ('b',2)]
c["z"]                          # 0 (no KeyError)
c1 + c2                         # combine counts
c1 - c2                         # subtract (drops zero/negative)
c1 & c2                         # intersection (min of each)
c1 | c2                         # union (max of each)
```

### set — Core Operations

```python
s = set()
s = {1, 2, 3}
s = set([1, 2, 3])              # from list

# --- Add / Remove --- O(1)
s.add(val)
s.remove(val)                   # KeyError if absent
s.discard(val)                  # no error if absent
s.pop()                         # remove & return arbitrary element

# --- Membership --- O(1)
val in s

# --- Set Operations --- O(min(len(a), len(b)))
a | b       # union            (a.union(b))
a & b       # intersection     (a.intersection(b))
a - b       # difference       (a.difference(b))
a ^ b       # symmetric diff   (a.symmetric_difference(b))
a <= b      # subset check     (a.issubset(b))
a >= b      # superset check   (a.issuperset(b))
```

---

## 4. Stacks

LIFO — use a Python **list**.

```python
stack = []

stack.append(val)       # push         O(1)
stack.pop()             # pop          O(1)
stack[-1]               # peek         O(1)
len(stack)              # size         O(1)
not stack               # is empty?    O(1)
```

### Classic Problems

```python
# Valid parentheses
def is_valid(s: str) -> bool:
    stack = []
    mapping = {')': '(', ']': '[', '}': '{'}
    for ch in s:
        if ch in mapping:
            if not stack or stack[-1] != mapping[ch]:
                return False
            stack.pop()
        else:
            stack.append(ch)
    return not stack

# Monotonic stack — next greater element
def next_greater(nums):
    n = len(nums)
    result = [-1] * n
    stack = []                      # stack of indices
    for i in range(n):
        while stack and nums[stack[-1]] < nums[i]:
            result[stack.pop()] = nums[i]
        stack.append(i)
    return result
```

---

## 5. Queues

### Regular Queue — collections.deque

```python
from collections import deque

q = deque()

q.append(val)           # enqueue (right)     O(1)
q.popleft()             # dequeue (left)      O(1)
q[0]                    # peek front          O(1)
len(q)                  # size                O(1)

# deque also supports stack-like ops and double-ended ops:
q.appendleft(val)       # push to front       O(1)
q.pop()                 # pop from right      O(1)
```

### BFS Template

```python
from collections import deque

def bfs(graph, start):
    visited = {start}
    queue = deque([start])
    while queue:
        node = queue.popleft()
        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)
```

### BFS on Grid (Level-Order)

```python
def bfs_grid(grid, sr, sc):
    rows, cols = len(grid), len(grid[0])
    visited = {(sr, sc)}
    queue = deque([(sr, sc)])
    directions = [(0,1),(0,-1),(1,0),(-1,0)]
    level = 0
    while queue:
        for _ in range(len(queue)):    # process one level
            r, c = queue.popleft()
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                if 0 <= nr < rows and 0 <= nc < cols and (nr, nc) not in visited and grid[nr][nc] != 0:
                    visited.add((nr, nc))
                    queue.append((nr, nc))
        level += 1
```

---

## 6. Heaps (Priority Queues)

Python's `heapq` provides a **min-heap** backed by a list.

```python
import heapq

h = []

heapq.heappush(h, val)          # push          O(log n)
heapq.heappop(h)                # pop min       O(log n)
h[0]                            # peek min      O(1)
heapq.heapify(arr)              # list → heap   O(n)
heapq.heappushpop(h, val)       # push then pop O(log n)
heapq.heapreplace(h, val)       # pop then push O(log n)
heapq.nlargest(k, arr)          # k largest     O(n log k)
heapq.nsmallest(k, arr)         # k smallest    O(n log k)

# --- Max-Heap trick: negate values ---
heapq.heappush(h, -val)
max_val = -heapq.heappop(h)

# --- Heap with tuples (priority, data) ---
heapq.heappush(h, (priority, data))
# Compares by first element, then second, etc.

# --- Top K frequent elements ---
def top_k_frequent(nums, k):
    freq = Counter(nums)
    return heapq.nlargest(k, freq.keys(), key=freq.get)
```

### Two-Heap Pattern (Median Finder)

```python
class MedianFinder:
    def __init__(self):
        self.lo = []   # max-heap (negated) — smaller half
        self.hi = []   # min-heap           — larger half

    def addNum(self, num):
        heapq.heappush(self.lo, -num)
        heapq.heappush(self.hi, -heapq.heappop(self.lo))
        if len(self.hi) > len(self.lo):
            heapq.heappush(self.lo, -heapq.heappop(self.hi))

    def findMedian(self):
        if len(self.lo) > len(self.hi):
            return -self.lo[0]
        return (-self.lo[0] + self.hi[0]) / 2
```

---

## 7. Linked Lists

Python has no built-in linked list for interviews — you build your own.

```python
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
```

### Common Operations

```python
# --- Traverse ---
cur = head
while cur:
    print(cur.val)
    cur = cur.next

# --- Reverse (iterative) ---
def reverse(head):
    prev, cur = None, head
    while cur:
        nxt = cur.next
        cur.next = prev
        prev = cur
        cur = nxt
    return prev

# --- Detect cycle (Floyd's) ---
def has_cycle(head):
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            return True
    return False

# --- Find cycle start ---
def cycle_start(head):
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            slow = head
            while slow is not fast:
                slow = slow.next
                fast = fast.next
            return slow
    return None

# --- Find middle ---
def middle(head):
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    return slow

# --- Merge two sorted lists ---
def merge(l1, l2):
    dummy = cur = ListNode(0)
    while l1 and l2:
        if l1.val <= l2.val:
            cur.next = l1
            l1 = l1.next
        else:
            cur.next = l2
            l2 = l2.next
        cur = cur.next
    cur.next = l1 or l2
    return dummy.next

# --- Dummy head trick ---
# Use when the head itself might change (deletions, insertions at front)
dummy = ListNode(0)
dummy.next = head
# ... operate ...
return dummy.next
```

---

## 8. Trees

### Binary Tree Node

```python
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
```

### Traversals

```python
# --- DFS Recursive ---
def preorder(root):            # Root → Left → Right
    if not root: return []
    return [root.val] + preorder(root.left) + preorder(root.right)

def inorder(root):             # Left → Root → Right  (BST → sorted)
    if not root: return []
    return inorder(root.left) + [root.val] + inorder(root.right)

def postorder(root):           # Left → Right → Root
    if not root: return []
    return postorder(root.left) + postorder(root.right) + [root.val]

# --- DFS Iterative (Inorder) ---
def inorder_iter(root):
    stack, result = [], []
    cur = root
    while cur or stack:
        while cur:
            stack.append(cur)
            cur = cur.left
        cur = stack.pop()
        result.append(cur.val)
        cur = cur.right
    return result

# --- BFS / Level Order ---
def level_order(root):
    if not root: return []
    result, queue = [], deque([root])
    while queue:
        level = []
        for _ in range(len(queue)):
            node = queue.popleft()
            level.append(node.val)
            if node.left:  queue.append(node.left)
            if node.right: queue.append(node.right)
        result.append(level)
    return result
```

### Common Tree Problems

```python
# --- Max depth ---
def max_depth(root):
    if not root: return 0
    return 1 + max(max_depth(root.left), max_depth(root.right))

# --- Check balanced ---
def is_balanced(root):
    def check(node):
        if not node: return 0
        l, r = check(node.left), check(node.right)
        if l == -1 or r == -1 or abs(l - r) > 1: return -1
        return 1 + max(l, r)
    return check(root) != -1

# --- Diameter of binary tree ---
def diameter(root):
    ans = 0
    def depth(node):
        nonlocal ans
        if not node: return 0
        l, r = depth(node.left), depth(node.right)
        ans = max(ans, l + r)
        return 1 + max(l, r)
    depth(root)
    return ans

# --- Lowest Common Ancestor ---
def lca(root, p, q):
    if not root or root == p or root == q:
        return root
    left = lca(root.left, p, q)
    right = lca(root.right, p, q)
    if left and right: return root
    return left or right

# --- Validate BST ---
def is_valid_bst(root, lo=float('-inf'), hi=float('inf')):
    if not root: return True
    if root.val <= lo or root.val >= hi: return False
    return (is_valid_bst(root.left, lo, root.val) and
            is_valid_bst(root.right, root.val, hi))

# --- Serialize / Deserialize (BFS) ---
def serialize(root):
    if not root: return ""
    result, q = [], deque([root])
    while q:
        node = q.popleft()
        if node:
            result.append(str(node.val))
            q.append(node.left)
            q.append(node.right)
        else:
            result.append("null")
    return ",".join(result)

def deserialize(data):
    if not data: return None
    vals = data.split(",")
    root = TreeNode(int(vals[0]))
    q, i = deque([root]), 1
    while q:
        node = q.popleft()
        if vals[i] != "null":
            node.left = TreeNode(int(vals[i]))
            q.append(node.left)
        i += 1
        if vals[i] != "null":
            node.right = TreeNode(int(vals[i]))
            q.append(node.right)
        i += 1
    return root
```

---

## 9. Tries (Prefix Trees)

```python
class TrieNode:
    def __init__(self):
        self.children = {}      # char → TrieNode
        self.is_end = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str) -> None:            # O(m)
        node = self.root
        for ch in word:
            if ch not in node.children:
                node.children[ch] = TrieNode()
            node = node.children[ch]
        node.is_end = True

    def search(self, word: str) -> bool:            # O(m)
        node = self._find(word)
        return node is not None and node.is_end

    def startsWith(self, prefix: str) -> bool:      # O(m)
        return self._find(prefix) is not None

    def _find(self, prefix: str):
        node = self.root
        for ch in prefix:
            if ch not in node.children:
                return None
            node = node.children[ch]
        return node
```

---

## 10. Graphs

### Representations

```python
# Adjacency list (most common in interviews)
from collections import defaultdict
graph = defaultdict(list)
for u, v in edges:
    graph[u].append(v)
    graph[v].append(u)       # omit for directed graph

# Adjacency matrix
n = len(nodes)
matrix = [[0] * n for _ in range(n)]
for u, v in edges:
    matrix[u][v] = 1
    matrix[v][u] = 1         # omit for directed
```

### DFS & BFS

```python
# --- DFS (recursive) ---
def dfs(graph, node, visited):
    visited.add(node)
    for nei in graph[node]:
        if nei not in visited:
            dfs(graph, nei, visited)

# --- DFS (iterative) ---
def dfs_iter(graph, start):
    visited = {start}
    stack = [start]
    while stack:
        node = stack.pop()
        for nei in graph[node]:
            if nei not in visited:
                visited.add(nei)
                stack.append(nei)

# --- BFS (see Queue section above) ---

# --- Count connected components ---
def count_components(n, edges):
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
    visited = set()
    count = 0
    for i in range(n):
        if i not in visited:
            dfs(graph, i, visited)
            count += 1
    return count
```

### Topological Sort (Kahn's BFS)

```python
from collections import deque

def topo_sort(num_nodes, edges):
    graph = defaultdict(list)
    indegree = [0] * num_nodes
    for u, v in edges:          # u → v
        graph[u].append(v)
        indegree[v] += 1

    queue = deque([i for i in range(num_nodes) if indegree[i] == 0])
    order = []
    while queue:
        node = queue.popleft()
        order.append(node)
        for nei in graph[node]:
            indegree[nei] -= 1
            if indegree[nei] == 0:
                queue.append(nei)

    return order if len(order) == num_nodes else []   # empty → cycle detected
```

### Topological Sort (DFS)

```python
def topo_sort_dfs(num_nodes, edges):
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)

    WHITE, GRAY, BLACK = 0, 1, 2
    color = [WHITE] * num_nodes
    order = []
    has_cycle = False

    def dfs(node):
        nonlocal has_cycle
        color[node] = GRAY
        for nei in graph[node]:
            if color[nei] == GRAY:
                has_cycle = True
                return
            if color[nei] == WHITE:
                dfs(nei)
        color[node] = BLACK
        order.append(node)

    for i in range(num_nodes):
        if color[i] == WHITE:
            dfs(i)
    return order[::-1] if not has_cycle else []
```

### Dijkstra's Algorithm (Shortest Path, Non-Negative Weights)

```python
import heapq

def dijkstra(graph, start):
    # graph: {node: [(neighbor, weight), ...]}
    dist = {start: 0}
    heap = [(0, start)]
    while heap:
        d, u = heapq.heappop(heap)
        if d > dist.get(u, float('inf')):
            continue
        for v, w in graph[u]:
            nd = d + w
            if nd < dist.get(v, float('inf')):
                dist[v] = nd
                heapq.heappush(heap, (nd, v))
    return dist
```

### Bellman-Ford (Handles Negative Weights)

```python
def bellman_ford(n, edges, start):
    dist = [float('inf')] * n
    dist[start] = 0
    for _ in range(n - 1):
        for u, v, w in edges:
            if dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
    # Check for negative cycle
    for u, v, w in edges:
        if dist[u] + w < dist[v]:
            return None   # negative cycle
    return dist
```

### Union-Find (Disjoint Set Union)

```python
class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.rank = [0] * n
        self.components = n

    def find(self, x):                              # O(α(n)) ≈ O(1)
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])  # path compression
        return self.parent[x]

    def union(self, x, y):                          # O(α(n)) ≈ O(1)
        px, py = self.find(x), self.find(y)
        if px == py: return False
        if self.rank[px] < self.rank[py]:
            px, py = py, px
        self.parent[py] = px
        if self.rank[px] == self.rank[py]:
            self.rank[px] += 1
        self.components -= 1
        return True

    def connected(self, x, y):
        return self.find(x) == self.find(y)
```

---

## 11. Sorting Algorithms

### Built-in (Tim Sort — O(n log n))

```python
arr.sort()                              # in-place
arr.sort(key=lambda x: x[1])           # custom key
arr.sort(key=lambda x: (-x[0], x[1]))  # multi-key
sorted(arr)                             # returns new list
sorted(arr, key=lambda x: x.val)
```

### Merge Sort — O(n log n), O(n) space, **Stable**

```python
def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)

def merge(a, b):
    result = []
    i = j = 0
    while i < len(a) and j < len(b):
        if a[i] <= b[j]:
            result.append(a[i]); i += 1
        else:
            result.append(b[j]); j += 1
    result.extend(a[i:])
    result.extend(b[j:])
    return result
```

### Quick Sort — O(n log n) avg, O(n²) worst, O(log n) space

```python
import random

def quick_sort(arr, lo=0, hi=None):
    if hi is None: hi = len(arr) - 1
    if lo >= hi: return
    pivot_idx = partition(arr, lo, hi)
    quick_sort(arr, lo, pivot_idx - 1)
    quick_sort(arr, pivot_idx + 1, hi)

def partition(arr, lo, hi):
    pivot_i = random.randint(lo, hi)
    arr[pivot_i], arr[hi] = arr[hi], arr[pivot_i]
    pivot = arr[hi]
    i = lo
    for j in range(lo, hi):
        if arr[j] < pivot:
            arr[i], arr[j] = arr[j], arr[i]
            i += 1
    arr[i], arr[hi] = arr[hi], arr[i]
    return i
```

### Quick Select — O(n) avg (Kth Smallest)

```python
def quick_select(arr, k):
    """Returns k-th smallest element (0-indexed)."""
    pivot = random.choice(arr)
    left  = [x for x in arr if x < pivot]
    mid   = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    if k < len(left):
        return quick_select(left, k)
    elif k < len(left) + len(mid):
        return pivot
    else:
        return quick_select(right, k - len(left) - len(mid))
```

### Heap Sort — O(n log n), O(1) space

```python
def heap_sort(arr):
    heapq.heapify(arr)          # O(n)
    return [heapq.heappop(arr) for _ in range(len(arr))]
```

### Counting Sort — O(n + k), for integers in known range

```python
def counting_sort(arr):
    if not arr: return arr
    lo, hi = min(arr), max(arr)
    count = [0] * (hi - lo + 1)
    for x in arr:
        count[x - lo] += 1
    result = []
    for i, c in enumerate(count):
        result.extend([i + lo] * c)
    return result
```

### Bucket Sort — O(n) avg, for uniform distribution

```python
def bucket_sort(arr, bucket_count=10):
    if not arr: return arr
    lo, hi = min(arr), max(arr)
    if lo == hi: return arr[:]
    bucket_range = (hi - lo) / bucket_count
    buckets = [[] for _ in range(bucket_count)]
    for x in arr:
        idx = min(int((x - lo) / bucket_range), bucket_count - 1)
        buckets[idx].append(x)
    result = []
    for b in buckets:
        result.extend(sorted(b))
    return result
```

### Sorting Comparison Table

| Algorithm      | Best       | Average    | Worst      | Space  | Stable |
|:---------------|:-----------|:-----------|:-----------|:-------|:-------|
| Merge Sort     | O(n log n) | O(n log n) | O(n log n) | O(n)   | Yes    |
| Quick Sort     | O(n log n) | O(n log n) | O(n²)     | O(log n)| No    |
| Heap Sort      | O(n log n) | O(n log n) | O(n log n) | O(1)   | No     |
| Tim Sort (std) | O(n)       | O(n log n) | O(n log n) | O(n)   | Yes    |
| Counting Sort  | O(n+k)     | O(n+k)     | O(n+k)     | O(k)   | Yes    |
| Bucket Sort    | O(n+k)     | O(n+k)     | O(n²)     | O(n+k) | Yes    |

---

## 12. Binary Search

```python
# --- Standard: find exact target ---
def binary_search(arr, target):
    lo, hi = 0, len(arr) - 1
    while lo <= hi:
        mid = (lo + hi) // 2
        if arr[mid] == target:   return mid
        elif arr[mid] < target:  lo = mid + 1
        else:                    hi = mid - 1
    return -1

# --- Left bisect: first index where arr[i] >= target ---
def bisect_left(arr, target):
    lo, hi = 0, len(arr)
    while lo < hi:
        mid = (lo + hi) // 2
        if arr[mid] < target:  lo = mid + 1
        else:                  hi = mid
    return lo

# --- Right bisect: first index where arr[i] > target ---
def bisect_right(arr, target):
    lo, hi = 0, len(arr)
    while lo < hi:
        mid = (lo + hi) // 2
        if arr[mid] <= target: lo = mid + 1
        else:                  hi = mid
    return lo

# --- bisect module ---
import bisect
bisect.bisect_left(arr, target)
bisect.bisect_right(arr, target)
bisect.insort_left(arr, val)      # insert maintaining sorted order
bisect.insort_right(arr, val)

# --- Binary search on answer space ---
# "Find the minimum x such that condition(x) is True"
def binary_search_on_answer(lo, hi):
    while lo < hi:
        mid = (lo + hi) // 2
        if condition(mid):
            hi = mid
        else:
            lo = mid + 1
    return lo
```

---

## 13. Dynamic Programming

### Templates

```python
# --- 1D DP ---
# e.g., Climbing stairs
dp = [0] * (n + 1)
dp[0] = dp[1] = 1
for i in range(2, n + 1):
    dp[i] = dp[i-1] + dp[i-2]

# Space-optimized (when only last k states matter)
a, b = 1, 1
for _ in range(2, n + 1):
    a, b = b, a + b

# --- 2D DP ---
# e.g., Unique paths
dp = [[1] * cols for _ in range(rows)]
for r in range(1, rows):
    for c in range(1, cols):
        dp[r][c] = dp[r-1][c] + dp[r][c-1]

# --- Top-Down (Memoization) ---
from functools import lru_cache

@lru_cache(maxsize=None)
def solve(state):
    if base_case: return base_value
    return recurrence(solve(sub_states))
```

### Classic DP Problems

```python
# --- 0/1 Knapsack ---
def knapsack(weights, values, capacity):
    n = len(weights)
    dp = [0] * (capacity + 1)
    for i in range(n):
        for w in range(capacity, weights[i] - 1, -1):   # reverse!
            dp[w] = max(dp[w], dp[w - weights[i]] + values[i])
    return dp[capacity]

# --- Unbounded Knapsack (Coin Change — min coins) ---
def coin_change(coins, amount):
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0
    for a in range(1, amount + 1):
        for c in coins:
            if c <= a:
                dp[a] = min(dp[a], dp[a - c] + 1)
    return dp[amount] if dp[amount] != float('inf') else -1

# --- Longest Common Subsequence ---
def lcs(s1, s2):
    m, n = len(s1), len(s2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i-1] == s2[j-1]:
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    return dp[m][n]

# --- Longest Increasing Subsequence ---
# O(n log n) solution
def lis(nums):
    tails = []
    for num in nums:
        pos = bisect.bisect_left(tails, num)
        if pos == len(tails):
            tails.append(num)
        else:
            tails[pos] = num
    return len(tails)

# --- Edit Distance ---
def edit_distance(s1, s2):
    m, n = len(s1), len(s2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    for i in range(m + 1): dp[i][0] = i
    for j in range(n + 1): dp[0][j] = j
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i-1] == s2[j-1]:
                dp[i][j] = dp[i-1][j-1]
            else:
                dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
    return dp[m][n]

# --- House Robber ---
def rob(nums):
    if not nums: return 0
    prev2, prev1 = 0, 0
    for num in nums:
        prev2, prev1 = prev1, max(prev1, prev2 + num)
    return prev1
```

---

## 14. Backtracking

```python
# --- Template ---
def backtrack(candidates, path, result, start):
    if goal_reached(path):
        result.append(path[:])    # copy!
        return
    for i in range(start, len(candidates)):
        # optional: skip duplicates
        if i > start and candidates[i] == candidates[i-1]:
            continue
        path.append(candidates[i])
        backtrack(candidates, path, result, i + 1)  # i+1 to avoid reuse, i to allow reuse
        path.pop()                                   # undo choice

# --- Subsets ---
def subsets(nums):
    result = []
    def bt(start, path):
        result.append(path[:])
        for i in range(start, len(nums)):
            path.append(nums[i])
            bt(i + 1, path)
            path.pop()
    bt(0, [])
    return result

# --- Permutations ---
def permutations(nums):
    result = []
    def bt(path, used):
        if len(path) == len(nums):
            result.append(path[:])
            return
        for i in range(len(nums)):
            if used[i]: continue
            used[i] = True
            path.append(nums[i])
            bt(path, used)
            path.pop()
            used[i] = False
    bt([], [False] * len(nums))
    return result

# --- Combination Sum ---
def combination_sum(candidates, target):
    result = []
    candidates.sort()
    def bt(start, path, remaining):
        if remaining == 0:
            result.append(path[:])
            return
        for i in range(start, len(candidates)):
            if candidates[i] > remaining: break
            path.append(candidates[i])
            bt(i, path, remaining - candidates[i])  # i (not i+1) → reuse allowed
            path.pop()
    bt(0, [], target)
    return result

# --- N-Queens ---
def solve_n_queens(n):
    result = []
    cols = set()
    diag1 = set()   # row - col
    diag2 = set()   # row + col

    def bt(row, board):
        if row == n:
            result.append(["".join(r) for r in board])
            return
        for col in range(n):
            if col in cols or (row-col) in diag1 or (row+col) in diag2:
                continue
            cols.add(col); diag1.add(row-col); diag2.add(row+col)
            board[row][col] = 'Q'
            bt(row + 1, board)
            board[row][col] = '.'
            cols.remove(col); diag1.remove(row-col); diag2.remove(row+col)

    bt(0, [['.' for _ in range(n)] for _ in range(n)])
    return result
```

---

## 15. Intervals

```python
# --- Merge overlapping intervals ---
def merge_intervals(intervals):
    intervals.sort(key=lambda x: x[0])
    merged = [intervals[0]]
    for start, end in intervals[1:]:
        if start <= merged[-1][1]:
            merged[-1][1] = max(merged[-1][1], end)
        else:
            merged.append([start, end])
    return merged

# --- Insert interval ---
def insert(intervals, new):
    result = []
    for i, (s, e) in enumerate(intervals):
        if e < new[0]:
            result.append([s, e])
        elif s > new[1]:
            result.append(new)
            return result + intervals[i:]
        else:
            new = [min(s, new[0]), max(e, new[1])]
    result.append(new)
    return result

# --- Min meeting rooms (sweep line) ---
def min_meeting_rooms(intervals):
    events = []
    for s, e in intervals:
        events.append((s, 1))     # start
        events.append((e, -1))    # end
    events.sort()
    rooms = max_rooms = 0
    for _, delta in events:
        rooms += delta
        max_rooms = max(max_rooms, rooms)
    return max_rooms
```

---

## 16. Bit Manipulation

```python
# --- Basics ---
x & y          # AND
x | y          # OR
x ^ y          # XOR
~x             # NOT (bitwise complement)
x << n         # left shift  (multiply by 2^n)
x >> n         # right shift (divide by 2^n)

# --- Common Tricks ---
x & 1          # check if odd
x & (x - 1)   # clear lowest set bit
x & (-x)       # isolate lowest set bit
bin(x).count('1')  # count set bits (popcount)
x ^ x == 0    # any number XOR itself = 0
x ^ 0 == x    # any number XOR 0 = itself

# --- Single Number (all appear twice except one) ---
def single_number(nums):
    result = 0
    for num in nums:
        result ^= num
    return result

# --- Check power of 2 ---
def is_power_of_two(n):
    return n > 0 and (n & (n - 1)) == 0

# --- Get/Set/Clear bit at position i ---
get_bit    = (x >> i) & 1
set_bit    = x | (1 << i)
clear_bit  = x & ~(1 << i)
toggle_bit = x ^ (1 << i)
```

---

## 17. Math & Number Theory Essentials

```python
import math

math.gcd(a, b)                  # GCD
math.lcm(a, b)                  # LCM (Python 3.9+)
math.isqrt(n)                   # integer square root
math.comb(n, k)                 # C(n, k) combinations
math.factorial(n)               # n!
math.inf                        # infinity
math.ceil(x) / math.floor(x)

# --- Sieve of Eratosthenes ---
def sieve(n):
    is_prime = [True] * (n + 1)
    is_prime[0] = is_prime[1] = False
    for i in range(2, int(n**0.5) + 1):
        if is_prime[i]:
            for j in range(i*i, n + 1, i):
                is_prime[j] = False
    return [i for i in range(n + 1) if is_prime[i]]

# --- Fast exponentiation ---
pow(base, exp, mod)              # (base^exp) % mod in O(log exp)

# --- Integer division (ceiling) ---
ceil_div = (a + b - 1) // b     # or -(-a // b)
```

---

## 18. Common Python Imports & Tricks for Interviews

```python
from collections import defaultdict, Counter, deque, OrderedDict
from heapq import heappush, heappop, heapify, nlargest, nsmallest
from itertools import accumulate, combinations, permutations, product
from functools import lru_cache, reduce
from bisect import bisect_left, bisect_right, insort
from math import gcd, lcm, inf, isqrt, comb, ceil, floor, log2
from typing import List, Optional, Tuple
from sortedcontainers import SortedList   # pip install; not in stdlib

# --- SortedList (balanced BST alternative) ---
from sortedcontainers import SortedList
sl = SortedList()
sl.add(val)                 # O(log n)
sl.remove(val)              # O(log n)
sl.discard(val)             # O(log n), no error
sl[0]                       # min           O(1)
sl[-1]                      # max           O(1)
sl.bisect_left(val)         # lower bound   O(log n)
sl.bisect_right(val)        # upper bound   O(log n)

# --- Infinity ---
float('inf')
float('-inf')

# --- Useful one-liners ---
list(zip(*matrix))              # transpose matrix
matrix[::-1]                    # reverse rows (step 1 of 90° rotation)
[list(row) for row in zip(*matrix[::-1])]  # rotate 90° clockwise

# --- Default recursion limit ---
import sys
sys.setrecursionlimit(10**5)    # increase for deep recursion problems
```

---

## 19. Complexity Cheat Sheet

| n       | Max Viable Complexity | Typical Algorithms                  |
|:--------|:----------------------|:------------------------------------|
| ≤ 10    | O(n!)                 | Permutations, brute force           |
| ≤ 20    | O(2^n)                | Bitmask DP, subsets                 |
| ≤ 500   | O(n³)                 | Floyd-Warshall, 3D DP               |
| ≤ 5000  | O(n²)                 | Quadratic DP, brute-force pairs     |
| ≤ 10^6  | O(n log n)            | Sort, heap, divide & conquer        |
| ≤ 10^8  | O(n)                  | Linear scan, two pointers, sliding  |
| > 10^8  | O(log n) / O(1)       | Binary search, math, formula        |

---

## 20. Monotonic Stack & Monotonic Queue

### Monotonic Stack

```python
# Next Greater Element for each index
def next_greater(nums):
    n = len(nums)
    res = [-1] * n
    stack = []   # decreasing stack of indices
    for i in range(n):
        while stack and nums[stack[-1]] < nums[i]:
            res[stack.pop()] = nums[i]
        stack.append(i)
    return res

# Largest Rectangle in Histogram
def largest_rectangle(heights):
    stack = []   # increasing stack of indices
    max_area = 0
    heights.append(0)   # sentinel
    for i, h in enumerate(heights):
        while stack and heights[stack[-1]] > h:
            height = heights[stack.pop()]
            width = i if not stack else i - stack[-1] - 1
            max_area = max(max_area, height * width)
        stack.append(i)
    return max_area
```

### Monotonic Deque (Sliding Window Max)

```python
def max_sliding_window(nums, k):
    dq = deque()     # decreasing deque of indices
    result = []
    for i in range(len(nums)):
        while dq and dq[0] < i - k + 1:       # remove out-of-window
            dq.popleft()
        while dq and nums[dq[-1]] < nums[i]:   # maintain decreasing
            dq.pop()
        dq.append(i)
        if i >= k - 1:
            result.append(nums[dq[0]])
    return result
```

---

*Good luck with your interviews, Naman. Refer back to this often — pattern recognition is 80% of the battle.*
