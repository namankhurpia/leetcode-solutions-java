//OPTIMISED SOLUTION - complexity - nlogn

class Solution {
    public int maxArea(int[] height) {
        
        int cor = 0;
        int area = 0;
        int leftpointer = 0;
        int rightpoointer = height.length-1;

        while(leftpointer!=rightpoointer)
        {
            cor = Math.min(height[leftpointer],height[rightpoointer]);
            area = Math.max(area ,cor * (rightpoointer - leftpointer)) ;
            //System.out.println("area: "+area + " rightpoointer:"+ rightpoointer +" leftpointer:"+leftpointer + " cor:"+cor);
            if(height[leftpointer]> height[rightpoointer])
            {
                rightpoointer = rightpoointer-1;
            }
            else
            {
                leftpointer = leftpointer+1;
            }
        }
        return area;

    }
}