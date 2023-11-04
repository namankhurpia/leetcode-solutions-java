//https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1

class Solution {
    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        
        boolean[] vis = new boolean[V];
        ArrayList<Integer> res = new ArrayList<Integer>();
        dfs(0,vis,res,adj);
        return res;
        
       
        
    }
    
    public void dfs(int ch,boolean [] vis, ArrayList<Integer> res, ArrayList<ArrayList<Integer>> adj)
    {
        vis[ch] = true;
        res.add(ch);
        
        for(int i=0;i<adj.get(ch).size();i++)
            if(!vis[adj.get(ch).get(i)])
                dfs(adj.get(ch).get(i),vis,res,adj);
        
    }
}