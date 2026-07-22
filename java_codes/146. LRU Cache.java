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



 // for o(1) time complexity we can use this
 class LRUCache {

    Node head = new Node(0,0);
    Node tail = new Node(0,0);

    HashMap<Integer,Node> map = new HashMap<Integer,Node>();
    int capacity =0;

    public LRUCache(int capacity) {
       this.capacity = capacity;
       head.next = tail;
       tail.next = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key))
        {
            Node tmp = map.get(key);
            remove(tmp);
            insert(tmp);
            return tmp.value;
        }
        else
        {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            remove(map.get(key));
            
        }
        if (map.size() == capacity) {
            remove(tail.prev);
        }
        
        insert(new Node(key,value));
        

    }

    public void remove(Node node)
    {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insert(Node node)
    {
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
}

class Node
{
    Node prev, next;
    int key, value;

    Node(int key, int value)
    {
        this.key = key;
        this.value = value;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */