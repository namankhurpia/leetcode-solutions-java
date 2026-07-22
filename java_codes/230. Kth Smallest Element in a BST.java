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

 //do inorder traversal and return the kth item
class Solution {
    int sol = 0;
    int c = 0;
    public int kthSmallest(TreeNode root, int k) {
         TraversePreOrder(root,k);
         return sol;
    }

    public void TraversePreOrder(TreeNode root,int k)
    {
        
        
        if(root==null)return;

        
        TraversePreOrder(root.left,k);
        c+=1;
        if(k==c)
        {
            sol= root.val;
            return;
        }

        TraversePreOrder(root.right,k);
        
    }
}