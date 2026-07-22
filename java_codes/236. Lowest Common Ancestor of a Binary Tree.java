//O(n)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
       recursion(root, p,q);
       return ans;

    }

    public static boolean recursion(TreeNode current, TreeNode p, TreeNode q)
    {
        if(current==null)
        {
            return false;
        }
        int left = recursion(current.left,p,q)?1:0;
        int right = recursion(current.right,p,q)?1:0;
        int mid = (current==p || current==q)?1:0;
        if(mid+left+right>=2)
        {
            ans = current;
        }

        if(mid+left+right>0)
            return true;
        else
            return false;
    }

    static TreeNode ans;

}


//ALTERNATE SOLUTION - O(n)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        if(root==null || root == p || root ==q)
        {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if(left==null)
        {
            return right;
        }
        else if(right==null)
        {
            return left;
        }
        else
        {
            return root;
        }

    }
}