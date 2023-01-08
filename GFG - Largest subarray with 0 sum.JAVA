
class GfG
{
    int maxLen(int arr[], int n)
    {
        // Your code here
        
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        
        int sum =0;
        int res = 0;
        for(int i=0;i<arr.length;i++)
        {
            sum+=arr[i];
            if(sum==0)
            {
                res = i+1;
            }
            else
            {
                if(map.containsKey(sum))
                {
                    res = Math.max(res, i-map.get(sum));
                }
                else
                {
                    map.put(sum,i);
                    
                }
            }
            
        }
        //System.out.println(map);
        return res;
        
        
    }
}