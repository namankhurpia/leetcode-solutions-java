//BRUTE FORCE SOLUTION - O(nlog n)

class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[0]-arr[1];
        for(int i=0;i<arr.length-1;i++)
        {
            if(arr[i]-arr[i+1]!=diff)
            {
                return false;
            }
        }
        return true;

    }
}

//Optimised - O(n)

class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i=0;i<arr.length;i++)
        {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        
        if((max-min)%(arr.length-1)!=0)
        {
            return false;
        }

        int d = (max - min) / (arr.length-1);
        if(d==0)
        {
            return true;
        }
            


        HashSet<Integer> set = new HashSet<Integer>();
        for(int i:arr)
        {
            set.add(i);
        }

        if(arr.length!=set.size())
        {
            return false;
        }


        
        for(int i=0;i<arr.length;i++)
        {
            if((arr[i]-min) % d != 0)
            {
                return false;
            }
        }

        

    return true;
    }

}