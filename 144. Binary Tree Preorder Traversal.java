/**
 * Definition for a binary tree node. 
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        TraversePreorder(root,res);
        return res;
    }

    public static void TraversePreorder(TreeNode root, ArrayList<Integer> res)
    {
        if(root==null) return;
        res.add(root.val);
        TraversePreorder(root.left, res);
        TraversePreorder(root.right, res);
    }
}



//using iterative method

public List<Integer> preorderTraversal(TreeNode root) {

    ArrayList<Integer> res = new ArrayList<Integer>();

    if(root==null)
        return res;

    Stack<TreeNode> s = new Stack<>();
    s.push(root);

    while(!s.isEmpty())
    {
        TreeNode node = s.peek();
        res.add(node.val);
        s.pop();

        
        if(node.right!=null)
        {
            s.push(node.right);
        }
        if(node.left!=null)
        {
            s.push(node.left);
        }
        
    }

    return res;
}