//BRUTE FORCE METHOD - only solution available

class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        HashSet<Character> set = new HashSet<Character>();
        
        for(int i=0;i<9;i++)
        {
            set.clear();
            for(int j=0;j<9;j++)
            {
                if(board[i][j] !='.' && set.contains(board[i][j]))
                {
                    
                    return false;
                }
                else
                {
                    set.add(board[i][j]);
                }
            }
            
        }

        for(int i=0;i<9;i++)
        {
            set.clear();
            for(int j=0;j<9;j++)
            {
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
            }
            
        }

        set.clear();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
                

            }
        }

        set.clear();
        for(int i=0;i<3;i++)
        {
            for(int j=3;j<6;j++)
            {
                
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
                

            }
        }

        set.clear();
        for(int i=0;i<3;i++)
        {
            for(int j=6;j<9;j++)
            {
                
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
                

            }
        }


        set.clear();
        for(int i=3;i<6;i++)
        {
            for(int j=0;j<3;j++)
            {
                
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
                

            }
        }

        set.clear();
        for(int i=3;i<6;i++)
        {
            for(int j=3;j<6;j++)
            {
                
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
                

            }
        }

        set.clear();
        for(int i=3;i<6;i++)
        {
            for(int j=6;j<9;j++)
            {
                
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
                

            }
        }


        set.clear();
        for(int i=6;i<9;i++)
        {
            for(int j=0;j<3;j++)
            {
                
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
                

            }
        }

        set.clear();
        for(int i=6;i<9;i++)
        {
            for(int j=3;j<6;j++)
            {
                
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
                

            }
        }

        set.clear();
        for(int i=6;i<9;i++)
        {
            for(int j=6;j<9;j++)
            {
                
                if(board[j][i] !='.' && set.contains(board[j][i]))
                {
                    return false;
                }
                else
                {
                    set.add(board[j][i]);
                }
                

            }
        }





  


        return true;

    }
}