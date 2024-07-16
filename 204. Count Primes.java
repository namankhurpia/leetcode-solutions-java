// Tutorial - https://leetcode.com/problems/count-primes/solutions/1200796/js-python-java-c-easy-sieve-of-eratosthenes-solution-w-explanation/

class Solution {
    public int countPrimes(int n) {
        boolean []seen = new boolean[n];

        int count = 0;
        for(int i=2;i<n;i++)
        {
            if(seen[i]==false)
            {
                count++;
            }

            for(int j=2;i*j<n;j++)
            {
                
                seen[i*j] = true;
            }
        }

        

        return count;
    }
}

