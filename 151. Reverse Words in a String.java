class Solution {
    public String reverseWords(String s) {
        
        s = s.trim();
        String res = "";
        String []arr = s.split(" ");
        for(int i=arr.length-1;i>=0;i--)
        {
            if(arr[i].trim()=="")
            {

            }
            else if(i==0)
            {
                res+=arr[i];
            }
            else
            {
                res+=arr[i]+" ";
            }
            
        }
        return res;

    }
}