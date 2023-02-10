//OPTIMISED SOLUTION - O(m x n)

class Solution {
    public int numIslands(char[][] grid) {
        
        if(grid.length==0 || grid[0].length==0)
        {
            return 0;
        }

        int res =0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]=='1')
                {
                    res = res+1;
                    recrusive_dfs(grid, i,j);
                }
                
            }
        }
        return res;

    }

    public static void recrusive_dfs(char[][] grid , int row, int column)
    {
        if(row<0 || column<0 || row>=grid.length || column>= grid[0].length || grid[row][column]=='0')
        {
            return;
        }

        grid[row][column] = '0';
        recrusive_dfs(grid, row-1, column);
        recrusive_dfs(grid, row, column-1);
        recrusive_dfs(grid, row+1, column);
        recrusive_dfs(grid, row, column+1);
    }
}