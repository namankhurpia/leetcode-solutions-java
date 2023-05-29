//implemeted in O()

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0;i<tokens.length;i++)
        {
            if(tokens[i].trim().equalsIgnoreCase( "+"))
            {
                int val1 = stack.pop();
                int val2 = stack.pop();
                stack.push(val1+val2);
                //                System.out.println("added:"+ (val1+val2));
            }
            else if(tokens[i].trim().equalsIgnoreCase("-"))
            {
                int val1 = stack.pop();
                int val2 = stack.pop();
                stack.push(val2-val1);
                //                System.out.println("added:"+(val1-val2));
            }
            else if(tokens[i].trim().equalsIgnoreCase("*"))
            {
                int val1 = stack.pop();
                int val2 = stack.pop();
                stack.push(val1*val2);
                //System.out.println("added:"+ (val1*val2));
            }
            else if(tokens[i].trim().equalsIgnoreCase("/"))
            {
                int val1 = stack.pop();
                int val2 = stack.pop();
                stack.push(val2/val1);
                //                System.out.println("added:"+ (val2/val1));
            }
            else
            {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}