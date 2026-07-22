//https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1

class Solution
{
    //Function to return a list of nodes visible from the top view 
    //from left to right in Binary Tree.
    static ArrayList<Integer> topView(Node root)
    {
        // add your code
        HashMap<Integer,Pair> visited = new HashMap<Integer,Pair>();
        
        Traverse(root, 0, 0, visited);
        
        ArrayList<Integer> res = new ArrayList<>();
        for(int i:visited.keySet())
        {
            res.add(i);
        }
        
        Collections.sort(res);
        
        ArrayList<Integer> res2 = new ArrayList<>();
        for(int i:res)
        {
            res2.add((int) visited.get(i).data);
        }
        
        
        
        
        return res2;
        
    }
    
    public static void Traverse(Node root, int distance , int level, HashMap<Integer,Pair> visited)
    {
        if(root==null)return;
        
        if( (!visited.containsKey(distance)) || visited.get(distance).level> level)
        {
            visited.put(distance, new Pair(root.data, level));
        }
        
        Traverse(root.left,distance-1, level+1, visited);
        Traverse(root.right,distance+1, level+1, visited);
        
        
        
    }
    
}

class Pair
{
    int data;
    int level;
    
    Pair(){
        
    }
    
    Pair(int data, int level)
    {
        this.data = data;
        this.level = level;
    }
}