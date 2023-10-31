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
    TreeNode ref = null;

    public TreeNode searchBST(TreeNode root, int val) {
        
        
        if(root==null)return null;
        
           
            if(val == root.val)
            {
                return root;
            }
            
            
             if(root.val> val )
             {
                 return searchBST(root.left, val);
             }
             else
             {
                 return searchBST(root.right, val);
             }

    
        
    }

 
}