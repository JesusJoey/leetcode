/*
797. All Paths From Source to Target
Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
*/
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, res, path, 0);
        return res;
    }
    
    public void dfs(int[][] graph, List<List<Integer>> res, List<Integer> path, int node) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int neighbor : graph[node]) {
            path.add(neighbor);
            dfs(graph, res, path, neighbor);
            path.remove(path.size() - 1);
        }
        return;
    }
}
