/*
207. Course schedule
Input: 2, [[1,0]] 
Output: true
*/
//BFS Method
public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<Integer>[] neighbour = new ArrayList[numCourses];
    int[] degree = new int[numCourses];
    Queue<Integer> queue = new LinkedList();
    int count = 0;

    for (int i = 0; i < numCourses; i++)
        neighbour[i] = new ArrayList();
    for (int[] pair : prerequisites) {
        neighbour[pair[1]].add(pair[0]);
        degree[pair[0]]++;
    }
    for (int i = 0; i < degree.length; i++){
        if (degree[i] == 0) queue.offer(i);
    }
    while (!queue.isEmpty()) {
        int course = queue.poll();
        count++;
        for (int adj : neighbour[course])
            if (--degree[adj] == 0)
                queue.offer(adj);
    }
    return count == numCourses;
}

//dfs toplogical sort

public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>(numCourses);
    for (int i = 0; i < numCourses; i++)
        adj.add(i, new ArrayList<> ());
    for (int i = 0; i < prerequisites.length; i++) 
        adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
    boolean[] visited = new boolean[numCourses];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < numCourses; i++) {
        if (!topologicalSort(adj,i,stack,visited,new boolean[numCourses]))
            return false;
    }
    return true;
}

//true means find a circle
private boolean topologicalSort(List<List<Integer>> adj, int v, Stack<Integer> stack,
    boolean[] visited, boolean[] isLoop) {
    if (visited[v]) return true;
    if (isLoop[v]) return false;
    isLoop[v] = true;
    for (Integer u : adj.get(v)) {
        if (!topologicalSort(adj, u, stack, visited, isLoop)) return false;
    }
    visited[v] = true;
    stack.push(v);
    return true;
}

/*
210. Course schedule ||
Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]

This problem is equivalent to finding the topological order in a directed graph.
If a cycle exists, no topological ordering exists and it will be impossible
to take all courses.
*/

public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>(numCourses);
    for (int i = 0; i < numCourses; i++)
        adj.add(i, new ArrayList<> ());
    for (int i = 0; i < prerequisites.length; i++) 
        adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
    boolean[] visited = new boolean[numCourses];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < numCourses; i++) {
        if (!topologicalSort(adj,i,stack,visited,new boolean[numCourses]))
            return new int[0];
    }
    int[] res = new int[numCourses];
    int index = 0;
    while (!stack.isEmpty()) {
        res[index++] = stack.pop();
    }
    return res;
}

private boolean topologicalSort(List<List<Integer>> adj, int v, 
    Stack<Integer> stack, boolean[] visited, boolean[] isLoop) {
    if (visited[v]) return true;
    if (isLoop[v]) return false;
    isLoop[v] = true;
    for (Integer u : adj.get(v)) {
        if (!topologicalSort(adj, u, stack, visited, isLoop)) return false;
    }
    visited[v] = true;
    stack.push(v);
    return true;
}

public int[] findOrder(int numCourses, int[][] prerequisites) { 
    if (numCourses == 0) return null;
    // Convert graph presentation from edges to indegree of adjacent list.
    int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
    for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
        indegree[prerequisites[i][0]]++;    

    Queue<Integer> queue = new LinkedList<Integer>();
    for (int i = 0; i < numCourses; i++) 
        if (indegree[i] == 0) {
            // Add the course to the order because it has no prerequisites.
            order[index++] = i;
            queue.offer(i);
        }

    // How many courses don't need prerequisites. 
    while (!queue.isEmpty()) {
        int prerequisite = queue.poll(); // Already finished this prerequisite course.
        for (int i = 0; i < prerequisites.length; i++)  {
            if (prerequisites[i][1] == prerequisite) {
                indegree[prerequisites[i][0]]--; 
                if (indegree[prerequisites[i][0]] == 0) {
                    // If indegree is zero, then add the course to the order.
                    order[index++] = prerequisites[i][0];
                    queue.offer(prerequisites[i][0]);
                }
            } 
        }
    }

    return (index == numCourses) ? order : new int[0];
}
