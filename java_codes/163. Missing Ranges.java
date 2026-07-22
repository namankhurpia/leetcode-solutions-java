//O(n) - BEST OPTIMAL
class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i=0;i<=nums.length;i++)
        {
            int lim1 = 0;
            int lim2 = 0;
            if(i==0)
                lim1 = lower;
            else
                lim1 = nums[i-1] +1;
            
            if(i==nums.length)
                lim2 = upper;
            else
                lim2 = nums[i] - 1;
            
            if(lim1>lim2)
            {

            }
            else if(lim1 == lim2)
            {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(lim1);
                list.add(lim2);
                res.add(list);
            }
            else
            {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(lim1);
                list.add(lim2);
                res.add(list);
            }
            
        }
        return res;
    }
}