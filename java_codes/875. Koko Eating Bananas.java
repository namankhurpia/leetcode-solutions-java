//O(nlogn) 

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        
        int r =Integer.MIN_VALUE;
        for(int i:piles)
            r = Math.max(i,r);
        int l = 1;
        int res = r;

        int mid=0;
        int c=0;
        while(l<=r)
        {
            mid = (l)+(r-l)/2;
            c=0;
            for(int i:piles)
            {
                
                int remain = i % mid;
                int div = (i/mid);
                if(remain!=0)
                {
                    div++;
                }
                
                //System.out.println("for pile:"+i +" mid id:"+ mid+",div is"+div);
                c=c+div;
            }
            
            if(c<=h)
            {
                res=Math.min(res,mid);
                r = mid-1;
            }
            else
            { 
                l = mid+1;
            }
                

        }
        return res;
        

    }
}