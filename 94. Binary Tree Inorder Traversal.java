//OPTIMISED SOLUTION

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
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root !=null || !stack.isEmpty())
        {
            while(root!=null)
            {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;

        
    }
}

//using recursion
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
    

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res= new ArrayList<>();
        TraversInorder(root, res);
        return res;
    }

    public static void TraversInorder(TreeNode root, ArrayList<Integer> res)
    {
        if(root==null)return;
        TraversInorder(root.left, res);
        res.add(root.val);
        TraversInorder(root.right, res); 
    }

}