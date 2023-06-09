//time  - O(n) and space O(1)
class Solution {

    public static int[] removeelement(int []nums, int index)
    {
        for(int i=index+1;i<nums.length;i++)
        {
            nums[i-1] = nums[i];
        }
        return nums;
    }
    public int removeDuplicates(int[] nums) {
        
        int len =nums.length;
        int count = 1;
        for(int i=1;i<len;i++)
        {
            if(nums[i]==nums[i-1])
            {
                count+=1;
                if(count > 2)
                {
                    nums = removeelement(nums,i);
                    i--;
                    len--;
                }
            }
            else
            {
                count=1;
            }

            
        }
        return len;
        

    }
}