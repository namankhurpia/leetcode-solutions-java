class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        
        if(numerator==0)
        {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append("-");
        }

        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        sb.append(num / den);
       
        num = num % den;
        if(num==0)
        {
            return sb.toString();
        }
        sb.append(".");
        //map to keep track of repeating decimal pattern
        HashMap<Long,Integer> map = new HashMap<>();
        

        
        map.put(num, sb.length());

        while(num!=0)
        {
            num = num*10;
            sb.append(num/den);
            num = num % den;

            if(map.containsKey(num))
            {
                int index = map.get(num);
                sb.insert(index, "(");
                sb.append(")");
                break;
            }
            else
            {
                map.put(num, sb.length());
            }

        }
        return sb.toString();


    }
}