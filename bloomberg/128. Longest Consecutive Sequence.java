/*
128. Longest Consecutive Sequence

Given an unsorted array of integers, find the length of the longest consecutive elements 
sequence.

Your algorithm should run in O(n) complexity.

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. 
Therefore its length is 4.
*/

/*
Using a set to collect all elements that hasn't been visited,
search element will be O(1) and eliminates visiting element again.
*/


    public int longestConsecutive(int[] nums) {
        HashSet set = new HashSet<>();
        for(int i : nums){
            set.add(i);
        }
        int max = 0;
        for(int i : nums){
            if(!set.contains(i-1)){
                int count = i;
                while(set.contains(count)){
                    count++;
                }
                max = Math.max(count - i, max);
            }
        }
        return max;
    }

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;
        
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            set.add(num);
        }
        int res = 1;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int cur = num;
                int len = 1;
                
                while (set.contains(++cur)) {
                    len++;
                }
                res = Math.max(res, len);
            }
        }
        return res;
    }
}







UnionFind:
public int longestConsecutive2(int[] nums) {
    int n = nums.length;
    UnionFind uf = new UnionFind(n);

    Map<Integer, Integer> map = new HashMap<>(); 
    for (int i = 0; i < n; i++) map.put(nums[i], i);

    for (int num : nums)
        if (map.containsKey(num+1))
            uf.union(map.get(num), map.get(num+1));

    return uf.getMaxSize();
}

private class UnionFind {
    private int[] id;
    private int[] sz;
    private int maxSize;

    public UnionFind(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
        maxSize = n > 0 ? 1 : 0;
    }

    public int findRoot(int i) {
        if (i == id[i])  return i;
        id[i] = findRoot(id[i]);
        return id[i];
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        if (rootP == rootQ)  return;
        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
            maxSize = Math.max(maxSize, sz[rootQ]);
        } else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
            maxSize = Math.max(maxSize, sz[rootP]);
        }
    }

    public int getMaxSize() {
        return this.maxSize;
    }
}