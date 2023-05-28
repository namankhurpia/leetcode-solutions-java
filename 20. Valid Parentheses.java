class Solution {
    public boolean isValid(String s) {
        
        Stack<Character> stack = new Stack<Character>();

        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='(' || s.charAt(i)=='{' || s.charAt(i)=='[')
                stack.add(s.charAt(i));

            if(!stack.isEmpty())
            {

                if(s.charAt(i)==')')
                {
                    if(stack.peek()=='(')
                    {
                        stack.pop();
                    }
                    else
                    {
                        stack.add(s.charAt(i));
                    }
                }
                else if(s.charAt(i)=='}')
                {
                    if(stack.peek()=='{' )
                    {
                        stack.pop();
                    }
                    else
                    {
                        stack.add(s.charAt(i));
                    }

                }
                else if(s.charAt(i)==']')
                {
                    if(stack.peek()=='[' )
                    {
                        stack.pop();
                    }
                    else
                    {
                        stack.add(s.charAt(i));
                    }
                }



            }
            else
            {
                return false;
            }
            
           
        }

        if(stack.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}