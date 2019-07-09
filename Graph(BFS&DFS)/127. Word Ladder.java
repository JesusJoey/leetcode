/**
127. Word Ladder

Given two words (beginWord and endWord), and a dictionary's word list, 
find the length of shortest transformation sequence from beginWord to endWord, 
such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. 
Note that beginWord is not a transformed word.
*/


class Solution {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Queue<String> queue = new LinkedList<String>();
		//Set<String> wordDict = new HashSet<String>();
		queue.add(beginWord);
		queue.add(null);

		Set<String> visited = new HashSet<String>();
		visited.add(beginWord);

		int level = 1;

		while (!queue.isEmpty()) {
			String str = queue.poll();

			if (str != null) {
				for (int i = 0; i < str.length(); i++) {
					char[] ch = str.toCharArray();

					for (char c = 'a'; c <= 'z'; c++) {
						ch[i] = c;

						String word = new String(ch);
						if (word.equals(endWord) && visited.contains(word)) return level + 1;

						if (wordList.contains(word) && !visited.contains(word)) {
							queue.add(word);
							visited.add(word);
							wordList.remove(word);
						}
					}
				}
			} else {
				level++;
				if (!queue.isEmpty()) {
					queue.add(null);
				}
			}
		}
		return 0;
	}
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int q=0; q < size; q++) {
                char[] cur = queue.poll().toCharArray();
                for (int i=0; i < cur.length; i++) {
                    char tmp = cur[i];
                    for (char c='a'; c <= 'z'; c++) {
                        cur[i] = c;
                        String dest = new String(cur);
                        if (dict.contains(dest)) {
                            if (dest.equals(endWord)) return level+1;
                            queue.add(dest);
                            dict.remove(dest);
                        }
                    }
                    cur[i] = tmp;
                }
            }
            level++;
        }
        return 0;
    }
}