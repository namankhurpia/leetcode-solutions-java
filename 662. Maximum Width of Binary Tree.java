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
    
    public int widthOfBinaryTree(TreeNode root) {
        
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();
        
        return Traverse(root,0,1,start,end);
        

    }

    public int Traverse(TreeNode root, int level, int count, List<Integer> start, List<Integer> end)
    {
        if(root==null)return 0;

        if(start.size()==level)
        {
            start.add(count);
            end.add(count);
        }
        else
        {
            end.set(level,count);
        }

        int cur = end.get(level)-start.get(level)+1;
        int left = Traverse(root.left, level+1, 2*count, start, end);
        int right = Traverse(root.right, level+1, 2*count+1, start, end);
        return Math.max(cur, Math.max(left, right));

        
    }
}