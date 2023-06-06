//O(log n)

class Solution {
    public int findMin(int[] nums) {

        int l = 0;
        int r = nums.length-1;
        int min = nums[0];

        while(l<=r)
        {
            if(nums[l]<nums[r])
            {
                min = Math.min(nums[l],min);
                break;
            }

            int mid = (l+r)/2;
            min = Math.min(nums[mid],min);
            System.out.println(min);
            if(nums[mid]>=nums[l])
            {
                l = mid+1;
            }
            else 
            {
                r = mid-1;
            }
        }
        return min;
    }
}