//optimal solution - O(n)

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
    int max ;
    public int diameterOfBinaryTree(TreeNode root) {
        
        max=0;
        findheight(root);
        return max;

    }

    public int findheight(TreeNode root)
    {
        if(root==null)
            return 0;
        
        int left = findheight(root.left);
        int right = findheight(root.right);

        max = Math.max(max, left+right);

        return 1+Math.max(left,right);
    } 
}