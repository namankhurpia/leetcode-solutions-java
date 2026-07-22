//O(n) and O(1)space

class Solution {
    public boolean increasingTriplet(int[] nums) {

        if(nums==null || nums.length<3)
        {
            return false;
        }

        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>a && nums[i]>b )
            {
                return true;
            }
            else if(nums[i]<=a)
            {
                a = nums[i];
            }
            else if(a < nums[i])
            {
                b = nums[i];
            }

        }
        return false;
        
    }
}