//https://www.youtube.com/watch?v=jKF9AcyBZ6E - java
//https://www.youtube.com/watch?v=aYqYMIqZx5s - neetcode - python

//O(n)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        int res = Integer.MAX_VALUE;

        int cont_sum = 0;
        int left = 0;

        for(int i=0;i<nums.length;i++)
        {
            cont_sum += nums[i];

            while(cont_sum>=target)
            {
                res = Math.min(res, i+1 -left);
                cont_sum -=nums[left];
                left++;
            }
        }

        return (res != Integer.MAX_VALUE)? res:0;


    }
}