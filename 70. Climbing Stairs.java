// Memoization using Hashmap

class Solution {
    public int climbStairs(int n) {

        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        return climbStairs(map, n);
    }

    public int climbStairs(HashMap<Integer,Integer> map, int n)
    {
        
        if(n==0 || n==1)
        {
            return 1;
        }
        else
        {
            if(!map.containsKey(n))
            {
                map.put(n, climbStairs(map,n-1)+climbStairs(map,n-2));
            }
            return map.get(n);
        }

    }
}

//RECURSIVE SOLUTION - TLE
class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairs(n-1) + climbStairs(n-2);
    }
}