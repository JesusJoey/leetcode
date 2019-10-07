/**
131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
*/

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        dfs(s, 0, list, res);
        return res;
    }
    
    public void dfs(String s, int pos, List<String> list, List<List<String>> res) {
        if (pos == s.length()) 
            res.add(new ArrayList<String>(list));
        else {
            for (int i = pos; i < s.length(); i++) {
                if (isPalindrome(s, pos, i)) {
                    list.add(s.substring(pos, i+1));
                    dfs(s, i+1, list, res);
                    list.remove(list.size() - 1);
                }
            }
        }
    }
    
    public boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo++) != s.charAt(hi--))
                return false;
        }
        return true;
    }
}
