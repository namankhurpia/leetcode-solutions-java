class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        //BRUTE FORCE
        /*
        for(int i=0;i<nums.length;i++)
        {
            for(int j=0;j<nums.length;j++)
            {
                if(i!=j)
                {
                    if(nums[i]+nums[j]==target)
                    {
                        return new int[]{i,j};
                    }
                }
            }
        }

        return new int[]{1,2};
        */

        //OPTIMISED
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();

        for(int i=0;i<nums.length;i++)
        {
            int complement = target - nums[i];
            if(map.containsKey(complement))
            {
                int val = map.get(complement);
                return new int[]{i,val};
            }
            map.put(nums[i],i);

        }
        return new int [] {1,2};

    }
}


//python

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        for i in range(0, len(nums)):
            for j in range (i+1, len(nums)):
                if target - nums[i] == nums[j]:
                    return [i,j]

//using hashmap / dictionary
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:

        dictionary = {}
        for i in range(0, len(nums)):
            dictionary [nums[i]] = i
        
        for i in range(0,len(nums)):
            complement = target -nums[i]
            if complement in dictionary and dictionary[complement] !=i:
                return [i, dictionary[complement]]        


//using one pass approach
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:

        dictionary = {}
        for i in range(0, len(nums)):
            complement = target - nums[i]
            if(complement in dictionary):
                return [i,dictionary[complement]]
            dictionary[nums[i]] = i
        
        return null

