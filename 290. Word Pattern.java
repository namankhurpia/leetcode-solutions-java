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

//OPTIMAL
