
/**
炒股系列
123. Best Time to Buy and Sell Stock III
最多买两次
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int[][] dp = new int[3][prices.length];
        int[] min = new int[3];
        Arrays.fill(min,prices[0]);
        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= 2; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1][i - 1]);
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min[k]);
            }
        }
        return dp[2][prices.length - 1];
    }

    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;
        int[] dp = new int[3];
        int[] min = new int[3];
        Arrays.fill(min, prices[0]);
        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= 2; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }
        return dp[2];
    }
}

    public int MaxProfit(int[] prices) {
        if (prices.Length < 2) return 0;
        int prev = 0, res = 0;
        int min = prices[0];
        for (int i = 1; i < prices.Length; i++) {
            min = Math.Min(min, prices[i] - prev);
            prev = res;
            res = Math.Max(res, prices[i] - min);
        }
        return res;
    }