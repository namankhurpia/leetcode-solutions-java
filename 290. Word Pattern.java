//BRUTE FORCE APPROACH
class Solution {
    public boolean wordPattern(String pattern, String s) {
        
        String arr[] = s.split("\\s");
        
        if(arr.length!=pattern.length())
        {
            return false;
        }

        for(int i=0;i<pattern.length();i++)
        {
            for(int j=0;j<pattern.length();j++)
            {
                if(i!=j && pattern.charAt(i)==pattern.charAt(j))
                {
                    if(!arr[i].equalsIgnoreCase(arr[j]))
                    {
                        return false;
                    }
                }
                else if (i!=j && pattern.charAt(i)!=pattern.charAt(j))
                {
                    if(arr[i].equalsIgnoreCase(arr[j]))
                    {
                        return false;
                    }
                }
                
            }
        }
        return true;

    }
}

//OPTIMISED APPROACH

HashMap map_index = new HashMap();
String[] words = s.split(" ");

if (words.length != pattern.length())
    return false;

for (Integer i = 0; i < words.length; i++) {
    char c = pattern.charAt(i);
    String w = words[i];

    if (!map_index.containsKey(c))
        map_index.put(c, i);

    if (!map_index.containsKey(w))
        map_index.put(w, i);

    if (map_index.get(c) != map_index.get(w))
        return false;
}

return true;
