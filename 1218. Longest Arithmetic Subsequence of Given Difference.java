/*

1218. Longest Arithmetic Subsequence of Given Difference
Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
Output: 4
Explanation: The longest arithmetic subsequence is [7,5,3,1].
Let dp[i] be the maximum length of a subsequence of the given difference whose last element is i
dp[i] = 1 + dp[i-k]
Here i shoule be the value, so we use a map
*/

class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        for (int num : arr) {
            dp.put(num, dp.getOrDefault((num - difference), 0) + 1);
        }
        int res = 0;
        for (Integer num : dp.keySet()) {
            res = Math.max(dp.get(num), res);
        }
        return res;
    }
}