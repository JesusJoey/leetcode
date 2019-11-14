/*
1223. Dice Roll Simulation

A die simulator generates a random number from 1 to 6 for each roll. 
You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times. 

Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.

Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, 
return it modulo 10^9 + 7.

Input: n = 2, rollMax = [1,1,2,2,2,3]
Output: 34
Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. 
In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, 
so the final answer is 36-2 = 34.
*/

class Solution {
    int[][][] dp = new int[5000][6][16];
    final int M = 1000000007;
    
    public int dieSimulator(int n, int[] rollMax) {
        return dfs(n, rollMax, -1, 0);
    }
    
    private int dfs(int dieLeft, int[] rollMax, int last, int curlen){
        if(dieLeft == 0) return 1;
        if(last >= 0 && dp[dieLeft][last][curlen] > 0) return dp[dieLeft][last][curlen];
        int ans = 0;
        for(int i=0; i<6; i++){
            if(i == last && curlen == rollMax[i]) continue;
            ans = (ans + dfs(dieLeft - 1, rollMax, i, i == last ? curlen + 1 : 1)) % M;
        }
        if(last >= 0) dp[dieLeft][last][curlen] = ans;
        return ans;
    }
}

/*
It is a 2D-DP problem.
DP[i][j] means how many choic for total i dices and the last number is j.
For each DP[i][j] we need to remove some invalid choice since there is a restriction for consecutive times.

Let's take an example:
Assuming restriction for 1 is 2 times and we meet the case axxb (a, b are indexes of uncertain values). We all know we can not take 1 at index-b when xx = '11', these are all invaild so we need to remove them.

First of all, the total possible cases of dp[b][1] are sum of dp[b-1][1~6]
for index-b if we want to choose 1 so we need to consider the case that the two consecutive number before b is '11'. Also we need to be careful about value at the index-a. It shouldn't be '1'. In short we need to remove all possible cases of a11 and a is not 1.
The transition equation = dp[b][1] = sum(dp[b-1][1~6]) - sum(dp[a][2~6(except 1)])

Following is my code:
I save the sum of dp[i][1~6] at dp[i][7] for easier calculation.
*/

class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        int mod = (int)1e9 + 7;
        //dp[i][j] represents the number of distinct sequences that can be obtained when rolling i times and ending with j
        //The one more row represents the total sequences when rolling i times
        int[][] dp = new int[n + 1][7];
        //init for the first roll
        for (int i = 0; i < 6; i++) {
            dp[1][i] = 1;
        }
        dp[1][6] = 6;
        for (int i = 2; i <= n; i++) {
            int total = 0;
            for (int j = 0; j < 6; j++) {
                //If there are no constraints, the total sequences ending with j should be the total sequences from preious rolling
                dp[i][j] = dp[i - 1][6];
                //For xx1, only 111 is not allowed, so we only need to remove 1 sequence from previous sum
                if (i - rollMax[j] == 1) {
                    dp[i][j]--;
                }
                //For axx1, we need to remove the number of a11 (211 + 311 + 411 + 511 + 611) => (..2 + ..3 + ..4 + ..5 + ..6) => (sum - ..1)
                if (i - rollMax[j] >= 2) {
                    int reduciton = dp[i - rollMax[j] - 1][6] - dp[i - rollMax[j] - 1][j];
                    //must add one more mod because subtraction may introduce negative numbers
                    dp[i][j] = ((dp[i][j] - reduciton) % mod + mod) % mod;
                }
                total = (total + dp[i][j]) % mod;
            }
            dp[i][6] = total;
        }
        return dp[n][6];
    }
}






