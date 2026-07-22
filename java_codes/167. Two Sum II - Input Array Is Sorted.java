//two pointer approach solves it

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        
        int l = 0;
        int r = numbers.length-1;

        while(numbers[l]+numbers[r]!=target)
        {
            if(numbers[l]+numbers[r]<target)
            {
                l++;
            }
            else
            {
                r--;
            }
        }
        int res[] = new int[2];
        res[0] = l+1;
        res[1] = r+1;
        return res;

    }
}