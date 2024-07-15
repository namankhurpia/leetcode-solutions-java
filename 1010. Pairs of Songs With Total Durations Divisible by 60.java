// two pointer approach 
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        
        HashMap<Integer,Integer> map = new HashMap<>();
       
        int ans = 0;
        for(int i=0;i<time.length;i++)
        {
            int temp =time[i]%60;
            int res=0;
            if(temp!=0)
            {
                res = (60 - temp);
            }
            else
            {
                res = 0;
            }
            ans =ans + map.getOrDefault(res%60, 0);
            map.put(time[i]%60, map.getOrDefault(time[i]%60, 0)+1);
            
        }
        return ans;

    }
}