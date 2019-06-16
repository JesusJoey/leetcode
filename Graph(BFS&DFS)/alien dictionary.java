/**
269. Alien Dictionary

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
*/

private final int N = 26;
public String alienOrder(String[] words) {
    boolean[][] adj = new boolean[N][N];
    int[] visited = new int[N];
    buildGraph(words, adj, visited);

    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < N; i++) {
        if(visited[i] == 0) {                 // unvisited
            if(!dfs(adj, visited, sb, i)) return "";
        }
    }
    return sb.reverse().toString();
}

public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
    visited[i] = 1;                            // 1 = visiting
    for(int j = 0; j < N; j++) {
        if(adj[i][j]) {                        // connected
            if(visited[j] == 1) return false;  // 1 => 1, cycle   
            if(visited[j] == 0) {              // 0 = unvisited
                if(!dfs(adj, visited, sb, j)) return false;
            }
        }
    }
    visited[i] = 2;                           // 2 = visited
    sb.append((char) (i + 'a'));
    return true;
}

public void buildGraph(String[] words, boolean[][] adj, int[] visited) {
    Arrays.fill(visited, -1);                 // -1 = not even existed
    for(int i = 0; i < words.length; i++) {
        for(char c : words[i].toCharArray()) visited[c - 'a'] = 0;
        if(i > 0) {
            String w1 = words[i - 1], w2 = words[i];
            int len = Math.min(w1.length(), w2.length());
            for(int j = 0; j < len; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if(c1 != c2) {
                    adj[c1 - 'a'][c2 - 'a'] = true;
                    break;
                }
            }
        }
    }
}

/** Pretty similar to Course Schedule problem (LC 210), the only difference is that
 * we need to build graph by comparing every consecutive pair of strings firstly,
 * and then doing topological sort for the graph to get the result string*/
/*.*/
public String alienOrder(String[] words) {
    if (words == null || words.length == 0) {
        return "";
    }

    Map<Character, Set<Character>> graph = new HashMap<>();
    Map<Character, Integer> inDegreeMap = new HashMap<>();
    /* MUST initialize the map, to avoid null exception for those character that will have zero inDegrees (i.e. starting characters) */
    for (String word : words) {
        for (char c : word.toCharArray()) {
            inDegreeMap.put(c, 0);
        }
    }

    /* build graph, as well as fill out inDegree map for every character */
    for (int i = 0; i < words.length - 1; i++) {
        String curWord = words[i];
        String nextWord = words[i + 1];
        int minLength = Math.min(curWord.length(), nextWord.length());

        /* according to given dictionary with specified order, traverse every pair of words,
         * then put each pair into graph map to build the graph, and then update inDegree map
         * for every "nextChar" (increase their inDegree by 1 every time) */
        for (int j = 0; j < minLength; j++) {
            char curChar = curWord.charAt(j);
            char nextChar = nextWord.charAt(j);
            if (curChar != nextChar) {
                
                /* update graph map */
                graph.putIfAbsent(curChar, new HashSet<>());
                Set<Character> set = graph.get(curChar);

                /** WARNING: we must check if we already build curChar -> nextChar relationship in graph
                 * if it contains, we cannot update inDegree map again. Otherwise, this nextChar
                 * will never be put in the queue when we do BFS traversal
                 * eg: for the input: {"za", "zb", "ca", "cb"}, we have two pairs of a -> b relationship
                 * if we increase inDegree value of 'b' again, the final result will not have 'b', since 
                 * inDegree of b will stay on 1 when queue is empty 
                 * correct graph: a -> b, z -> c
                 * incorrect graph: a -> b, a -> b, z -> c
                 * */
                if (!set.contains(nextChar)) {
                    set.add(nextChar);
                    graph.put(curChar, set);

                    /* update inDegree map */
                    inDegreeMap.put(nextChar, inDegreeMap.getOrDefault(nextChar, 0) + 1);
                }

                /* we can determine the order of characters ony by first different pair of characters so we cannot add relationship by the rest of characters */
                break;
            }
        }
    }

    /* after building graph, we will have an input that has exact same format as Course Schedule, then we can use BFS to do topological sort */
    StringBuilder sb = new StringBuilder();
    Queue<Character> queue = new LinkedList<>();

    /* put all starting node into queue, which means put all nodes that have inDegree = 0 */
    for (char key : inDegreeMap.keySet()) {
        if (inDegreeMap.get(key) == 0) {
            queue.offer(key);
        }
    }

    /* BFS traversal to build result string */
    while (!queue.isEmpty()) {
        char curChar = queue.poll();
        sb.append(curChar);

        /* traverse all next node of current node in graph, update inDegree value then put all nodes with zero inDegree into queue */
        if (graph.containsKey(curChar)) {
            for (char nextChar : graph.get(curChar)) {
                inDegreeMap.put(nextChar, inDegreeMap.get(nextChar) - 1);
                if (inDegreeMap.get(nextChar) == 0) {
                    queue.offer(nextChar);
                }
            }
        }
    }

    /* check if input order is valid */
    if (sb.length() != inDegreeMap.size()) {
        return "";
    }
    return sb.toString();
} 
