class Solution{
    
    // Function to find floor of x
    // arr: input array
    // n is the size of array
    static int findFloor(long arr[], int n, long x)
    {
        
        return findFloorUtil(arr, 0, n-1, x);
        
    }
    
    static int findFloorUtil(long arr[] , int low, int high, long x)
    {
        
        if(low>high)
        {
            return -1;
        }

        
        if(x>=arr[high])
        {
            return high;
        }
        
        int mid = (low+high)/2;
        
        if(arr[mid]==x)
        {
            return mid;
        }
        
        
        if (mid > 0 && arr[mid-1] <= x && x < arr[mid]) 
        {
            return mid-1; 
        }
            
        
        if(x<arr[mid])
        {
            return findFloorUtil(arr, low, mid-1, x);
        }
        else 
        {
            return findFloorUtil(arr, mid+1, high, x);
        }
        
        
        
    }
    
}
