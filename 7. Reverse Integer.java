class Solution {
    public int reverse(int x) {

try{
        if(x<0)
        {
            x = (-1)*x ;
            StringBuilder str = new StringBuilder(x+"");
            str = str.reverse();
            x = Integer.parseInt(str.toString());
            x = (-1)*x ;
            return x;
        }
        else
        {
            StringBuilder str = new StringBuilder(x+"");
            str = str.reverse();
            System.out.println(str);
            return Integer.parseInt(str.toString());
        }
}
catch(Exception e)
{
    return 0;
}
        
        
        
    }
}