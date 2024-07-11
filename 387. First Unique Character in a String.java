class Solution {
    public int firstUniqChar(String s) {
        int ar [] = new int[26];
        Arrays.fill(ar,0);

        for(int i=0;i<s.length();i++)
        {
            int index = s.charAt(i)-'a';
            ar[index] = ar[index]+1;
        }

        for(int i=0;i<s.length();i++)
        {
            int index = s.charAt(i)-'a';
            if(ar[index]==1)
            {
                return i;
            }
        }
        return -1;
        
    }
}