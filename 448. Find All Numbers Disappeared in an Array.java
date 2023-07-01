//O(n)

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        
        int len = nums.length;
        HashSet<Integer> arr = new HashSet<>();
        
        for(int i:nums)
            arr.add(i);

        for(int i=1;i<=len;i++)
        {
            if(!arr.contains(i))
            {
                arr.add(i);
            }
            else
            {
                arr.remove(Integer.valueOf(i));
            }
        }
        return new ArrayList<>(arr);

    }
}

//O(n) but faster

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        
        for(int i=0;i<nums.length;i++)
        {
            int index = Math.abs(nums[i])-1;
            if(nums[index]>0)
            {
                nums[index] = -1* nums[index];
            }
        }

        List<Integer> arr = new ArrayList<Integer>();
        for(int i=1;i<=nums.length;i++)
        {
            if(nums[i-1]>0)
            {
                arr.add(i);
            }
        }
        return arr;

    }
}