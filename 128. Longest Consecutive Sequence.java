//OPTIMAL APPROACH - BEATS 80% in runtime
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet set = new HashSet<Integer>();
        for(int i:nums)
            set.add(i);

        if(nums.length==0)
        {
            return 0;
        }

        ArrayList<Integer> arr = new ArrayList<>(set);
        Collections.sort(arr);
        
        int cur=1,max = 1;
        for(int i=0;i<arr.size()-1;i++)
        {
            if(arr.get(i)+1==arr.get(i+1))
            {
                cur = cur +1;              
            }
            else
            {
                cur = 1;
            }

            if(cur>max)
            {
                max = cur;
            }
        }
        return max;

    }
}