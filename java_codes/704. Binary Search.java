//best solution - log(n)

// take 2 pointers for index, initalized at 0 and length-1, now find the middle and shift pointers accodringly
class Solution {
    public int search(int[] nums, int target) {
        
        int left = 0;
        int right = nums.length-1;
        int mid = 0;

        while(left<=right)
        {
            mid = (left+right)/2;
            if(nums[mid]>target)
            {
                right = mid-1;
            }
            else if(nums[mid]<target)
            {
                left = mid+1;
            }
            else
            {
                return mid;
            }
        }
        return -1;

    }
}