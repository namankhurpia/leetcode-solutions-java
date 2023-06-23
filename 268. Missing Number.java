//O(n)

class Solution {
    public int missingNumber(int[] nums) {
        
        HashSet<Integer> set = new HashSet<>();
        for(int i:nums)
            set.add(i);
        int len = nums.length;
        for(int i=0;i<len;i++)
        {
            if(!set.contains(i))
            {
                return i;
            }
        }
        return len;

    }
}

//O(n) and space also O(n)

class Solution {
    public int missingNumber(int[] nums) {
        
        int sum =0;
        for(int i:nums)
            sum+=i;
        int len = nums.length;
        int sum2 = len*(len+1);
        sum2 = sum2/2;
        return sum2-sum;

    }
}