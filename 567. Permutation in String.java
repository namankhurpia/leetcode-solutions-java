class Solution {
    public boolean checkInclusion(String s1, String s2) {
        
        if(s1.length()>s2.length())
            return false;

        // basically we check for anagram of s1 inside s2, so if we want to check inside s2 we simply do sliding window protocol

        int []s1arr = new int[26];
        int []s2arr = new int[26];

        for(int i=0;i<s1.length();i++)
        {
            char c1 = s1.charAt(i);
            s1arr[c1 - 'a'] +=1;
            char c2 = s2.charAt(i);
            s2arr[c2 - 'a'] +=1; 
        } 

        int matches = 0;
        for(int i=0;i<26;i++)
        {
            if(s1arr[i] == s2arr[i])
            {
                matches+=1;
            }
        }

        int l = 0;
        for(int r =s1.length();r<s2.length();r++)
        {
            //System.out.println(r+" "+l + ":"+matches);

            if(matches == 26)
            {
                return true;
            }

            char c1 = s2.charAt(r);
            s2arr[c1 - 'a'] +=1;

            if(s1arr[c1 - 'a'] == s2arr[c1-'a'])
                matches +=1;
            else if(s1arr[c1 - 'a'] +1 == s2arr[c1-'a'])
                matches -=1;

            char c2 = s2.charAt(l);
            s2arr[c2 - 'a'] -=1; 

            if(s1arr[c2 - 'a'] == s2arr[c2-'a'])
                matches +=1;
            else if(s1arr[c2 - 'a'] -1 == s2arr[c2-'a'])
                matches -=1;

            l+=1;

            //print(s1arr);
            //print(s2arr);

        }


        
        return matches == 26;
        

    }

    public static void print(int arr[])
    {
        System.out.println("array:");
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i]);
        System.out.println();
    }
}