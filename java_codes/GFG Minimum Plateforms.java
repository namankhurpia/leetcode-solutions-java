//Minimum Platforms
//https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1#

class Solution
{
    //Function to find the minimum number of platforms required at the
    //railway station such that no train waits.
    static int findPlatform(int arr[], int dep[], int n)
    {
        // add your code here
        Arrays.sort(arr);
        Arrays.sort(dep);
        
        int i=1,j =0;
        int pla =1;
        int res =1;
        
        while( i<n && j<n)
        {
            if(arr[i]<=dep[j])
            {
                pla ++;
                i++;
            }
            
            else if(arr[i]> dep[j])
            {
                pla--;
                j++;
            }
            
            res = Math.max(res,pla);
        }
        return res;
        
    }
    
}