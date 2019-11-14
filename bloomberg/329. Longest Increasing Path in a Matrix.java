/*
329. Longest Increasing Path in a Matrix

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
*/


class Solution {
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max,dfs(matrix, i, j, cache));
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = dfs(matrix, x, y, cache) + 1;
            max = Math.max(len, max);
        }
        cache[i][j] = max;
        return max;
    }
}