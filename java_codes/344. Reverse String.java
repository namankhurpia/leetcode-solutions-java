class Solution {
    public void reverseString(char[] s) {
        
        int len = s.length-1;
        int i=0;
        while(i<s.length/2)
        {
            char temp = s[(len)-i];
            s[(len)-i] = s[i];
            s[i] = temp;
            i++;
        }

        

    }
}