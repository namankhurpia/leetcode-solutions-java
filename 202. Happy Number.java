//BRUTE FORCE APPROACH
class Solution {
    public boolean isHappy(int n) {
        
        if(n<=0)
        {
            return false;
        }

        ArrayList<Integer> sumset = new ArrayList<Integer>();
        int sumsquare = n;
        
        while (sumsquare != 1 && !sumset.contains(sumsquare))
        {
            sumset.add(sumsquare);
            sumsquare = getsquaresum(sumsquare);
        }
        if(sumsquare == 1 )
        {
            return true;
        }
        else
        {   
            return false;
        }
    }

    public static int getsquaresum(int n)
    {
        String num = n+"";
        char arr[] = num.toCharArray();
        int sumsquare = 0;
        for(int i=0;i<arr.length;i++)
        {
            sumsquare = sumsquare + (Integer.parseInt(arr[i]+"")*Integer.parseInt(arr[i]+""));
        }
        return sumsquare;
    }
}

//optimal approach 
