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

 