//brute force solution - O(n) but space complexity is high

class Solution {
    public int firstMissingPositive(int[] nums) {

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int n = nums.length;

        for(int i=0;i<n;i++)
        {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        System.out.println(max);
        System.out.println(min);


        StringBuffer sol = new StringBuffer(max);
        for(int i=0;i<max+1;i++)
        {
            sol.append('0');
        }

        for(int i=0;i<n;i++)
        {
            if(nums[i]>0)
            {
                sol.setCharAt(nums[i],'1');
            }                 
        }
        System.out.println(sol);

        for(int i=1;i<max+1;i++)
        {
            if(sol.charAt(i) == '0')
            {
                return i;
            }
        }
        return max+1;
        
    }
}

//OPTIMAL SOLUTION - O(n)

class Solution {
    public int firstMissingPositive(int[] nums) {

        int n = nums.length;

        int c =0;
        for(int i=0;i<n;i++)
        {
            if(nums[i]==1)
            {
                c++;
            }
        }
        if(c==0)
            return 1;

        for(int i=0;i<n;i++)
        {
            if(nums[i]<=0 || nums[i]>n)
            {
                nums[i] = 1;
            }
        }

        for(int i=0;i<n;i++)
        {
            int a = Math.abs(nums[i]);
            if(a==n)
            {
                nums[0] = - Math.abs(nums[0]);
            }
            else
            {
                nums[a] = - Math.abs(nums[a]);
            }
        }

        for(int i=1;i<n;i++)
        {
            if(nums[i]>0)
                return i;
        }

        if(nums[0]>0)
            return n;
        
        return n+1;




        
    }
}

