class Solution {
    public List<String> removeComments(String[] source) {
        ArrayList<String> res = new ArrayList<String>();
        boolean block = false;
        StringBuilder sb = new StringBuilder();
        for(String s:source)
        {
            for(int i=0;i<s.length();i++)
            {
                if(block)
                {
                    if(i< s.length()-1 && s.charAt(i)=='*' && s.charAt(i+1)=='/' )
                    {
                        block = false;
                        i++;
                    }
                }
                else
                {
                     if(i< s.length()-1 && s.charAt(i)=='/' && s.charAt(i+1)=='/'  )
                     {
                        break;
                     }
                     else if(i< s.length()-1  && s.charAt(i)=='/' && s.charAt(i+1)=='*'  )
                     {
                        block = true;
                        i++;
                     }
                     else
                     {
                        sb.append(s.charAt(i));
                     }
                }   
            }
            if(!block && sb.length()>0)
            {
                res.add(sb.toString());
                sb = new StringBuilder();
            }   
        }
        return res;
    }
}