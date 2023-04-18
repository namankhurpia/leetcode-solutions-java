//OPTIMAL O(n) solution - backtracking

class Solution {
    public List<String> generateParenthesis(int n) {
        
        List<String> list = new ArrayList<String>();
        dfs(list, "" , 0 , 0, n);
        return list;

    }

    public static void dfs(List<String> list ,String str, int opencount, int closecount, int max)
    {
        if(str.length()== max*2)
        {
            list.add(str);
            return;
        }

        if(opencount<max)
            dfs(list, str+"(", opencount+1, closecount, max);
        if(closecount<opencount)
            dfs(list , str+")" , opencount, closecount+1, max);

    }
}