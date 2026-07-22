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
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new  ArrayList<List<Integer>>();      
        Traverse(root, res,0);
        return res;
    }

    public static void Traverse(TreeNode root,  ArrayList<List<Integer>> res, int h)
    {

        if(root==null)return;
        if(h>=res.size())
        {
            res.add(new ArrayList<Integer>());
        }
        res.get(h).add(root.val);

        Traverse(root.left, res, h+1);
        Traverse(root.right, res, h+1);
 
    }
}