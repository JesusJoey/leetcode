/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

*/

	public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.isEmpty()) {
			return false;
		}

		int len = s.length();
		boolean[] dp = new boolean[len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j <= i; j++) {
				// NOTE: we are going to update dp only for below two scenarios, so avoided
				// making unecessary computation until this condition is met
				if (j == 0 || dp[j - 1]) {
					String sub = s.substring(j, i + 1);
					if (wordDict.contains(sub)) {
						dp[i] = true;
					}
				}
			}
		}
		return dp[len - 1];
	}

/*
140. Word Break II

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
*/


public class Solution {
    
	//solution 1
	public List<String> wordBreak(String s, Set<String> wordDict) {
	    return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
	}       

	// DFS function returns an array including all substrings derived from s.
	List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
	    if (map.containsKey(s)) 
	        return map.get(s);
	        
	    LinkedList<String>res = new LinkedList<String>();     
	    if (s.length() == 0) {
	        res.add("");
	        return res;
	    }               
	    for (String word : wordDict) {
	        if (s.startsWith(word)) {
	            List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
	            for (String sub : sublist) 
	                res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
	        }
	    }       
	    map.put(s, res);
	    return res;
	}

	//solution 2
    HashMap<String,List<String>> map = new HashMap<String,List<String>>();
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0) {
            return res;
        }
        if(map.containsKey(s)) {
            return map.get(s);
        }
        if(wordDict.contains(s)) {
            res.add(s);
        }
        for(int i = 1 ; i < s.length() ; i++) {
            String t = s.substring(i);
            if(wordDict.contains(t)) {
                List<String> temp = wordBreak(s.substring(0 , i) , wordDict);
                if(temp.size() != 0) {
                    for(int j = 0 ; j < temp.size() ; j++) {
                        res.add(temp.get(j) + " " + t);
                    }
                }
            }
        }
        map.put(s , res);
        return res;
    }
}
	//solution 3
	public List<String> wordBreak(String s, Set<String> wordDict) {
	    List<Integer>[] starts = new List[s.length() + 1]; // valid start positions
	    starts[0] = new ArrayList<Integer>();
	    
	    int maxLen = getMaxLen(wordDict);
	    for (int i = 1; i <= s.length(); i++) {
	        for (int j = i - 1; j >= i - maxLen && j >= 0; j--) {
	            if (starts[j] == null) continue;
	            String word = s.substring(j, i);
	            if (wordDict.contains(word)) {
	                if (starts[i] == null) {
	                    starts[i] = new ArrayList<Integer>();
	                }
	                starts[i].add(j);
	            }
	        }
	    }
	    
	    List<String> rst = new ArrayList<>();
	    if (starts[s.length()] == null) {
	        return rst;
	    }
	    
	    dfs(rst, "", s, starts, s.length());
	    return rst;
	}


	private void dfs(List<String> rst, String path, String s, List<Integer>[] starts, int end) {
	    if (end == 0) {
	        rst.add(path.substring(1));
	        return;
	    }
	    
	    for (Integer start: starts[end]) {
	        String word = s.substring(start, end);
	        dfs(rst, " " + word + path, s, starts, start);
	    }
	}

	private int getMaxLen(Set<String> wordDict) {
	    int max = 0;
	    for (String s : wordDict) {
	        max = Math.max(max, s.length());
	    }
	    return max;
	}

	public List<String> wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return new ArrayList<String>();
        }
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int max_length = 0;
        for (String str : wordDict) {
            max_length = Math.max(max_length, str.length());
        }
        for (int i = 1; i <= n; i++) {
            for (int j = Math.max(0, i - max_length); j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        return DFS(s, wordDict, map, dp);
    }
    
    private List<String> DFS(String s, Set<String> wordDict, HashMap<String, List<String>> map, boolean[] dp) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<String>();
        if (s.length() == 0) {
            return res;
        }
        if (wordDict.contains(s)) {
            res.add(s);
        }
        int end = s.length();
        for (int i = end - 1; i >= 0; i--) {
            String cur = s.substring(i, end);
            if (wordDict.contains(cur) && dp[i]) {
                List<String> list = DFS(s.substring(0, i), wordDict, map, dp);
                for (String str : list) {
                    res.add(str + " " + cur);
                }
            }
        }
        map.put(s, res);
        return res;
    }

