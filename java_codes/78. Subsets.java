class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    public static void backtrack(ArrayList<List<Integer>> list, ArrayList<Integer> cur,int []nums, int i)
    {
        list.add(new ArrayList<Integer>(cur));
        for(;i<nums.length;i++)
        {
            cur.add(nums[i]);
            backtrack(list, cur, nums, i+1);
            cur.remove(cur.size()-1);
        }
    }
}