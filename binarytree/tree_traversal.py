
"""
tree -
     1
   2    3 
 4  5  6

"""

class TreeNode:
    def __init__(self, val = 0, left = None, right = None):
        self.val = val 
        self.left = left
        self.right = right
    
    def __str__(self):
        return str(self.val)

def makeTree():
    A = TreeNode(1)
    B = TreeNode(2)
    C = TreeNode(3)
    D = TreeNode(4)
    E = TreeNode(5)
    F = TreeNode(6)
    A.left = B
    A.right = C
    B.left = D
    B.right = E 
    C.left = F
    return A


def preorder_dfs(root):
    if root == None:
        return 
    
    print(root)
    preorder_dfs(root.left)
    preorder_dfs(root.right)

def inorder_dfs(root):
    if not root:
        return 
    
    inorder_dfs(root.left)
    print(root)
    inorder_dfs(root.right)


def postorder_dfs(root):
    if not root:
        return
    
    postorder_dfs(root.left)
    postorder_dfs(root.right)
    print(root)
    
    


A = makeTree()
print("printing pre order")
preorder_dfs(A)
print("printing in order")
inorder_dfs(A)
print("printing post order")
postorder_dfs(A)