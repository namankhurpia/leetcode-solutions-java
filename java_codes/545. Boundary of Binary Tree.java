//lintcode - 878 · Boundary of Binary Tree

//https://www.lintcode.com/problem/878/description

//o(3n)

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: a TreeNode
     * @return: a list of integer
     */
     ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        ArrayList<Integer> leaf = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        // write your code here

        if(root==null)
        {
            return left;
        }
        else
        {
            left.add(root.val);
        }

        doleft(root, left, right, leaf);
        domid(root, left, right, leaf);
        doright(root, left, right, leaf);
        left.addAll(leaf);
        Collections.reverse(right);
        left.addAll(right);
        return left;

    }

    public static void doleft(TreeNode root, ArrayList<Integer> left,ArrayList<Integer> right, ArrayList<Integer> leaf)
    {
        TreeNode curr = root.left;
        while(curr!=null && (curr.left!=null || curr.right!=null))
        {
            left.add(curr.val);
            if(curr.left==null)
            {
                curr = curr.right;
            }
            else 
            {
                curr = curr.left;
            }
        }


    }

    public static void domid(TreeNode root, ArrayList<Integer> left,ArrayList<Integer> right, ArrayList<Integer> leaf)
    {
        if(root==null)
        {
            return;
        }
        if(root.left==null && root.right==null)
        {
            leaf.add(root.val);
        }
        domid(root.left, left, right, leaf);
        domid(root.right,left, right, leaf);
   
    }

    public static void doright(TreeNode root, ArrayList<Integer> left,ArrayList<Integer> right, ArrayList<Integer> leaf)
    {
        TreeNode curr = root.right;
        while(curr!=null && (curr.left!=null || curr.right!=null))
        {
            right.add(curr.val);
            if(curr.right==null)
            {
                curr = curr.left;
            }
            else 
            {
                curr = curr.right;
            }
        }


    }

}