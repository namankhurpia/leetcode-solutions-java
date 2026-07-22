//BRUTE force - O(n^2)
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int length = heights.length;
        for (int i = 0; i < length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }
}

//best case - O(n)

class Solution {
    public int largestRectangleArea(int[] heights) {

        int maxarea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> indexes = new Stack<Integer>();
        int start = 0;
        for (int i=0;i<heights.length;i++)
        {
            start = i;
            while(!stack.isEmpty() && stack.peek()>heights[i])
            {
                int height = stack.pop();
                int index = indexes.pop();
                maxarea = Math.max(maxarea, height*(i-index));
                start = index;
            }
            stack.push(heights[i]);
            indexes.push(start);
        }
  
        ArrayList<Integer> stacklist = new ArrayList<Integer>(stack);
        ArrayList<Integer> indexlist = new ArrayList<Integer>(indexes);
     
        for(int i=0;i<stacklist.size();i++)
        {
            maxarea = Math.max(maxarea, stacklist.get(i) * (heights.length-indexlist.get(i)));
        }
 
        return maxarea;

    }
}