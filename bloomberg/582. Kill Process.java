/*
582. Kill Process

Input: 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation: 
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.
*/

public class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        if (kill == 0) return pid;
        
        int n = pid.size();
        Map<Integer, Set<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            tree.put(pid.get(i), new HashSet<Integer>());
        }
        for (int i = 0; i < n; i++) {
            if (tree.containsKey(ppid.get(i))) {
                Set<Integer> children = tree.get(ppid.get(i));
                children.add(pid.get(i));
                tree.put(ppid.get(i), children);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        traverse(tree, result, kill);
        
        return result;
    }
    
    private void traverse(Map<Integer, Set<Integer>> tree, List<Integer> result, int pid) {
        result.add(pid);
        
        Set<Integer> children = tree.get(pid);
        for (Integer child : children) {
            traverse(tree, result, child);
        }
    }
}

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        
        Map<Integer, List<Integer>> children = new HashMap();
        
        for(int i=0; i<pid.size(); i++){
            Integer parent = ppid.get(i);
            children.putIfAbsent(parent, new ArrayList());
            children.get(parent).add(pid.get(i));
        }
        
        List<Integer> ans = new ArrayList();
        Queue<Integer> q = new LinkedList();
        q.add(kill);
        
        while(!q.isEmpty()){
            Integer current = q.poll();
            ans.add(current);
            q.addAll(children.getOrDefault(current,new LinkedList()));
        }
        
        return ans;
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
    
	    // Store process tree as an adjacency list
	    Map<Integer, List<Integer>> adjacencyLists = new HashMap<>();
	    for (int i=0;i<ppid.size();i++) {
	        adjacencyLists.putIfAbsent(ppid.get(i), new LinkedList<>());
	        adjacencyLists.get(ppid.get(i)).add(pid.get(i));
	    }
	    
	    // Kill all processes in the subtree rooted at process "kill"
	    List<Integer> res = new LinkedList<>();
	    Stack<Integer> stack = new Stack<>();
	    stack.add(kill);
	    while (!stack.isEmpty()) {
	        int cur = stack.pop();
	        res.add(cur);
	        List<Integer> adjacencyList = adjacencyLists.get(cur);
	        if (adjacencyList == null) continue;
	        stack.addAll(adjacencyList);
	    }
	    return res;   
	}