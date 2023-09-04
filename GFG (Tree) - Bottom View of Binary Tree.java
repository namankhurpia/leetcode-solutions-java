//link - https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1

class Solution
{
    //Function to return a list containing the bottom view of the given tree.
    public ArrayList <Integer> bottomView(Node root)
    {
        // Code here
        ArrayList<Integer> arr = new ArrayList<Integer>();
        if(root == null)
            return arr;
        
        TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
        LinkedList<Node> q = new LinkedList<Node>();
        root.hd =0;
        q.add(root);
        while(!q.isEmpty())
        {
            Node temp = q.remove();
            int hd = temp.hd;
            map.put(hd,temp.data);
            
            if(temp.left!=null)
            {
                temp.left.hd = hd-1;
                q.add(temp.left);
            }
            if(temp.right!=null)
            {
                temp.right.hd = hd+1;
                q.add(temp.right);
            }
            
            
        }
        
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            arr.add(entry.getValue()); 
        }
        return arr; 
        
        
    }
}