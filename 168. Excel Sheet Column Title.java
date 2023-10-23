//O(log n)

class Solution {
    public String convertToTitle(int columnNumber) {
        
        StringBuilder res = new StringBuilder();
        while(columnNumber>0)
        {
            columnNumber--;
            char c = (char) (columnNumber % 26 +'A');
            columnNumber /=26;
            res.append(c);
        }

        return res.reverse().toString();


        
    }
}