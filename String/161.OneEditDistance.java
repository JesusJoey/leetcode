/**
161. one edit distance

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
*/
class Solution {
	public boolean isOneEditDistance(String s, String t) {
		int n = s.length(), m = t.length();
		if (n > m) 
			return isOneEditDistance(t, s);
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) != t.charAt(i)) {
				if (m == n) {
					return s.substring(i + 1).equals(t.substring(i + 1));
				} else {
					return s.substring(i).equals(t.substring(i + 1));
				}
			}
		}
		return ( n + 1 == m);
	}
}
// Using helper functions
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int n = s.length(), m = t.length();
        if (n - m == 1) return deleted(s, t);
        else if (m - n == 1) return deleted(t, s);
        else if (m == n) return replaced(t, s);
        else return false;
        
    }
    
    public boolean deleted(String s, String t) {
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(i) != t.charAt(i))
                return s.substring(i + 1).equals(t.substring(i));
        }
        return true;
    }

    public boolean replaced(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i))
                return s.substring(i + 1).equals(t.substring(i + 1));
        }
        return false;
    }
    
}