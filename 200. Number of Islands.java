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

    //DFS
    //O(m x n)
    // this code will change the value of the grid to 0 if it is 1 and will also change the value of the neighbouring 1's to 0

    public static void recrusive_dfs(char[][] grid , int row, int column)
    {
        //base case - this will be called when the row or column is out of bounds or the value is 0
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