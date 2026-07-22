//BRUTE FORCE SOLUTION
class Solution {
    public int countOdds(int low, int high) {
        int count = 0;
        for(int i=low;i<=high;i++)
        {
            if(i%2!=0)
            {
                count+=1;
            }
        }
        return count;
       

    }
}

//OPTIMAL SOLUTION

class Solution {
    public int countOdds(int low, int high) {

        if(low%2==0)
        {
            low+=1;
        }

        if(low>high)
        {
            return 0;
        }
        return (high-low)/2 +1;

        

    }
}