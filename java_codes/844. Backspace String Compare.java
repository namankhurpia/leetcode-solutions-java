//O(n) - beats 50%

class Solution {
    public boolean backspaceCompare(String s, String t) {
        
        return (evaluate(s).equalsIgnoreCase(evaluate(t)));
        
    }

    public static String evaluate(String s)
    {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)!='#')
            {
                stack.push(s.charAt(i));   
            }
            else
            {
                if(!stack.empty())
                    stack.pop();
            }
                    
        }
        return String.valueOf(stack);
    } 
}