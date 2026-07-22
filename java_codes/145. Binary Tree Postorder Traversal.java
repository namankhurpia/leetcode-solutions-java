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
    public List<Integer> postorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();
        TraversePost(root, res);
        return res;
        
    }

    public static void TraversePost(TreeNode node, ArrayList<Integer> res)
    {
        if(node==null)return;
        
        TraversePost(node.left, res);
        TraversePost(node.right, res);
        res.add(node.val);
    }
}