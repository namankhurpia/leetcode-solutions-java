//User function Template for Java//User function Template for Java
class Solution{
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        // code here
        return recursiveCall(arr,0,N-1,0);

    }
    
    public static ArrayList<Integer> recursiveCall(ArrayList<Integer> arr, int l, int r, int sum)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        if(l>r)
        {
            //System.out.println(sum);
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(sum);
            return temp;
        }
        list.addAll(recursiveCall(arr,l+1,r,arr.get(l)+sum));
        list.addAll(recursiveCall(arr,l+1,r,sum));
        
        return list;
        
    }


    
}