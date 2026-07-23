class Solution:
    def maxArea(self, height: List[int]) -> int:
        p1 = 0
        p2 = len(height)-1
        maxarea = float('-inf')

        while(p1<p2):
        
            distance = p2-p1
            area = distance * min(height[p2],height[p1])
            maxarea = max(maxarea, area)
            #print("maxarea is", maxarea)

            if(height[p1]<height[p2]):
                p1+=1
            else:
                p2-=1
        
        return maxarea