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