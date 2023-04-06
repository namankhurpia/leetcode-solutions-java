//BRUTE FORCE APPROACH
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int len1 = nums1.length;
        int len2 = nums2.length;

        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i:nums1)
        {
            arr.add(i);
        }
        for(int i:nums2)
        {
            arr.add(i);
        }
        Collections.sort(arr);
        int size = arr.size();
        if(size%2==0)
        {
            
            //even
            size = size/2;
            double res = arr.get(size -1) + arr.get(size) ;
            res= res/2;
            return res;
        }
        else
        {
            double res = arr.get((size/2)) ;
            return res;
        }

    }
}

//OPTIMAL SOLUTION
