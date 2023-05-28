//optimal solution

class Solution {
    public int diagonalSum(int[][] mat) {
        
        int sum =0;
        for(int i=0;i<mat.length;i++)
        {
            System.out.println(mat[i][i]);
            sum = sum+ mat[i][i];
        }
        int i=0;
        for(int j=mat.length-1;j>=0;j--)
        {
            
            System.out.println(mat[i][j]);
            sum = sum+mat[i][j];
            i++;
        }

        int mid = (mat.length-1) / 2;
        if(mat.length==1)
        {
            mid = 0;
        }
        
        System.out.println("remove" + mat[mid][mid]);
        if(mat.length%2!=0)
            sum = sum-mat[mid][mid];
        return sum;


    }
}