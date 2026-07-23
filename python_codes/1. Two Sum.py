#O(n)^2

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        
        for i,n in enumerate(nums):
            if target-n in nums[i+1:]:
                index = nums.index(target-n, i+1)
                return i,index


#O(n) - single pass approach

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        
        seen = {}
        for i,n in enumerate(nums):
            if target - n in seen:
                return seen[target-n] ,i
            seen[n] = i
