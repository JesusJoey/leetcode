/*
The subproblems are overlapped. So we can use divide and conquer + cache.

Balloons 0, 1, ..., n - 1
What is the max value if we burst all of them [0, n - 1]?
Let's first consider bursting [start, end]
Imagine we burst index i at the end
[start, ... i - 1, (i), i + 1 ... end]
Before the end, we already bursted [start, i - 1] and [i + 1, end]
Before the end, boundaries start - 1, i, end + 1 are always there
This helps us calculate coins without worrying about details inside [start, i - 1] and [i + 1, end]
So the range can be divided into
start - 1, maxCoin(start, i - 1), i, maxCoins(i + 1, end), end + 1
*/


public int maxCoins(int[] nums) {
    int[][] dp = new int[nums.length][nums.length];
    return maxCoins(nums, 0, nums.length - 1, dp);
}

public int maxCoins(int[] nums, int start, int end, int[][] dp) {
    if (start > end) {
        return 0;
    }
    if (dp[start][end] != 0) {
        return dp[start][end];
    }
    int max = nums[start];
    for (int i = start; i <= end; i++) {
        int val = maxCoins(nums, start, i - 1, dp) + 
                  get(nums, i) * get(nums, start - 1) * get(nums, end + 1) + 
                  maxCoins(nums, i + 1, end, dp);
                  
        max = Math.max(max, val);
    }
    dp[start][end] = max;
    return max;
}

public int get(int[] nums, int i) {
    if (i == -1 || i == nums.length) {
        return 1;
    }
    return nums[i];
}