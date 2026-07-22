//BRUTE FORCE SOLUTION - nlogn

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        ArrayList<Integer> arr = new ArrayList<>();

        if(n==0)
        {
            
        }
        else
        {
            for(int i=0;i<m;i++)
            {
                arr.add(nums1[i]);
            }
            for(int i=0;i<n;i++)
            {
                arr.add(nums2[i]);
            }

            
        }

        Collections.sort(arr);
        int j=0;
        for(int i:arr)
        {
            nums1[j] = i;
            j=j+1;
        }
       

    }
}

//OPTIMISED SOLUTION - O(n)

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int nums1copy[] = new int[m];
       for(int i=0;i<m;i++)
       {
           nums1copy[i]=nums1[i];
       }

    int l=0,k=0;
       for(int i=0;i<m+n;i++)
       {
           if(l>=m)
           {
               nums1[i] = nums2[k];
                k=k+1;
           }
           else if(k>=n)
           {
                    nums1[i] = nums1copy[l];
                   l=l+1;
           }
           else
           {
               if(nums1copy[l]<nums2[k])
               {
                   nums1[i] = nums1copy[l];
                   l=l+1;
               }
               else
               {
                   nums1[i] = nums2[k];
                   k=k+1;
               }
           }
       }


        
        


    }
}