//optimal - O(Nlogn)

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;

        TreeMap<Integer, Integer> map = new TreeMap<Integer,Integer>();
        for(int i=0;i<n;i++)
        {
            map.put(target-position[i],speed[i]);
        }

        int c =0;
        Double r = -1d;
        for (Map.Entry<Integer, Integer> e:map.entrySet())
        {
            int d = e.getKey();
            int s = e.getValue();
            Double t = (1.0*d)/s; 
            if(t>r)
            {
                c++;
                r = t;
            }

        }
        return c;




    }
}