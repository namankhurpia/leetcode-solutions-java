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


//my approach-

class Solution {
    public int maxArea(int[] height) {

        int l = 0;
        int r = height.length-1;
        int res = Integer.MIN_VALUE;

        while(l<r)
        {
            int minheight = Math.min(height[l],height[r]);
            res = Math.max(res, area(l,r,minheight));
            if(height[l]<height[r])
            {
                l++;
            }
            else
            {
                r--;
            }
            
        }

        return res;
        
    }

    public static int area(int l, int r, int minheight)
    {
        return (r-l)*minheight;
    }
}