"""
# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""

from typing import Optional
class Solution:
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        if not node:
            return None

        clone = {}

        def dfs(curr):
            if curr in clone:
                return clone[curr]
            
            copy = Node(curr.val)
            clone[curr] = copy
            
            for nbs in curr.neighbors:
                copy.neighbors.append(dfs(nbs))
            
            return copy

        return dfs(node)

    


"""
Core idea: use a map “original node → cloned node”

As soon as you create a clone of a node, store it in a hashmap/dict:

key: original node object

value: cloned node object

Then:

If you ever see the same original node again (cycle), you reuse the clone from the map instead of re-creating it.

What “deep copy” means here

Deep copy means:

new Node objects

new neighbors lists

structure preserved (same connections), but no clone node is the same object as original

Why you need a visited/map

Example cycle: 1 -- 2 -- 3 -- 1
If you DFS from 1:

clone 1

clone neighbor 2

clone neighbor 3

neighbor of 3 is 1 again → you must not clone 1 again, you must reuse the already-cloned node.

That’s exactly what the hashmap handles.

DFS solution (recursive) — easiest to understand
Python code (standard LeetCode Node definition)


"""