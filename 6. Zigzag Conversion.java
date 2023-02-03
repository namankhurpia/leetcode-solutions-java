class Solution {
    public String convert(String s, int numRows) {

        if(numRows==1)
        {
            return s;
        }

        StringBuilder builder = new StringBuilder();
        int len = s.length();
        int section1 = 2* (numRows -1);

        for(int i=0;i<numRows;i++)
        {
            int index = i ;
            while(index<len)
            {
                builder.append(s.charAt(index));
                if(i!=0 && i!=numRows-1)
                {
                    int section2 = section1- (2*i);
                    int section3 = index + section2;

                    if(section3<len){
                        builder.append(s.charAt(section3));
                    }
                }

                index = index + section1;
                
            }
        }
        return builder.toString();
        
    }
}