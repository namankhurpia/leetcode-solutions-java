//brute force approach - O(n^2)

class Solution {
    public String removeDuplicates(String s, int k) {
        
        StringBuilder str = new StringBuilder(s);
        int len = -1;
        while(len!=str.length())
        {
            len = str.length();
            int c=1;
            for(int i=0;i<str.length();i++)
            {
                if(i==0 || str.charAt(i-1)!=str.charAt(i))
                {
                    c=1;
                }
                else
                {
                    c++;
                }
                
                if(c == k)
                {
                    str.delete(i-k+1,i+1);
                    break;
                }


                
            }
        }
    return str.toString();

    }
}

//OPTIMAL SOLUTION - O(n)
class Solution {
    public String removeDuplicates(String s, int k) {
        
    StringBuilder str = new StringBuilder(s);
    int count [] = new int[str.length()];
    for(int i=0;i<str.length();i++)
    {
        if(i==0 || str.charAt(i-1)!=str.charAt(i))
        {
            count[i] = 1;
        }
        else
        {
            count[i] = count [i-1] + 1;
            if(count [i] == k)
            {
                str.delete(i-k+1,i+1);
                i=i-k;
            }
        }
        
    }
    return str.toString();

    }
}

//OPTIMAL SOLUTION - using stacks - O(n)

class Solution {
    public String removeDuplicates(String s, int k) {
        
    StringBuilder str = new StringBuilder(s);
    Stack<Integer> count =new Stack<Integer>();
    for(int i=0;i<str.length();i++)
    {
        if(i==0 || str.charAt(i-1)!=str.charAt(i))
        {
            count.push(1);
        }
        else
        {
            int temp = count.pop() +1 ;
            if(temp == k)
            {
                str.delete(i-k+1,i+1);
                i=i-k;
            }
            else
            {
                count.push(temp);
            }
        }
        
    }
    return str.toString();

    }
}