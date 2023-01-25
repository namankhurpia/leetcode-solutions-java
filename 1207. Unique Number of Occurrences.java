class Solution {
    public boolean uniqueOccurrences(int[] arr) {

        if(arr.length==2)
        {
            if(arr[0]==arr[1])
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        ArrayList<Integer> quan = new ArrayList<Integer>();


        Arrays.sort(arr);
        int count =1 ;
        for(int i=0;i<arr.length-1;i++)
        {
            if(arr[i]==arr[i+1])
            {
                count = count+1;
            }
            else
            {
                if(quan.contains(count))
                {
                    return false;
                }
                quan.add(count);
                count = 1;
            }
        }
        return true;
        
    }
}

//OPTIMAL SOLUTION

class Solution {
    public boolean uniqueOccurrences(int[] arr) {

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        Set<Integer> freqSet = new HashSet<>(freq.values());
        return freq.size() == freqSet.size();
        
    }
}