//gfg - https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1



//User function Template for Java

/* A Binary Tree node
class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}*/
class Tree
{
    //Function to return list containing elements of left view of binary tree.
    ArrayList<Integer> leftView(Node root)
    {
      // Your code here
      ArrayList<Integer> res = new ArrayList<>();
      Traverse(root, res, 0);
      return res;
      
    }
    
    public static void Traverse(Node root, ArrayList<Integer> res, int h)
    {
        if(root==null)return;
        
        if(h>=res.size())
        {
            res.add(root.data);
        }
        Traverse(root.left, res, h+1);
        Traverse(root.right, res, h+1);
        
    }
    
    
}