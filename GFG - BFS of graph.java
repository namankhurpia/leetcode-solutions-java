//https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1

//{ Driver Code Starts
// Initial Template for Java
import java.util.*;


class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        
        boolean visited[]=new boolean[V];
        int s = 0;
        visited[s] = true;
        ArrayList<Integer> res = new ArrayList<Integer>();
        LinkedList<Integer> queue =new LinkedList<Integer>();
        queue.add(s);
        
        while(queue.size()!=0)
        {
            s = queue.poll();
            res.add(s);
            Iterator<Integer> i = adj.get(s).listIterator();
            while(i.hasNext())
            {
                int n = i.next();
                if(!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
       
        
        return res;
    }
}