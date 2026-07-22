//binary saerch - log(n)

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m*n-1;
        
        int ele = 0, num =0;

       while(left<=right)
       {
            ele = (left + right)/2;
            num = matrix[ele/n][ele%n];
            if(target == num)
            {
                return true;
            }
            else
            {
                if(target<num)
                {
                    right = ele -1;
                }
                else
                {
                    left = ele+1;
                }
            }
       }
       return false;

    }

   

}