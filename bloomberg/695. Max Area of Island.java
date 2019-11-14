// /695. Max Area of Island

class Solution {
    int[][] dirs = {{0, 1},{1, 0},{0, -1},{-1, 0}};
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    area = 0;
                    dfs(grid, i, j);
                    max = Math.max(area, max);
                }
            }
        }
        return max;
    }
    int area;
    private void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        area++;
        /*for (int[] dir : dirs) {
            dfs(grid, i + dir[0], j + dir[1]);
        }*/
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}