class Solution {
    public int findDuplicate(int[] nums) {
        
        //BRUTE FORCE - uses Arrays.sort
        /*
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i]==nums[i+1])
            {
                return nums[i];
            }
        }
        return -1;
        */

        

        //OPTIMAL - NEGATIVE NUMBERS
        int dup  = 0;
        for(int i=0;i<nums.length;i++)
        {
            int cur = Math.abs(nums[i]);
            if(nums[cur]<0)
            {
                dup = cur;
            }
            else
            {
                nums[cur] = -nums[cur];
            }
            
        }

        
        return dup;
        
    }
}


//optimal approach        
        while (nums[0] != nums[nums[0]]) {
            int nxt = nums[nums[0]];
            nums[nums[0]] = nums[0];
            nums[0] = nxt;
        }
        return nums[0];
    