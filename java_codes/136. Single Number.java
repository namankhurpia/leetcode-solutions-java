//OPTIMAL SOLUTION

class Solution {
    public int singleNumber(int[] nums) {

        HashSet<Integer> set = new HashSet<Integer>();
        int arraysum = 0;
        for(int i:nums)
        {
            arraysum = arraysum+i;
            set.add(i);
        }
        
        int setsum = set.stream().reduce(0, Integer::sum);
        setsum = 2*setsum;

        return setsum-arraysum;
        
    }
}