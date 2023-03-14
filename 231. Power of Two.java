//optimal solution - using recursion

class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n==0)
        {
            return false;
        }
        if(n==1)
        {
            return true;
        }
        
        int power = 0;
        while(n!=0)
        {
            if(n==1)
            {
                return true;
            }
            if(n%2==0)
            {
                n=n/2;
                power ++;
            }
            else
            {
                return false;
            }
            
        }
        return true;

    }
}