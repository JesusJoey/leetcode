/*
1208. Get Equal Substrings Within Budget
Input: s = "abcd", t = "bcdf", maxCost = 3
Output: 3
Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
*/
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int res = 0;
        int i = 0;
        int sum = 0;
        for (int j = 0; j < s.length(); j++) {
            sum += Math.abs(sc[j] - tc[j]);
            while (sum > maxCost) {
                sum -= Math.abs(sc[i] - tc[i]);
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}