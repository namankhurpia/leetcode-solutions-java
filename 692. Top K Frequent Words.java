class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        
        HashMap<String,Integer> map = new HashMap<>();
        for(String s:words)
        {
            map.put(s,map.getOrDefault(s,0)+1);
        }


        List<String> list = new ArrayList(map.keySet());

        Collections.sort(list, (i, j) -> map.get(i).equals(map.get(j)) ? i.compareTo(j) : map.get(j) - map.get(i));


        return list.subList(0,k);

    }
}