//same is Unique Paths I, but with obstacles

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int dp [] [] = new int[m][n];

        //initialize first row
        for(int i=0;i<m;i++)
        {
            if(obstacleGrid[i][0]==1)
            {
                dp[i][0] = 0;
                break;
            }
            else
            {
                dp[i][0] = 1;
            }
        }

        //initialize first column
        for(int i=0;i<n;i++)
        {
            if(obstacleGrid[0][i]==1)
            {
                dp[0][i] = 0;
                break;
            }
            else
            {
                dp[0][i] = 1;
            }
        }


        //now fill the dp array

        for(int i=1;i<m;i++)
        {
            for(int j=1;j<n;j++)
            {
                if (obstacleGrid[i][j] == 1) 
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];

    }
}