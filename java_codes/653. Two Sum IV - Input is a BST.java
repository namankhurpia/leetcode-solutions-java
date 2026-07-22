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
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<Integer>();
        return Traverse(root, set, k);
    }

    public boolean Traverse(TreeNode root,HashSet<Integer> set, int k)
    {
        if(root==null)return false;
        if(set.contains(k-root.val)) return true;
        set.add(root.val);
        return (Traverse(root.left,set,k) || Traverse(root.right,set,k));

    }
}

//2 pointer approach

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
    public boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Traverse(root, arr, k);

        //now using 2 pointer approach
        for(int i=0,j=arr.size()-1; i<j;)
        {
            if((arr.get(i)+arr.get(j))==k)return true;
            if(arr.get(i)+arr.get(j)<k)
                i++;
            else
                j--;
        }

        return false;
    }

    public void Traverse(TreeNode root,ArrayList<Integer> arr, int k)
    {
        if(root==null)return;
        
        Traverse(root.left,arr,k);
        arr.add(root.val);
        Traverse(root.right,arr,k);

    }
}