class Solution:
    def longestPalindrome(self, s: str) -> str:
        longest_palindrome_coordinates = [0,0]

        def expand_from_center(left,right):
            while(left >=0 and right<len(s) and s[left]==s[right]):
                left -=1
                right += 1
            
            return right-left-1
        
        for i in range(len(s)):
            odd_range = expand_from_center(i,i)
            if odd_range > longest_palindrome_coordinates[1] - longest_palindrome_coordinates[0] +1:
                distance = odd_range // 2
                longest_palindrome_coordinates = [i-distance, i+distance]

            #print(longest_palindrome_coordinates)

            even_range = expand_from_center(i,i+1)
            if even_range > longest_palindrome_coordinates[1] - longest_palindrome_coordinates[0] +1:
                distance = (even_range // 2 )-1 
                longest_palindrome_coordinates = [i-distance, i+distance +1]
            
            #print(longest_palindrome_coordinates)
        
        i,j = longest_palindrome_coordinates
        
        return s[i:j+1]
        




            

"""


       
Complexity Analysis

Given n as the length of s,

Time complexity: O(n 
2
 )

There are 2n−1=O(n) centers. For each center, we call expand, which costs up to O(n).

Although the time complexity is the same as in the DP approach, the average/practical runtime of the algorithm is much faster. This is because most centers will not produce long palindromes, so most of the O(n) calls to expand will cost far less than n iterations.

The worst case scenario is when every character in the string is the same.

Space complexity: O(1)

We don't use any extra space other than a few integers. This is a big improvement on the DP approach.

""" 