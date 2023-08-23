//User function Template for Java//User function Template for Java

import java.util.ArrayList;

class Solution{
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        // code here
        ArrayList<Integer> subsetsum = new ArrayList<>();
        return recursiveCall(arr,0,N-1,0);

    }
    
    public static void recursiveCall(ArrayList<Integer> arr, int l, int r, int sum)
    {
        
        
    }



}