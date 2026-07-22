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
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        int merge [] = new int[len1+len2];
        

        int i = 0;
        int j = 0;
        int z = 0;

        while(i<len1 || j<len2)
        {
            
            if(i==len1)
            {
                merge[z] = nums2[j];
                j++;
            }
            else if(j==len2)
            {
                merge[z] = nums1[i];
                i++ ; 
            }
            else
            {
                    if(nums1[i]<nums2[j])
                    {
                        merge[z] = nums1[i];
                        i++ ; 
                    }
                    else
                    {
                        merge[z] = nums2[j];
                        j++;
                    }
            }
            
            z++;
        }
        

        return Median(merge);


    }

    public static Double Median(int [] arr)
    {
        if(arr.length%2==0)
        {
            //even
            Double res =  new Double( (arr[arr.length/2]+arr[(arr.length/2)-1])   );
            return res/2;
        }
        else
        {
            //odd
            return new Double(arr[(arr.length/2)]);
        }

    }

}