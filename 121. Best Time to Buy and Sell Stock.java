class Solution {
    public int maxProfit(int[] prices) {
        
        //brute force approach - o(n^2)
        /*
        int max =0;
        for(int i=0;i<prices.length-1;i++)
        {
            for(int j=i+1;j<prices.length;j++)
            {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;

        */

        //single pass approach
        // logic - Need to find the maximum difference between the prices[i] and prices[j] where i<j
        int lowest_yet = Integer.MAX_VALUE;
        int highest_yet = 0;
        int maxdiff = 0;
        for(int i=0;i<prices.length;i++)
        {
            if(prices[i]<lowest_yet)
            {
                lowest_yet = Math.min(lowest_yet, prices[i]); 
            }
            else 
            {
                
                maxdiff = Math.max(maxdiff, prices[i]-lowest_yet);
            }
        

        }
        return maxdiff;

    }

    

}