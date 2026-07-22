//Brute force O(m.n)

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        int res[] = new int[nums1.length];
        for(int i=0;i<res.length;i++)
        {
            res[i] = -1;
        }
        for(int i=0;i<nums1.length;i++)
        {
            int numtofind = nums1[i];
            boolean find = false;
            for(int j=0;j<nums2.length;j++)
            {
                if(numtofind==nums2[j])
                {
                    find = true;
                }

                if(find==true)
                {
                    if(nums2[j]>numtofind)
                    {
                        res[i] = nums2[j];
                        break;
                    }
                    
                    
                }
                
            }
        }

        return res;


    }
}


//O(n) - using hashmap and stacks

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        HashMap<Integer,Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<Integer>();

        for(int i:nums2)
        {
            while(!stack.isEmpty() && stack.peek()<i)
            {
                int temp = stack.pop();
                map.put(temp, i);
                
            }
            stack.push(i);
        }

        for(int i=0;i<nums1.length;i++)
        {
            nums1[i] = map.getOrDefault(nums1[i],-1);
        }

        return nums1;




    }
}