//o(n)

class Solution {
    public int singleNonDuplicate(int[] nums) {
        if(nums.length==1)
        {
            return nums[0];
        }
        
        for(int i=0;i<nums.length;i++)
        {
            if(i==nums.length-1)
            {
                return nums[nums.length-1];
            }
            if(nums[i]==nums[i+1])
            {
                i++;
            }
            else
            {
                return nums[i];
            }
        }
        return -1;

    }
}

//O(logn)
