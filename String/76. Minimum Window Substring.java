/**
76. Minimum Window Substring

Given a string S and a string T, find the minimum window in S 
which will contain all the characters in T in complexity O(n).

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Basically it's a problem of sliding window. The brief algorithm is:

Use start/end pointer to indicate current valid window.
Go through the string s by moving forward the end pointer.
For each step, try to move forward the start pointer to see 
if we can still get a valid window.
Remember the minimum window and return it.

1.Use a HashMap:
"how many times for a character we need to match the requirement".

2. Use count:
 "how many valid characters we have gotten".
 If count == s.length(), it means we have gotten all characters needed 
 and current window is valid.
*/

class Solution {
	public String minWindow(String s, String t) {
		int start = 0, count = 0;
		String res = "";
		HashMap <Character, Integer> map = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			char ch = t.charAt(i);
			map.put(ch,map.getOrDefault(ch,0)+1);
		}
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (map.containsKey(ch)) {
				map.put(ch,map.ge(ch) - 1);
				if (map.get(ch >= 0)) {
					count++;
				}
			}
			while (count == t.length()) {
				if (res.length() == 0 || (i - start + 1 < res.length())) {
					res = s.substring(start, i + 1);
				}
				ch = s.charAt(start);
				if (map.containsKey(ch)) {
					//map.put(ch, map.get(ch) + 1);
					if (map.get(ch) >= 0) count--;
				}
				start++;
			}
		}
		return res;
	}
}
