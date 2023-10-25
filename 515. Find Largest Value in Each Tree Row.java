//O(n)

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
    public List<Integer> largestValues(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        Traverse(root, res, 0);
        return res;
        
    }

    public static void Traverse(TreeNode root, ArrayList<Integer> res, int d)
    {
        if(root==null)
        {
            return;
        }

        if(res.size()==d)
        {
            res.add(root.val);
        }
        else
        {
            res.set( d , Math.max(res.get(d), root.val));
        }
    

        
        
            Traverse(root.left, res, d+1);
        
        
            Traverse(root.right, res, d+1);
        



    }
}