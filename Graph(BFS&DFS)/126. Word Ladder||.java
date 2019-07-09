/**
126. Word Ladder ||

Given two words (beginWord and endWord), and a dictionary's word list, 
find all shortest transformation sequence(s) from beginWord to endWord, such that:

1. Only one letter can be changed at a time
2. Each transformed word must exist in the word list. 
Note that beginWord is not a transformed word.

*/
public class Solution {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>(wordList);
		List<List<String>> res = new ArrayList<>();
		if (!dict.contains(endWord)) {
			return res;
		}
		Map<String, List<String>> map = getChildren(beginWord, endWord, dict);
		List<String> path = new ArrayList<>();
		path.add(beginWord);
		findLadders(beginWord, endWord, map, res, path);
		return res;
	}

	//dfs
	public void findLadders(String beginWord, String endWord, Map<String, List<String>> map,
							List<List<String>> res, List<String> path) {
		if (beginWord.equals(endWord))
			res.add(new ArrayList<>(path));
		if (!map.containsKey(beginWord)) {
			return;
		}
		for (String next : map.get(beginWord)) {
			path.add(next);
			findLadders(next, endWord, map, res, path);
			path.remove(path.size() - 1);
		}
	}
	//bfs
	public Map<String, List<String>> getChildren(String beginWord, String endWord, Set<String> dict) {
		Map<String, List<String>> map = new HashMap<>();
		Set<String> start = new HashSet<>();
		start.add(beginWord);
		Set<String> end = new HashSet<>();
		Set<String> visited = new HashSet<>();
		end.add(endWord);
		boolean found = false, isBackward = false;
		while (!start.isEmpty() && !found) {
			if (start.size() > end.size()) {
				Set<String>  tmp = start;
				start = end;
				end = tmp;
				isBackward = !isBackward;
			}
			Set<String> set = new HashSet<>();
			for (String cur : start) {
				visited.add(cur);
				for (String next : getNext(cur, dict)) {
					if (visited.contains(next) || start.contains(next)) {
						continue;
					}
					if (end.contains(next)) {
						found = true;
					}
					set.add(next);
					String parent = isBackward ? next : cur;
					String child = isBackward ? cur : next;
					if (!map.containsKey(parent)) {
						map.put(parent, new ArrayList<>());
					}
					map.get(parent).add(child);
				}
			}
			start = set;
		}
		return map;
	}

	private List<String> getNext(String cur, Set<String> dict) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char ch = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				if (c == ch) continue;
				chars[i] = c;
				String next = new String(chars);
				if (dict.contains(next)) {
					res.add(next);
				}
			}
			chars[i] = ch;
		}
		return res;
	}
}

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		Set<String> wordDict = new HashSet<>(wordList);
		List<List<String>> result = new LinkedList<>();
		Queue<List<String>> queue = new LinkedList<>();
		queue.offer(Arrays.asList(beginWord));
		wordDict.remove(beginWord);

        while (!queue.isEmpty()) {
			int n = queue.size();
			Set<String> toRemove = new HashSet<>();
			for (int k = 0; k < n; k++) {
				List<String> path = queue.poll();
				String word = path.get(path.size() - 1);
				char[] chars = word.toCharArray();
				for (int i = 0; i < word.length(); i++) {
					char c = chars[i];
					for (char exch = 'a'; exch <= 'z'; exch++) {
						if (c != exch) {
							chars[i] = exch;
							String nextWord = String.valueOf(chars);
							if (wordDict.contains(nextWord)) {
								toRemove.add(nextWord);
								List<String> newPath = new ArrayList<>(path);
								newPath.add(nextWord);
								queue.offer(newPath);

								if (nextWord.equals(endWord)) {
									result.add(newPath);
								}
							}
						}
					}
					chars[i] = c;
				}
			}
			if (!result.isEmpty()) {
				break;
            }
			if (!toRemove.isEmpty()) {
				wordDict.removeAll(toRemove);
			}
		}

        return result;
    }
}