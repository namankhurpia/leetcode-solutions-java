// BRUTE FORCE APPROACH, complexity - nlogn

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i:nums)
        {
            int c = map.getOrDefault(i,0);
            map.put(i,c+1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>( (a,b) -> map.get(a) - map.get(b));
        for(int i:map.keySet())
        {
            pq.add(i);
            if(pq.size()>k)
            {
                pq.poll();
            }
        }

        int []res = new int[k];
        for(int i=0;i<k;i++)
        {
            res[i] = pq.poll();
        }

        return res;

    }
}

//OPTIMAL SOLUTION - Complexity - O(n)

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i:nums)
        {
            int c = map.getOrDefault(i,0);
            map.put(i,c+1);
        }
        
        //array of list
        List<Integer> [] bucket = new List[nums.length+1];
        for(int i=0;i<bucket.length;i++)
        {
            bucket[i] = new ArrayList<Integer>();
        }

        for(int i:map.keySet())
        {
            bucket[map.get(i)].add(i);
        }

        ArrayList<Integer> flattened = new ArrayList<Integer>();
        for(int i=bucket.length-1;i>=0;i--)
        {
            for(int num:bucket[i])
            {
                flattened.add(num);
            }
        }

        int [] top = new int[k];
        for(int i=0;i<k;i++)
        {
            top[i] = flattened.get(i);
        }
        return top;
    }
}