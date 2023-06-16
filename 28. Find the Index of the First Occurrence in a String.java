// optimal solution - O(n)
class Solution {
    public int strStr(String haystack, String needle) {

   if(needle.length()>haystack.length())
   {
       return -1;
   }

    int hl = haystack.length();
    haystack+=needle;
    int ln = needle.length();
    for(int i=0;i<hl;i++)
    {
        String check = haystack.substring(i,i+ln);
        if(needle.equalsIgnoreCase(check))
        {
            return i;
        }
    }
    return -1;
   

    }
}

//one liner solution


class Solution {
    public int strStr(String haystack, String needle) {

    return  haystack.indexOf(needle);

    }
}