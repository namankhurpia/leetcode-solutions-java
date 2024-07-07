class Solution {
    public int compress(char[] chars) {
        
        int res =0;
        int i=0;
       

        while(i<chars.length)
        {
            int gl = 0;
            char currentChar = chars[i];
            while( i < chars.length && chars[i] == currentChar)
            {
                gl++;
                i++;
            }
            
            chars[res] = currentChar;

            res++;

            if (gl > 1) {
                String glstr = gl+"";
                for(int j=0;j<glstr.length();j++)
                {
                    chars[res] = glstr.charAt(j);
                    res++;
                }
                
            }
            

        }

       
        
        return res;
        


    }
}