//dp  - memoized

class Solution {
    int [] dp;
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        dp = new int[n];
        
        return Math.min(jump(cost,n-1), jump(cost,n-2));

    }

    public  int jump(int cost[] , int n)
    {
        if(n<0)
        {
            return 0;
        }
            
        if(n==0|| n==1)
        {
            return cost[n];
        }

        if(dp[n]!=0)
        {
            return dp[n];
        }
        else
        {
            dp[n] = cost[n] + Math.min(jump(cost, n-1),jump(cost, n-2)); 
            return dp[n];
        }
        
    }
}