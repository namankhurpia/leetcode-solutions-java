//BRUTE FORCE APPROACH

class MyHashMap {
    ArrayList<Integer> keys;
    ArrayList<Integer> values;

    public MyHashMap() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }
    
    public void put(int key, int value) {
        if(!keys.contains(key))
        {
            keys.add(key);
            values.add(value);
        }
        else
        {
            int pos = 0;
            
            for(int i:keys)
            {
                if(i==key)
                {
                    break;
                }
                pos++;
            }

            values.set(pos, value);

        }
        
        
    }
    
    public int get(int key) {
        int pos =-1;
        for(int i=0;i<keys.size();i++)
        {
            if(key==keys.get(i))
            {
                pos = i;
                break;
            }
        }

        
        if(pos!=-1)
        {
            return values.get(pos);
        }
        else
        {
            return pos;
        }
        


    }
    
    public void remove(int key) {
        int pos =-1;
        for(int i=0;i<keys.size();i++)
        {
            if(key==keys.get(i))
            {
                pos = i;
                break;
            }
        }
        if(pos!=-1)
        {
            keys.remove(pos);
            values.remove(pos);
        }
        else
        {

        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

 class MyHashMap {

   int[] data;
    public MyHashMap() {
        data = new int[1000001];
        Arrays.fill(data, -1);
    }
    public void put(int key, int val) {
        data[key] = val;
    }
    public int get(int key) {
        return data[key];
    }
    public void remove(int key) {
        data[key] = -1;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */


 //BEST OPTIMAL - AVOID COLLISION

 class MyHashMap {

    int size = 10000;
    int mul = 34;
    ListNode [] data;

    public MyHashMap() {
        data = new ListNode[size];
    }

    int hash(int key)
    {
        return (int)(key*mul %size);
    }
    
    public void put(int key, int value) {
        int h = hash(key);
        remove(key);
        ListNode node = new ListNode(key,value,data[h]);
        data[h] = node;
    }
    
    public int get(int key) {
        
        int h = hash(key);
        ListNode node = data[h];
        while(node!=null)
        {
            if(node.key == key)
            {
                return node.val;
            }
            node = node.next;
        }
        return -1;
    }
    
    public void remove(int key) {
        int h = hash(key);
        ListNode node = data[h];
        if(node==null) return;
        if(node.key == key){
            data[h] = node.next;
        }
        else
        {
            while(node.next!=null)
            {
                if(node.next.key == key)
                {
                    node.next = node.next.next;
                    return;
                }
                node = node.next;
            }
        }
        
    }
}

class ListNode {
    int key, val;
    ListNode next;
    public ListNode(int key, int val, ListNode next) {
        this.key = key;
        this.val = val;
        this.next = next;
    }
}
/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */