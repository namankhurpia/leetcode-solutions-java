class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        last = {}
        largest = 0
        l_index = 0

        for index, char in enumerate(s):
            if char in last and last[char]>= l_index:
                l_index = last[char] + 1
            last[char] = index
            largest = max(largest, index - l_index + 1)

        return largest
