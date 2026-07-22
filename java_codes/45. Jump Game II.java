class Solution {
    public int jump(int[] nums) {
        int curEnd = 0;
        int curFarthest = 0;
        int jumps = 0;

        for(int i=0;i<nums.length-1;i++)
        {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;

    }
}