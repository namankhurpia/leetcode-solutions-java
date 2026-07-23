# O(nlog(n))

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if len(nums)==0:
            return 0

        nums = sorted(set(nums))
        final_count = 1
        count_cur = 1
        #print(nums)
        for i in range(len(nums)-1):
            if nums[i] +1  == nums[i+1]:
                count_cur += 1
                final_count = max(final_count, count_cur)
            else:
                count_cur = 1
        return final_count

        
#O(n) - most optimal - find a starting point by checking if that number exists in the list or not 

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if len(nums)==0:
            return 0

        nums_set = set(nums)
        nums = list(nums_set)
        maxyet = 1
        
        for i in nums:
            if i-1 in nums_set:
                continue
            
            # found a starting point in the pattern
            start = i
            count = 1
            while(start+1 in nums_set):
                count +=1
                start +=1
            maxyet = max(maxyet, count)
        return maxyet
        
                    

        
