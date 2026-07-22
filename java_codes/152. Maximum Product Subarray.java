//BRUTE FORCE SOLUTION
class Solution {
    public int maxProduct(int[] nums) {
        
        if (nums.length == 0) return 0;

        int max =nums[0];
        for(int i=0;i<nums.length;i++)
        {
            int acc = 1;
            for(int j=i;j<nums.length;j++)
            {
                acc = acc*nums[j];
                max = Math.max(max, acc);

            }
        }
        return max;
        

    }
}

//OPTIMISED SOLUTION
class Solution {
    public int maxProduct(int[] nums) {
        int min=0, max =0;
        
        int maxsofar = nums[0];
        int minsofar = nums[0];
        int res =maxsofar;
        for(int i=1;i<nums.length;i++)
        {
            max = Math.max(minsofar*nums[i] ,maxsofar*nums[i] );
            min = Math.min(minsofar*nums[i] ,maxsofar*nums[i] );

            int tempmax = Math.max(max, nums[i] );
            minsofar = Math.min(min, nums[i]);
            maxsofar = tempmax;
            res = Math.max(maxsofar,res);
        }
        return res;

    }
}