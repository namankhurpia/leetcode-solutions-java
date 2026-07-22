//OPTIMAL TIME COMPLEXITY- O(n) , BEATS 100% , space complexity is O(3n) = O(n) but can be reduced to O(1)

class Solution {
    public int[] productExceptSelf(int[] nums) {

       int prefix[] = new int[nums.length+1];
       int postfix[] = new int[nums.length+1];

        prefix[0] = 1;
        int mul = 1;
       for(int i=0;i<nums.length;i++)
       {
           prefix[i+1] = mul*nums[i];  
           mul = mul*nums[i];
       }


         mul = 1;
        postfix[postfix.length-1]=1;
       for(int i=postfix.length-2;i>=0;i--)
       {
           postfix[i] = mul*nums[i];  
           mul *=nums[i];
       }

       int res[] = new int[nums.length];
       for(int i=0;i<res.length;i++)
       {
           res[i] = prefix[i]*postfix[i+1];
       }
       return res;


    }
}

//OPTIMAL SOLUTION - O(n) - time, O(1) - space

class Solution {
    public int[] productExceptSelf(int[] nums) {

       int n = nums.length;
        int[] res = new int[n];
        // Calculate lefts and store in res.
        int left = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0)
                left = left * nums[i - 1];
            res[i] = left;
        }
        // Calculate rights and the product from the end of the array.
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1)
                right = right * nums[i + 1];
            res[i] *= right;
        }
        return res;


    }
}
