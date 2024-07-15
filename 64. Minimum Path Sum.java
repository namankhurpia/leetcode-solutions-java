class Solution {
    public int minPathSum(int[][] grid) {
        int h = grid.length;
        int w = grid[0].length;
        int dp[][] = new int[h][w];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return min(grid,0,0,dp);
    }

        public static int min(int[][] grid, int curi, int curj, int[][] dp) {
        if (curi == grid.length - 1 && curj == grid[0].length - 1) {
            return grid[curi][curj];
        }
        if (curi >= grid.length || curj >= grid[0].length) {
            return Integer.MAX_VALUE;
        }

        if (dp[curi][curj] != -1) {
            return dp[curi][curj];
        }

        int right = min(grid, curi, curj + 1, dp);
        int down = min(grid, curi + 1, curj, dp);

        dp[curi][curj] = grid[curi][curj] + Math.min(right, down);
        return dp[curi][curj];
    }
}