//OPTIMISED SOLUTION AND BRUTE FORCE  - only one approach
class Solution {
    public int minimumRounds(int[] tasks) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i:tasks)
        {
            map.put(i,map.getOrDefault(i,0)+1);
        }

        int count = 0;
        for(Map.Entry<Integer, Integer> mv : map.entrySet())
        {
            int val = mv.getValue();
            if(val==1)
            {
                return -1;
            }
            if(val%3==0 )
            {  
                count += val/3;
            }
            else
            {
                count += (val/3) +1;
            }

        }

       return count; 
    }
    
}