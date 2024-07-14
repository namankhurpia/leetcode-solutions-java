class Solution {
    public int myAtoi(String s) {
        

        s = s.trim();
        if(s.length()==0)
        {
            return 0;
        }
        boolean is_neg = false;
        int j=s.length();
        int i=0;
        if(s.charAt(0)=='-')
        {
            is_neg = true;
            i++;
        }
        if(s.charAt(0)=='+')
        {
            is_neg = false;
            i++;
        }
        
        int res = 0;
        while(i<s.length())
        {
            char c = s.charAt(i);
            if(Character.isDigit(c))
            {
                int integerdigit = c - '0';
                
                    
                if (res > (Integer.MAX_VALUE / 10) || (res == (Integer.MAX_VALUE / 10) && integerdigit > 7))
                {
                    return is_neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                res = res*10 + integerdigit;

            }
            else
            {
                break;
            }
            i++;
            j--;
        }

       
        if(is_neg)
            res = res*(-1);

        return res;


    }

}