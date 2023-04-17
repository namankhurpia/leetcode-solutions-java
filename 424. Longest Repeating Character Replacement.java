//OPTIMAL SOLUTION

class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];

        int res = 0;
        int l = 0;
        int max =0;
        for(int r =0;r<s.length();r++)
        {
            char c  = s.charAt(r);
            count[c - 'A'] += 1; 
            max = findmax(count);

            while( (r - l +1) - max > k )
            {
                char c2 = s.charAt(l);
                count[c2 - 'A'] -=1; 
                l = l+1;
            }
            res = Math.max(r-l+1, res);
        }
        return res;

        
    }

    public static int findmax(int count[])
    {
        int res = 0;
        for(int i=0;i<count.length;i++)
        {
            res = Math.max(res, count[i]);
        }
        return res;
    }
}

//MOST OPTIMAL APPROACH - BEATS 85%

class Solution {
    public int characterReplacement(String s, int k) {

        //METHOD 2
        int[] count = new int[26];

        int res = 0;
        int l = 0;
        int max =0;
        int maxfreq = 0;
        for(int r =0;r<s.length();r++)
        {
            char c  = s.charAt(r);
            count[c - 'A'] += 1; 
            maxfreq = Math.max(maxfreq,  count[c - 'A']);
        
            while( (r - l +1) - maxfreq > k )
            {
                char c2 = s.charAt(l);
                count[c2 - 'A'] -=1; 
                l = l+1;
            }
            res = Math.max(r-l+1, res);
        }
        return res;

        
    }
}
