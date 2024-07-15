class Solution {
    public int[][] merge(int[][] intervals) {
        
        if(intervals.length<=1)
        {
            return intervals;
        }
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[0],b[0]));
        ArrayList<int[]> list= new ArrayList<>();
        int []newinterval = intervals[0];
        list.add(newinterval);

        for(int []i:intervals)
        {
            if(i[0]<=newinterval[1])
            {
                //merge
                newinterval[1] = Math.max(newinterval[1],i[1]);
            }
            else
            {
                newinterval = i;
                list.add(newinterval);
            }
        }

        return list.toArray(new int[list.size()][]);



    }
}