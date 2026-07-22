class Solution {
    public boolean detectCapitalUse(String word) {

        
        int c=0,ng=0;
        for(int i=0;i<word.length();i++)
        {
            if(Character.isUpperCase(word.charAt(i)))
            {
                c +=1;
            }
            else
            {
                ng+ =1;
            }

        }
        if(c==word.length())
        {
            return true;
        }
        if(ng==word.length())
        {
            return true;
        }
        
        
        for(int i=0;i<word.length();i++)
        {
            if(i==0)
                {
                    if(Character.isUpperCase(word.charAt(i)))
                    {
                        continue;
                    }
                    else
                    {
                        return false;
                    }
                }
            if(Character.isUpperCase(word.charAt(i)))
            {
                
                if(i!=0 && word.charAt(i-1) == ' ')
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
        }

        return true;

    }
}