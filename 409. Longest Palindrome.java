//OPTIMAL SOLUTION

class Solution {
    public int longestPalindrome(String s) {

        int arr[] = new int[123];
        for(int i=0;i<s.length();i++)
        {
            arr[s.charAt(i)] = arr[s.charAt(i)]+1;
        }


        Boolean once = true;
        int max = 0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]%2==0)
            {
                max = max+arr[i];
            }
            else
            {
                int contri = (arr[i]/2);
                max = max+(contri*2);
                if(once)
                {
                    max = max+1;
                    once = false;
                }
            }
            
        
        }

        
        return max;
        
        

        
    }
}