//OPTIMAL SOLUTION

class Solution {
    public String breakPalindrome(String palindrome) {
        
        if(palindrome.length()==1 || palindrome.length()==0)
        {
            return "";
        }

        StringBuilder strblr = new StringBuilder(palindrome);
        for(int i=0;i<palindrome.length()/2;i++)
        {
            if(palindrome.charAt(i)!='a')
            {
                strblr.setCharAt(i,'a');
                return strblr.toString();
            }
        }
        
        strblr.setCharAt(palindrome.length()-1,'b');
        return strblr.toString();

    }
}