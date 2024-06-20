//python optimised solution - neetcode - outer expansion approach

class Solution(object):
    def longestPalindrome(self, s):
        # storing my result and the length of longest result
        res = ""
        resLen = 0

        for i in range(0, len(s)):
            # consider i as center of substring
            # if odd len string - if the palindrome is odd len
            l = i
            r = i
            #is the substring a palindrome?
            while(l>=0 and r<len(s) and s[l] == s[r]):
                #if existing result is longer than previous stored result
                if(r-l+1> resLen):
                    res = s[l:r+1]
                    resLen = len(res)
                #expanding from center 
                l -=1
                r +=1
            # if even len string - if the palindrome is odd len
            l = i
            r = i+1
            #is the substring a palindrome?
            while(l>=0 and r<len(s) and s[l] == s[r]):
                #if existing result is longer than previous stored result
                if(r-l+1>resLen):
                    res = s[l:r+1]
                    resLen = len(res)
                l-=1
                r+=1
        
        return res
    
//java code with same logic

class Solution {
    public String longestPalindrome(String s) {

        String even = "";
        String odd = "";
        String maxStr = s.substring(0,1);

        for(int i=0;i<s.length()-1;i++)
        {
            odd = expandfromcenter(i,i,s);
            even = expandfromcenter(i,i+1,s);
            
            if(odd.length()> maxStr.length())
            {
                maxStr = odd;
            }
            if(even.length()> maxStr.length())
            {
                maxStr = even;
            }
        }
        return maxStr;

    }

    public static String expandfromcenter(int left, int  right, String s)
    {
        while(left >= 0 && right< s.length() && s.charAt(left) == s.charAt(right))
        {
            left --;
            right ++;
        }
        return s.substring(left+1, right);
    }
}