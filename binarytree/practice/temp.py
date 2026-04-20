

"""
tree -
     1
   2    3 
 4  5  6

"""

class Node:
    def __init__(self, val =0 , left = None, right = None):
        self.val = val
        self.left = left
        self.right = right

    def makeTree():
        A = Node(1)
        B = Node(2)
        C = Node(3)
        D = Node(4)
        E = Node(5)
        F = Node(6)
        G = Node(7)
        A.left =B
        A.right = C
        B.left = D
        B.right = E
        C.left = F
        C.right = G

    def bfs():
        pass

    def dfs():
        pass

    def 