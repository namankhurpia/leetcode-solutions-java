//BRUTE FORCE APPROACH - BEATS 80% in runtime - O(nlogn)
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet set = new HashSet<Integer>();
        for(int i:nums)
            set.add(i);

        if(nums.length==0)
        {
            return 0;
        }

        ArrayList<Integer> arr = new ArrayList<>(set);
        Collections.sort(arr);
        
        int cur=1,max = 1;
        for(int i=0;i<arr.size()-1;i++)
        {
            if(arr.get(i)+1==arr.get(i+1))
            {
                cur = cur +1;              
            }
            else
            {
                cur = 1;
            }

            if(cur>max)
            {
                max = cur;
            }
        }
        return max;

    }
}


//OPTIMAL - O(n)

//intuition - https://www.youtube.com/watch?v=oO5uLE7EUlM&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=29
// add all numbers to hashset to avoid all duplicates, now we will check for streak. 
//current streak will be your current pointers streak and max streak will be maximum streak yet,
// loop over your set, and on a while loop check if next bigger number exists and increase your current streak
// if not then you can try finding another starting point and make your current streak 1.

class Solution {
    public int longestConsecutive(int[] nums) {
        
        if(nums.length==0)
            return 0;

        HashSet<Integer> set = new HashSet<Integer>();
        for(int i:nums)
            set.add(i);

        int maxstreak =0;
        int currentstreak =1;
        for(int i: set)
        {
            if(! set.contains(i-1))
            {
                int cur =i;
                currentstreak = 1;
                while(set.contains(cur+1))
                {
                    cur=cur+1;
                    currentstreak = currentstreak+1;
                    
                }
                
                maxstreak = Math.max(currentstreak, maxstreak);
                
            }
            
        }
    
        return maxstreak;
        
        
    }
}



