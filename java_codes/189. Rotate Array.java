// Using extra array - O(n)

class Solution {
    public void rotate(int[] nums, int k) {
        
        int newnums[] = new int[nums.length];
        for(int i=0;i<nums.length;i++)
        {
            newnums[(i+k)%nums.length] = nums[i];
        }

        for(int i=0;i<nums.length;i++)
        {
            nums[i] = newnums[i];
        }
        
    }
}