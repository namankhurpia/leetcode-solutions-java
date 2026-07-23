from typing import List

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if len(nums)==0:
            return 0

        nums = list(set(nums))
        maxyet = 1
        
        for i in range(len(nums)):
            if nums[i]-1 in nums:
                continue
            else:
                # found a starting point in the pattern
                
                start = nums[i]
                print(start)
                j = i
                count = 1
                while(True):
                    if(start+1 in nums):
                        count +=1
                        start +=1
                    else:
                        break
                maxyet = max(maxyet, count)
        return maxyet
        
if __name__=="__main__":
    sol = Solution()
    print("answer is",sol.longestConsecutive([-1,0,1])) 

        