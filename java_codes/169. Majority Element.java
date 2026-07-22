class Solution {
    public int majorityElement(int[] nums) {
        
        Arrays.sort(nums);
        return nums[nums.length/2];
        

        /*
        int countpossible =0;
        if(nums.length%2==0)
        {
          countpossible = (nums.length) /2;  
        }
        else
        {
            countpossible = (nums.length+1) /2;  
        }
        
        int count =0;
        for(int i=0;i<nums.length;i++)
        {
            count =0;
            for(int j:nums)
            {
                if(j==nums[i])
                {
                    count =count+1;
                }
            }
            if(count>=countpossible)
            {
                return nums[i];
            }
        }
        return 0;
        */

        

    }
}