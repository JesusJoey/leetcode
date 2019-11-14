/*
460. LFU Cache

*/

class LFUCache {
    HashMap<Integer, Integer> values;
    HashMap<Integer, Integer> counts;
    //count , nodes
    HashMap<Integer, LinkedHashSet<Integer>> lists;
    int cap;
    int min;
    public LFUCache(int capacity) {
        cap = capacity;
        values = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }
    
    public int get(int key) {
        if (!values.containsKey(key))
            return -1;
        int count = counts.get(key);
        counts.put(key,count + 1);
        //因为又加了key进来，所以需要更新key的次数
        //先删除list中次数为count的key，再添加到count+1
        lists.get(count).remove(key);
        if (count == min && lists.get(count).size() == 0) 
            min++;
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
        return values.get(key);
        
    }
    
    public void put(int key, int value) {
        if (cap <= 0) return;
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }
        if (values.size() >= cap) {
            int del = lists.get(min).iterator().next();
            lists.get(min).remove(del);
            values.remove(del);
        }
        values.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(min).add(key);
    }
}