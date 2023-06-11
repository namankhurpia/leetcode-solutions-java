//BEATS 99% - O(n)

class TimeMap {

    ArrayList<String> keys ;
    ArrayList<String> values ;
    ArrayList<Integer> times ;
    
    public TimeMap() {
        keys = new ArrayList<String>();
        values = new ArrayList<String>();
        times = new ArrayList<Integer>();
    }
    
    public void set(String key, String value, int timestamp) {
        
        keys.add(key);
        values.add(value);
        times.add(timestamp);

    }
    
    public String get(String key, int timestamp) {

        for(int i=keys.size()-1;i>=0;i--)
        {
            if(keys.get(i).equalsIgnoreCase(key) 
            && timestamp>=times.get(i))
            {
                return values.get(i);
            }
        }
        return "";
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

 //Using binary search

 class TimeMap {

    HashMap<String,ArrayList<Pair<Integer,String>>> map;
    
    public TimeMap() {
        map = new HashMap();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key))
        {
            map.put(key,new ArrayList());
        }

        map.get(key).add(new Pair(timestamp,value));
        

    }
    
    public String get(String key, int timestamp) {

        if(!map.containsKey(key))
        {
            return "";
        }

        if(timestamp<map.get(key).size())
        {
            return "";
        }

        int l =0;
        int r = map.get(key).size();

        while(l<r)
        {
            int mid = (l+r)/2;
            if(map.get(key).get(mid).getKey()<=timestamp)
            {
                l = mid+1;
            }
            else
            {
                r = mid;
            }
        }

        if(r==0)
        {
            return "";
        }

        return map.get(key).get(r-1).getValue();

    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */