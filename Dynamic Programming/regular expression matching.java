/**
10. Regular Expression Matching
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. 
*/

class Solution {
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.length() > 1 && p.charAt(1) == '*') {  // second char is '*'
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
                return isMatch(s.substring(1), p);
            }
            return false;
        } else {                                     // second char is not '*'
            if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
                return isMatch(s.substring(1), p.substring(1));
            }
            return false;
        }
    }
}

/**
dp solution
dp[i][j] s[0-i]与p[0-j]是否匹配
dp[0][0] = true

1. p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]
2. If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
3. If p.charAt(j) == '*':
    1). if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //a* counts as empty
    2). if p.charAt(j-1) = s.chatAt(i) or p.charAt(j-1) = '.';
        dp[i][j] = dp[i][j-1] //a* counts as once
        dp[i][j] = dp[i-1][j] //a* counts as multiple
        do[i][j] = dp[i][j-2] //a* counts as empty
*/
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean [s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        //"aab" "c*aab" 预处理
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true; //dp[0][2] = true a* empty
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == s.charAt(i))
                    dp[i + 1][j + 1] = dp[i][j];
                if (p.charAt(j) == '.')
                    dp[i + 1][j + 1] = dp[i][j];
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][ j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j+1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
