//OPTIMAL APPROACH

class Solution {
    public String addStrings(String num1, String num2) {
        
        int carry = 0;

        StringBuilder sb =new StringBuilder();

        for(int i=num1.length()-1, j = num2.length()-1; i>=0 || j>=0 ||carry==1;i--,j--)
        {
                int digit_num1 = i < 0 ? 0 : num1.charAt(i) - '0';
                int digit_num2 = j < 0 ? 0 : num2.charAt(j) - '0';
                int sum = carry + digit_num1 + digit_num2;
                //System.out.println(sum);
                carry = sum/10;
                sb.append(sum%10);

            
        }

        return sb.reverse().toString();

    }
}