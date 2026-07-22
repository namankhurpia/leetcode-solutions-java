//BRUTE FORCE SOLUTION

class Solution {
    public boolean isPalindrome(int x) {
        String st = x+"";
        StringBuilder str = new StringBuilder(x+"");
        String rev = str.reverse().toString();

        if(rev.equalsIgnoreCase(st))
        {
            return true;
        }
        else
        {
            return false;
        }
        
        
    }
}


//OPTIMAL SOLUTION - beats 100

class Solution {
    public boolean isPalindrome(int x) {
       
       if(x<0 || (x%10 == 0 && x!=0))
       {
           return false;
       }

       int revertedNu = 0;
       while(x>revertedNu)
       {
           revertedNu = revertedNu * 10 + x % 10;
           x = x/10;
       }

       if(x==revertedNu)
       {
           return true;
       }
       else if(x == revertedNu/10)
       {
           return true;
       }
       else
       {
           return false;
       }
        
    }
}