//OPTIMAL SOLUTION - O(n*m) where n is the length of array and m is the avg size of string in array
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String,ArrayList<String>> map = new HashMap<>();
        for(String s:strs)
        {
            
            int count [] = new int[26];
            String str = "";
            for(int i=0;i<s.length();i++)
            {
                count[s.charAt(i)-'a'] += 1;
            }
            str = converttoString(count);
            
            if(map.containsKey(str))
            {
                ArrayList<String> str2 = map.get(str);
                str2.add(s);
                map.put(str,str2 );
            }
            else
            {
                ArrayList<String> list2 = new ArrayList<>();
                list2.add(s);
                map.put(str,list2);
            }
  
            
        }
        ArrayList<List<String>> res = new ArrayList<List<String>>(map.values());
        return res;
        
    }



    public static String converttoString(int [] count)
    {
        String res = "";
        for(int i=0;i < count.length;i++)
        {
            res = res+count[i];
        }
        return res;
    }
}