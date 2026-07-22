//OPTIMAL - O(n)

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        
        int max = Integer.MIN_VALUE;
        for(int i:candies)
            max = Math.max(i,max);
        
        ArrayList<Boolean> res = new ArrayList<Boolean>();
        for(int i:candies)
        {
            if(i+extraCandies>=max)
            {
                res.add(true);
            }
            else
            {
                res.add(false);
            }
        }
        return res;



    }
}