//https://leetcode.com/problems/lru-cache/solutions/4322888/easiest-approach-using-hashmap-and-deque/

class LRUCache {

    
    Deque<Integer> deque = new ArrayDeque<>();
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    int capacity = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key))
        {
            deque.remove(key);
            deque.addFirst(key);
            
            return map.getOrDefault(key,-1);
        }
        else
        {
           
            return -1;
        }
        
        
    }
    
    public void put(int key, int value) {
       
       if(map.containsKey(key))
       {
           map.put(key,value);
           deque.remove(key);
           deque.addFirst(key);
       }
       else
       {

       
            if(deque.size()<capacity)
            {
                deque.addFirst(key);
                map.put(key,value);
            }
            else
            {
                    map.remove(deque.getLast());
                    deque.removeLast();
                
                    deque.addFirst(key);
                    map.put(key,value);   
            }
           
       }
    
       
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

