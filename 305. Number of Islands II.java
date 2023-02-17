//INCORRECT SOLUTION- FASTER APPROACH BUT WORKS ONLY IF NO ILANDS ARE REMOVED
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        
        int xcord[] = new int[positions.length];
        int ycord[] = new int[positions.length];
        for(int i=0;i<positions.length;i++)
        {
            xcord[i] = Integer.MIN_VALUE;
            ycord[i] = Integer.MIN_VALUE;
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        int count =0 ;
        for(int i=0;i<positions.length;i++)
        {
            int xcordt = positions[i][0];
            int ycordt = positions[i][1];
            
            if(exist(xcord,ycord,xcordt,ycordt))
            { 
            }
            else
            {
                count = count+1;
            }
            res.add(count);
            xcord[i] = xcordt;
            ycord[i] = ycordt;
            
        }
        return res;

    }

    public static boolean exist(int [] xcord,int [] ycord, int xcordt, int ycordt)
    {
        for(int i=0;i<xcord.length;i++)
        {
            if((xcordt-1==xcord[i] && ycordt==ycord[i] ) || (xcordt==xcord[i]-1 && ycordt==ycord[i] ))
            {
                return true;
            }
            else if((xcordt==xcord[i] && ycordt-1==ycord[i]) || (ycord[i]-1 ==ycordt &&  xcordt==xcord[i]))
            {
                return true;
            }
            
        }
        return false;
    }



}


//OPTIMAL SOLUTION
