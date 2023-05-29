//optimal - O(n)

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int res[] = new int[temperatures.length];
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();

        for (int i=0;i<temperatures.length;i++)
        {
            while(!stack.isEmpty() && temperatures[i]>temperatures[stack.peek()])
            {
                int day = stack.pop();
                res[day] = i - day;
            }
            stack.push(i);
            
        }

        return res;
        
        
    }
}