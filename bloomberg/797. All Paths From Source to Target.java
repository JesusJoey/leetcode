/*

One dfs solution is to traverse the graph from start node to the end, 
and keep track of each node along the path. Each node can be visited 
many times when it has multiple indegree.
*/
/*
To generate all of these paths you need to use Backtracking:
1. Go through every vertex's childern (This is essentially picking a path)
2. Then dfs() on that path to find if any of those childern lead to your target vertex
3. If they, do then add them to your answer list

Time Complexity: O(2^n), because there will be 2^n number of paths
Space Complexity: O(2^n), because you will need to return 2^n number of paths
*/

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, res, path, 0);
        return res;
    }
    
    public void dfs(int[][] graph, List<List<Integer>> res, List<Integer> path, int source) {
        if (source == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int neighbor : graph[source]) {
            path.add(neighbor);
            dfs(graph, res, path, neighbor);
            path.remove(path.size() - 1);
        }
        return;
    }
}