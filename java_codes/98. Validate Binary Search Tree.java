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
    public boolean isValidBST(TreeNode root) {
        return Traverse(root,Long.MAX_VALUE, Long.MIN_VALUE);
        
    }

    public  boolean Traverse(TreeNode root, long max, long min)
    {
        if(root==null) return true;

        if(root.val >= max || root.val <= min)return false;
        
        return (Traverse(root.left,root.val,min) && Traverse(root.right,max, root.val ) );
       
    }
}