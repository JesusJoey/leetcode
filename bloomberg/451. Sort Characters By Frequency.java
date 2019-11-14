class Solution {
    public String frequencySort(String s) {
        String res = "";
        if (s == null || s.length() == 0) return res;
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        List<Character> [] bucket = new List[s.length() + 1];
        for (char key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
            bucket[freq].add(key);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null) {
                for (char c : bucket[pos]) {
                    for (int i = 0; i < freqMap.get(c); i++)
                        sb.append(c);
                }    
            }
        }
        return sb.toString();
    }
}

//O(nlogm)
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<> 
        (new Comparator<Map.Entry<Character, Integer>> () {
            public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
                if (a.getValue() == b.getValue()) {
                    return Character.compare(a.getKey(), b.getKey());
                } else {
                    return Integer.compare(b.getValue(), a.getValue());
                }    
            }
       });
       maxHeap.addAll(map.entrySet());
       StringBuilder sb = new StringBuilder();
       while (!maxHeap.isEmpty()) {
           Map.Entry entry = maxHeap.poll();
           for (int i = 0; i < (int)entry.getValue(); i++) {
               sb.append(entry.getKey());
           }
       }                                 
       return sb.toString();  
    }
}