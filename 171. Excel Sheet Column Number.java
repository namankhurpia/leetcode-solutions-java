//optimal solution

class Solution {
    public int titleToNumber(String columnTitle) {
        
        char []arr = columnTitle.toCharArray();
        int sum = 0;
        for(int i=0;i<arr.length;i++)
        {
            sum = sum * 26;
            sum = sum + arr[i] - 64;   
        }

        return sum;

    }
}