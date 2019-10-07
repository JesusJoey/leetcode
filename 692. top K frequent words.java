/*
题目：692. Top K frequent words
	Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
	Output: ["i", "love"]
	Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.

    Follow up:
	Try to solve it in O(n log k) time and O(n) extra space.
*/

public List<String> topKFrequent(String[] words, int k) {
	List<String> res = new ArrayList<> ();
	if (words == null || words.length == 0) return res;

	Map<String, Integer> map = new HashMap<> ();
	for (String word:words) {
		map.put(word,map.getOrDefault(word,0)+1);
	}

	int max = 0;
	for (String key : map.keySet()) {
		if (max < map.get(key))
			max = map.get(key);
	}

	List<String>[] buckets = new ArrayList[max+1];
	for (String word : map.keySet()) {
		int count = map.get(word);
		if (buckets[count] == null)
			buckets[count] = new ArrayList<String>();
		buckets[count].add(word);
	}	

	for (int i = buckets.length-1; i>=0; i--) {
		int j = 0;
		//如果不考虑duplicate则不需要sort
		if (buckets[i] != null) {
			Collections.sort(buckets[i]);
		}
		//else continue;
		while (buckets[i] != null && k>0 && j<buckets[i].size()) {
			res.add(buckets[i].get(j));
			j++;
			k--;
		}
		if (k == 0) break;
	}
	/*
	for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
		if (bucket[pos] != null) {
			res.addAll(bucket[pos]);
		}
	}
	*/
	return res;
}

public List<String> topKFrequent(String[] words, int k) {
    
    List<String> result = new LinkedList<>();
    Map<String, Integer> map = new HashMap<>();
    for(int i=0; i<words.length; i++)
    {
        if(map.containsKey(words[i]))
            map.put(words[i], map.get(words[i])+1);
        else
            map.put(words[i], 1);
    }
    
    PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
             (a,b) -> a.getValue()==b.getValue() ? 
             b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
    );
    
    for(Map.Entry<String, Integer> entry: map.entrySet())
    {
        pq.offer(entry);
        if(pq.size()>k)
            pq.poll();
    }

    while(!pq.isEmpty())
        result.add(0, pq.poll().getKey());
    
    return result;
}