//BRUTE FORCE APPROACH - O(k * n-k)

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        if(n == 0 || k==0)
            return new int[0];
        if(k==1)
            return nums;
        int res []= new int[n - k +1];
        for(int i=0;i<n-k+1;i++)
        {
            int max = Integer.MIN_VALUE;
            for(int j=i;j<i+k;j++)
            {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        } 
        return res;

    }
}

//O(n) - beats 80%
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        
        output = []
        l=0
        r=0
        deque = collections.deque()

        while(r<len(nums)):
            while deque and nums[deque[-1]]<nums[r]:
                deque.pop()
            deque.append(r)

            if(l>deque[0]):
                deque.popleft()
            
            if((r+1)>=k):
                output.append(nums[deque[0]])
                l+=1;
            r+=1
        
        return output