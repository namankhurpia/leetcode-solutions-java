class Solution {
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        ArrayList<Integer> sublist = new ArrayList<Integer>();
        sublist.add(1);
        result.add(sublist);
        if(numRows==1)
            return result;

        ArrayList<Integer> sublist2 = new ArrayList<Integer>();
        sublist2.add(1);
        sublist2.add(1);
        result.add(sublist2);
        if(numRows==2)
            return result;

        
        for(int i=2;i<numRows;i++)
        {
            ArrayList<Integer> sub  = new ArrayList<Integer>();
            List<Integer> result_list = result.get(i-1);
            sub.add(1);
            for(int j=0;j<=result_list.size()-2;j++)
            {
                sub.add(result_list.get(j)+result_list.get(j+1));
            }
            sub.add(1);
            result.add(sub);
        }
        

        return result;


    }
}