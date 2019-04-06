/*
题目：692. Top K frequent words
	Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
	Output: ["i", "love"]
	Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.

    Follow up:
	Try to solve it in O(n log k) time and O(n) extra space.
*/

public class Solution {
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
		return res;

	}
}