//BINARY SEARCH APPROACH - OPTIMAL

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        if(matrix == null || matrix[0].length==0)
        {
            return false;
        }

        int shortest = Math.min(matrix[0].length, matrix.length);
        for(int i=0;i<shortest;i++)
        {
            boolean verticalfound = binarySearch(matrix, target, i, true);
            boolean horizontalfound = binarySearch(matrix, target, i, false);
            if(verticalfound||horizontalfound)
            {
                return true;
            }
        }
        return false;

    }

    public static boolean binarySearch(int [][]matrix, int target, int start, boolean vertical)
    {
        int low = start;
        int high =0;
        if(vertical)
        {
            high = matrix[0].length-1;
        }
        else
        {
            high = matrix.length-1;
        }

        while(low <= high)
        {
            int mid = (low+high)/2; 
            if(vertical)
            {
                System.out.println("VERTICAL"+matrix[start][mid]);
                //search vertical
                if(matrix[start][mid]<target)
                {
                    low = mid+1;
                }
                else if(matrix[start][mid]>target)
                {
                    high = mid-1;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                System.out.println("HORIZONTAL"+matrix[mid][start]);
                //search horizontal
                if(matrix[mid][start]<target)
                {
                    low = mid+1;
                }
                else if(matrix[mid][start]>target)
                {
                    high = mid-1;
                }
                else
                {
                    return true;
                }
            }
        }
        return false;

    }

}