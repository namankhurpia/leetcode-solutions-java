// space -  O(2n) and time  - O(n)

class Solution {
    public boolean isPalindrome(String s) {
         

        s = s.replaceAll("[^a-zA-Z0-9]","");
        s = s.replaceAll(" ","");
        s = s.toLowerCase();
        StringBuilder str = new StringBuilder(s);
        return (str.reverse().toString().equalsIgnoreCase(s));
        

    }
}

//constant space complexity and time complexity - space- O(1), time - O(n)

class Solution {
    public boolean isPalindrome(String s) {

        s = s.replaceAll("[^a-zA-Z0-9]","");
        s = s.replaceAll(" ","");
        s = s.toLowerCase();
        
        int left = 0;
        int right = s.length()-1;
        while(left<right)
        {
            if(s.charAt(left) == s.charAt(right))
            {
                left = left+1;
                right = right-1;
                continue;
            }
            else
            {
                return false;
            }
            
        }
        return true;
        


    }
}