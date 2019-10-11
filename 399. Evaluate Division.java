/* 399. Evaluate Division
Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].
*/

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String str1 = equations.get(i).get(0);
            String str2 = equations.get(i).get(1);
            map.putIfAbsent(str1, new HashMap<>());
            map.putIfAbsent(str2, new HashMap<>());
            map.get(str1).put(str2, values[i]);
            map.get(str2).put(str1, 1 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String str1 = queries.get(i).get(0);
            String str2 = queries.get(i).get(1);
            //The hashset is to reduce redundant loops
            res[i] = dfs(str1, str2, 1, map, new HashSet<>());
        }
        return res;
        
    }
    
    double dfs(String str1, String str2, double i, Map<String, Map<String, Double>> map, HashSet<String> seen) {
        if (!map.containsKey(str1) ||!seen.add(str1)) return -1;
        if (str1.equals(str2)) return i;
        Map<String, Double> neighbor = map.get(str1);
        for (String key : neighbor.keySet()) {
            double res = dfs(key, str2, i * neighbor.get(key), map, seen);
            //skip if hasn't found
            if (res != -1) return res;
        }
        return -1;
    }
}

/*
a / b = 2.0
==> b is the parent of a and map.put(a, 2.0)
==> a = root.get(a) * map.get(a);
"root" restores the parent of the node; "map" restores factor. The formula is "node = parent * factor"
For example, "x / y = 2.0". Here, y is the parent of x; and the factor is 2.0.
If we also have "y / z = 3.0", which means that z is the final parent of x due to path compression; and the factor turns out to be 6.0.
*/

/**
    1. Thoughts
        - check if we have enough info to get the result
        - if yes, calculate; if not, return -1.0
        - Method: union find
            - a/b = 2.0 --> b is the root of a; the distance from a to b is 1/2.0
            - if two nums have the same root, we can get the result; a/b=2.0, b/c=3.0
            index   a   b   c
            root    b   c   c 
            dist    2   3   1
            - if we want to know a/c = ?: a = 2 * b = 2 * 3 * c => a/c = 6.0
    2. Corner case
        - if any input is null, return null
        - no enough info, return -1.0
    3. Steps
        - go through equations to union elements with the same root and update root map and distance map
        - go through each query: check if has the same root; find relative dist
*/

class Solution {
    public double[] calcEquation(String[][] e, double[] values, String[][] q) {
        double[] res = new double[q.length];
        Map<String, String> root = new HashMap<>();
        Map<String, Double> dist = new HashMap<>();
        for (int i = 0; i < e.length; i++) {
            String r1 = find(root, dist, e[i][0]);
            String r2 = find(root, dist, e[i][1]);
            root.put(r1, r2);
            dist.put(r1, dist.get(e[i][1]) * values[i] / dist.get(e[i][0]));
        }
        for (int i = 0; i < q.length; i++) {
            if (!root.containsKey(q[i][0]) || !root.containsKey(q[i][1])) {
                res[i] = -1.0;
                continue;
            }
            String r1 = find(root, dist, q[i][0]);
            String r2 = find(root, dist, q[i][1]);
            if (!r1.equals(r2)) {
                res[i] = -1.0;
                continue;
            }
            res[i] = (double) dist.get(q[i][0]) / dist.get(q[i][1]);
        }
        return res;
    }
    
    private String find(Map<String, String> root, Map<String, Double> dist, String s) {
        if (!root.containsKey(s)) {
            root.put(s, s);
            dist.put(s, 1.0);
            return s;
        }
        if (root.get(s).equals(s)) return s;
        String lastP = root.get(s);
        String p = find(root, dist, lastP);
        root.put(s, p);
        dist.put(s, dist.get(s) * dist.get(lastP));
        return p;
    }
}



When we find the parent of the string, we also accumulately multiply the factors