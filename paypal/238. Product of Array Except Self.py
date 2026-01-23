class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        prefix = [1 for _ in range(len(nums)+1)]
        postfix = [1 for _ in range(len(nums)+1)]
        result = [0 for _ in range(len(nums))]

        for i in range (0,len(nums)):
            prefix[i+1] = prefix[i]*nums[i]

        #print(prefix)

        for i in range(len(nums)-1,-1,-1):
            postfix[i] = postfix[i+1]*nums[i]
        
        #print(postfix)

        for i in range(0,len(nums)):
            result[i] = prefix[i]*postfix[i+1]
        return result


# same time complexity but space complexity is O(1)
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        res = [1] * n

        # prefix product in res
        pref = 1
        for i in range(n):
            res[i] = pref
            pref *= nums[i]

        # multiply by postfix product
        post = 1
        for i in range(n - 1, -1, -1):
            res[i] *= post
            post *= nums[i]

        return res


            