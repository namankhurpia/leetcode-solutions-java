//BRUTE FORCE APPROACH - nlogn

class Solution {
    public boolean isAnagram(String s, String t) {
        
        char arr[] = s.toCharArray();
        char arr2[] = t.toCharArray();

        Arrays.sort(arr);
        Arrays.sort(arr2);

        return Arrays.equals(arr,arr2);
           
    }
}

//OPTIMAL APPROACH - O(n)

class Solution {
    public boolean isAnagram(String s, String t) {
        
        int words[] = new int[26];
        for(int i=0;i<s.length();i++)
        {
            int index = s.charAt(i) - 'a';
            words[index] = words[index] +1; 
        }

        for(int i=0;i<t.length();i++)
        {
            int index = t.charAt(i) - 'a';
            
            if(words[index]-1<0)
            {
                return false;
            }
            words[index ] = words[index]-1;
        }

        for(int i=0;i<words.length;i++)
        {
            if(words[i]!=0)
            {
                return false;
            }
        }

        return true;
           
    }
}

