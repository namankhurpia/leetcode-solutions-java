//OPTIMAL SOLUTION
class Solution {
    public List<String> fizzBuzz(int n) {

        ArrayList<String> res = new ArrayList<String>();
        for(int i=1;i<=n;i++)
        {
            //System.out.println("I:"+i);
            if(i%5==0 && i%3==0)
            {
                res.add("FizzBuzz");
            }   
            else if(i%5==0)
            {
                res.add("Buzz");
            }
            else if(i%3==0)
            {
                res.add("Fizz");
            }
            else
            {
                res.add(i+"");
            }
        }
        return res;
        
        
        
    }
}