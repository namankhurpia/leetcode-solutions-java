class Solution {
    public boolean judgeCircle(String moves) {
        int cd = 0;
        int cu =0;
        int cl = 0;
        int cr = 0;
        for(int i=0;i<moves.length();i++)
        {
            char c = moves.charAt(i);
            if(c=='D')
            {
                cd++;
            }
            else if(c=='U')
            {
                cu++;
            }
            else if(c=='L')
            {
                cl++;
            }
            else if(c=='R')
            {
                cr++;
            }
        }

        if(cu == cd && cl == cr)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}