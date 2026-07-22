class Solution {
    public int maxSubArray(int[] nums) {
        
        /*
        int sum = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++)
        {
            int subarray = 0;
            for(int j=i;j<nums.length;j++)
            {
                subarray = subarray + nums[j];
                sum = Math.max(subarray,sum);
            }
        }
        return sum;
        */

        //kdanes method
        int sum = nums[0];
        int maxval = nums[0];
        for(int i=1;i<nums.length;i++)
        {
            sum = Math.max( sum + nums[i], nums[i]);
            maxval = Math.max(sum,maxval);
            
        }
        return maxval;

    }
}