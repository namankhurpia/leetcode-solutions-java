class Solution {
    public double knightProbability(int n, int k, int row, int column) {

        double curr[][] = new double[n][n];
        double next[][] = new double[n][n];

        curr[row][column] = 1;

        for(int m=0;m<k;m++)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(curr[i][j]!=0)
                    {

                        int ni=0;
                        int nj = 0;

                        ni = i + 1;
                        nj = j + 2;
                        if(isValid(n,ni,nj))
                        {
                            next[ni][nj] += curr[i][j] /8.0;
                        }

                        ni = i - 1;
                        nj = j + 2;
                        if(isValid(n,ni,nj))
                        {
                            next[ni][nj] += curr[i][j] /8.0;
                        }

                        ni = i + 1;
                        nj = j - 2;
                        if(isValid(n,ni,nj))
                        {
                            next[ni][nj] += curr[i][j] /8.0;
                        }

                        ni = i - 1;
                        nj = j - 2;
                        if(isValid(n,ni,nj))
                        {
                            next[ni][nj] += curr[i][j] /8.0;
                        }

                        ni = i + 2;
                        nj = j + 1;
                        if(isValid(n,ni,nj))
                        {
                            next[ni][nj] += curr[i][j] /8.0;
                        }

                        ni = i - 2;
                        nj = j + 1;
                        if(isValid(n,ni,nj))
                        {
                            next[ni][nj] += curr[i][j] /8.0;
                        }

                        ni = i + 2;
                        nj = j - 1;
                        if(isValid(n,ni,nj))
                        {
                            next[ni][nj] += curr[i][j] /8.0;
                        }

                        ni = i - 2;
                        nj = j - 1;
                        if(isValid(n,ni,nj))
                        {
                            next[ni][nj] += curr[i][j] /8.0;
                        }


                    }
                    

                }
            }

            curr = next;
            next = new double[n][n];
        }

        double sum = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                sum += curr[i][j];
            }
        }
        return sum;
    }


        public static boolean isValid(int n, int ni, int nj)
        {
            if(ni<n && nj<n && ni>=0 && nj>=0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
    
}