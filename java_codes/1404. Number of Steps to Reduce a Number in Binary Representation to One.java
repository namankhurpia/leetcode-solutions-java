//brute force solution - doesnt get accepted

class Solution {
    public int numSteps(String s) {
        Long decimal = Long.parseLong(s,2);
        
        int res =0;
        while(decimal!=1)
        {
            if(decimal%2!=0)
            {
                decimal+=1;
            }
            else
            {
                decimal = decimal/2;
            }
            res++;
        }
        return res;
    }

    

}

