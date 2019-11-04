/*
526. Beautiful Arrangement
Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by
 these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.

Now given N, how many beautiful arrangements can you construct?
*/

class Solution {
    public int countArrangement(int N) {
        char[] currState = new char[N + 1];
        Arrays.fill(currState, 'f');  // f means not selected, t means selected
        return helper(new HashMap<String, Integer>(), currState, 1);
    }
    
    public int helper(Map<String, Integer> map, char[] currState, int index) {
        if(index == currState.length) return 1;
        String key = String.valueOf(currState);
        if(map.containsKey(key)) return map.get(key);
        int count = 0;
        for(int i = 1; i < currState.length; i++) {
            if(currState[i] == 'f' && (i % index == 0 || index % i == 0)) {
                currState[i] = 't';
                count += helper(map, currState, index + 1);
                currState[i] = 'f';
            }
        }
        map.put(key, count);
        return count;
    }
}

class Solution {
    public int countArrangement(int N) {
        dfs(N, N, new boolean[N + 1]);
        return count;
    }
    
    int count = 0;
    
    void dfs(int N, int k, boolean[] visited) {
        if (k < 2) {
            count++;
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (visited[i] || k % i != 0 && i % k != 0) {
                continue;
            }
            visited[i] = true;
            dfs(N, k - 1, visited);
            visited[i] = false;
        }
    }
}