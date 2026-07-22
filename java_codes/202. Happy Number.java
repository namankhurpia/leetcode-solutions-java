//BRUTE FORCE APPROACH
class Solution {
    public boolean isHappy(int n) {
        
        if(n<=0)
        {
            return false;
        }

        ArrayList<Integer> sumset = new ArrayList<Integer>();
        int sumsquare = n;
        
        while (sumsquare != 1 && !sumset.contains(sumsquare))
        {
            sumset.add(sumsquare);
            sumsquare = getsquaresum(sumsquare);
        }
        if(sumsquare == 1 )
        {
            return true;
        }
        else
        {   
            return false;
        }
    }

    public static int getsquaresum(int n)
    {
        String num = n+"";
        char arr[] = num.toCharArray();
        int sumsquare = 0;
        for(int i=0;i<arr.length;i++)
        {
            sumsquare = sumsquare + (Integer.parseInt(arr[i]+"")*Integer.parseInt(arr[i]+""));
        }
        return sumsquare;
    }
}

//optimal approach -1
//Floyd's cycle detection algorithm
class Solution {
    public boolean isHappy(int n) {

 
         int slow=n,fast=getsquaresum(n);
         while(n!=1 && slow!=fast)
         {
             slow = getsquaresum(slow);
             fast = getsquaresum(getsquaresum(fast));
             System.out.println("slow is:"+slow+" fast is:"+fast);
         }
         if(slow==1 || fast==1)
         {
             return true;
         }
         return false;
         
 
     }
 
     public static int getsquaresum(int n)
     {
         int sumsquare = 0;
         while(n!=0)
         {
             sumsquare = sumsquare + (n%10)*(n%10);
             n = n/10;
         }
         return sumsquare;
     }
 }


 //FASTEST APPROACH
//hardcoding the only cycle available
 class Solution {
    public boolean isHappy(int n) {

         Set<Integer> cycle = new HashSet<Integer>(Arrays.asList(4,16,37,58,89,145,42,20));
         while (n != 1 && !cycle.contains(n))
         {
             n = getsquaresum(n);
         }
         if(n == 1 )
         {
             return true;
         }
         else
         {   
             return false;
         }
         
 
     }
 
     public static int getsquaresum(int n)
     {
         int sumsquare = 0;
         while(n!=0)
         {
             sumsquare = sumsquare + (n%10)*(n%10);
             n = n/10;
         }
         return sumsquare;
     }
 }