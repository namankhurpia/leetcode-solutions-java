class main{

    public static int [][] solution (int [][]mat1, int [][] mat2)
    {
        int m = mat1.length, n = mat2[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < mat2.length; ++k) {
                    res[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }


        //printing the result
        for(int i=0;i<res.length;i++)
        {
            for(int j=0;j<res[0].length;j++)
            {
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
        return res;

    }
    
    public static void main(String[] args) {
        
        //Testcase 1
        int[][] mat1 = {
            {1, 0, 0},
            {0, 0, 0},
            {0, 0, 1}
        };
        
        int[][] mat2 = {
            {1, 0, 0},
            {0, 0, 0},
            {0, 0, 1}
        };
        solution(mat1,mat2);

        //Testcase 2
        int[][] mat11 = {
            {1, 0, 0, 0, 2},
            {0, 0, 0, 0, 0},
            {0, 0, 3, 0, 0},
            {0, 4, 0, 0, 0},
            {5, 0, 0, 0, 0}
        };
        
        int[][] mat22 = {
            {0, 0, 0, 6, 0},
            {0, 0, 0, 0, 0},
            {7, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 8, 0, 0, 0}
        };
        solution(mat11, mat22);


    }
}