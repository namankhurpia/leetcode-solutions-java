//OPTIMAL SOLUTION

class Solution {
    public int[] runningSum(int[] nums) {
        int cur =0;
        for(int i=0;i<nums.length;i++)
        {
            cur = cur + nums[i];
            nums[i] = cur;
        }
        return nums;
    }
}