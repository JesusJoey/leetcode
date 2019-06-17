/**
44. Wildcard Matching 
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence)

Input:
s = "acdcb"
p = "a*c?b"
Output: false

和leetcode 10很像
*/
public boolean isMatch(String s, String p) {
	int m = s.length(), n = p.length();
	boolean[][] dp = new boolean[m + 1][n + 1];
	dp[0][0] = true;
	for (int i = 1; i <= n; i++) {
		if (p.charAt(i - 1) == '*') {
			dp[0][i] = true;
		} else {
			break;
		}
	}

	for (int i = 1; i <= m; i++) {
		for (int j = 1; j <=n; j++) {
			if (p.charAt(j-1) != '*') {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) =='?')
					dp[i][j] = dp[i-1][j-1];
			} else {
				dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
			}
		}
	}
	return dp[m][n];
}