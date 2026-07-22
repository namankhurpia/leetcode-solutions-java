//OPTIMAL SOLUTION - O(n)

class Solution {
    public int trap(int[] height) {

        int maxleft[] = new int[height.length];
        int maxright[] = new int[height.length];

        int max = 0;
        maxleft[0] =0; 
        for(int i=1;i<maxleft.length;i++)
        {
            maxleft[i] = Math.max(max, height[i-1]);
            max = maxleft[i];
        }
        
        max = 0;
        
        for(int i=height.length-2;i>=0;i--)
        {
            maxright[i] = Math.max(max, height[i+1]);
            max = maxright[i];
        }
        maxright [0] = Math.max(max, height[0]);


        int water =0;
        for(int i=0;i<height.length;i++)
        {
            int v = Math.min(maxleft[i],maxright[i]);
            water = water +Math.max(v-height[i] ,0);
        }
        return water;
        
        
    }
}