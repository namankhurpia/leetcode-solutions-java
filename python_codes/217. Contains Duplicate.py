from typing import List

class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        
        myset = set()
        for n in nums:
            myset.add(n)
        
        if len(myset)<len(nums):
            return True
        else:
            return False


if __name__ == "__main__":
    sol = Solution()
    arr = [1,2,3,4,5]
    print(sol.containsDuplicate(arr))
    arr = [1,2,3,4,5,1]
    print(sol.containsDuplicate(arr))

