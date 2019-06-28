/**
97. Interleaving String

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
*/


神仙方法：
/**
Start from indices 0, 0, 0 and compare s1[i] == s3[k] or s2[j] == s3[k]
Return valid only if either i or j match k and the remaining is also valid
Caching is the key to performance. This is very similar to top down dp
Only need to cache invalid[i][j] since most of the case s1[0 ~ i] and s2[0 ~ j]
does not form s3[0 ~ k]. 
*/

public boolean isInterleave(String s1, String s2, String s3) {
	char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
	int m = s1.length(), n = s2.length();
	if (m + n != s3.length()) 
		return false;
	return dfs(c1, c2, c3, 0, 0, 0, new boolean[m + 1][n + 1]);
}

public boolean dfs(char[] c1, char[] c2, char[] c3, int i, int j, int k, boolean[][] invalid) {
	if (invalid[i][j]) return false;
	if (k == c3.length) return true;
	boolean valid =
		i < c1.length && c1[i] == c3[k] && dfs(c1, c2, c3, i + 1, j ,k + 1, invalid) ||
		j < c2.length && c2[j] == c3[k] && dfs(c1, c2, c3, i, j + 1, k + 1, invalid);
	if (!valid) invalid[i][j] = true;
	return valid;
}

DP Solution:
public boolean isInterleave(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) return false;
    char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();

    boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
    for (int i = 0; i < s1.length() + 1; i++) {
        for (int j = 0; j < s2.length() + 1; j++) {
            if (i == 0 && j == 0)
                dp[i][j] = true;
            else if (i == 0)
                dp[i][j] = dp[i][j - 1] && (c2[j - 1] == c3[i + j - 1]);
            else if (j == 0)
                dp[i][j] = dp[i -1][j] && (c1[i - 1] == c3[i + j - 1]);
            else 
                dp[i][j] = (dp[i][j - 1] && c2[j - 1] == c3[i + j - 1]) || 
                            (dp[i -1][j] && c1[i - 1] == c3[i + j - 1]);
        }
    }
    return dp[s1.length()][s2.length()];
}