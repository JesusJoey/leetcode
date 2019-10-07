/*
1219. Path with Maximum Gold
Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.

Complexity

Time: (m*n)^2,
Space: m*n -> DFS stack depth
m is the number of rows, n is the number of columns in the matrix
1 <= m, n <= 15
*/

class Solution {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxGold = 0;
        for (int r = 0; r < m; r++) // O((m*n)^2) -> This is complexity of this solution
            for (int c = 0; c < n; c++)
                maxGold = Math.max(maxGold, findMaxGold(grid, r, c));

        return maxGold;
    }

    private static final int[] directions = new int[]{0, 1, 0, -1, 0};
	// O(m*n), because from starting (r,c) they can go up to m*n unique paths
    private int findMaxGold(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) return 0;

        int origin = grid[r][c];
        grid[r][c] = 0; // mark as visited

        int maxGold = 0;
        for (int i = 0; i < 4; i++)
            maxGold = Math.max(maxGold, findMaxGold(grid, directions[i] + r, directions[i+1] + c));

        grid[r][c] = origin; // backtrack
        return maxGold + origin;
    }
}