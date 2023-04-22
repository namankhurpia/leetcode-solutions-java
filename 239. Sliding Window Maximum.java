//BRUTE FORCE APPROACH - O(k * n-k)

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        if(n == 0 || k==0)
            return new int[0];
        if(k==1)
            return nums;
        int res []= new int[n - k +1];
        for(int i=0;i<n-k+1;i++)
        {
            int max = Integer.MIN_VALUE;
            for(int j=i;j<i+k;j++)
            {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        } 
        return res;

    }
}