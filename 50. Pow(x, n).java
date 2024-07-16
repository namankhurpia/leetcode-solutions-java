//multiply the number itself - n times - gives TLE

class Solution {
    public double myPow(double x, int n) {
        Double res =1d;
        if(n==0 || x==1)
        {
            return 1;
        }
        if(n<0)
        {
            n=n*(-1);
            x=1/x;
        }
        for(int i=0;i<n;i++)
        {
            res = res*x;
        }
        return res;
    }
}


//optimal solution-
class Solution {
    public double myPow(double x, int n) {
        Double d = 1d;
        if(n==0)
        {
            return 1;
        }
        else if(n<0)
        {
            n=n*(-1);
            x=1/x;
        }
        d = dohalf(x,n);
        return d;


    }

    public static double dohalf(double x, int n)
    {
        if(n==0)
        {
            return 1.00;
        }
        else
        {
            double half =  dohalf(x,n/2);
            if(n%2==0)
            {
                return half*half;
            }
            else
            {
                return half*half*x;
            }
        }
    }

}