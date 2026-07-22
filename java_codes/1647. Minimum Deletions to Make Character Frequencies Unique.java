//Brute force solution - O(n*26)

class Solution {
    public int minDeletions(String s) {
        
        int arr[] = new int[26];
        for(int i=0;i<s.length();i++)
        {
            arr[s.charAt(i)-'a']++; 
        }

        int delete = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i=0;i<26;i++)
        {
            
            while(arr[i]>0 && set.contains(arr[i]))
            {
                System.out.println("deleted " +arr[i] );
                delete+=1;
                arr[i] = arr[i]-1;
                
            }
            set.add(arr[i]);
            
        }
        return delete;


    }
}

//O(n)

class Solution {
    public int minDeletions(String s) {
        
        int arr[] = new int[26];
        for(int i=0;i<s.length();i++)
        {
            arr[s.charAt(i) - 'a']++; 
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]>0)
                pq.add(arr[i]);
        }

        int delete =0;

        while(pq.size()>1)
        {
            int ele = pq.remove();
            if(pq.peek() == ele)
            {
                if(ele-1>0)
                    pq.add(ele-1);
                delete++;
            }
        }

        return delete;

    }
}