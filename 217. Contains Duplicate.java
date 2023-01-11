class Solution {
    public boolean containsDuplicate(int[] nums) {
        
        //BRUTE FORCE
        /*ArrayList<Integer> arr =new ArrayList<>();
        for(int i:nums)
        {
            if(arr.contains(i))
            {
                return true;
            }
            else
            {
                arr.add(i);
            }
        }

        return false;

*/

        //HASHSET
        HashSet<Integer> set = new HashSet<>();
        for(int i:nums)
        {
            set.add(i);
        }
        if(set.size()==nums.length)
        {
            return false;
        } 
        
        else
        {
            return true;
        }

        
    }
}

