//o(n)

class Solution {
    public int singleNonDuplicate(int[] nums) {
        if(nums.length==1)
        {
            return nums[0];
        }
        
        for(int i=0;i<nums.length;i++)
        {
            if(i==nums.length-1)
            {
                return nums[nums.length-1];
            }
            if(nums[i]==nums[i+1])
            {
                i++;
            }
            else
            {
                return nums[i];
            }
        }
        return -1;

    }
}

//O(logn)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        
        int left = 0;
        int right = nums.length-1;

        while(left<right)
        {
            int mid = (left+right)/2;
            if(mid%2==1)
            {
                mid--;
            }

            if(nums[mid]==nums[mid+1])
            {
                left = mid+2;
            }
            else
            {
                right = mid;
            }

        }

        return nums[left];

    }
}
