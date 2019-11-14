/*
662. Maximum Width of Binary Tree
*/

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> p =new LinkedList<>();
        q.offer(root);
        p.offer(1);
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            int start = 0, end = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                int index = p.poll();
                if (i == 0) start = index;
                if (i == size - 1) end = index;
                if (node.left != null) {
                    q.offer(node.left);
                    p.offer(index * 2);
                }
                if (node.right != null) {
                    q.offer(node.right);
                    p.offer(index * 2 + 1);
                }
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}

    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
    }
    
    public int dfs(TreeNode root, int level, int order, List<Integer> start, List<Integer> end){
        if(root == null)return 0;
        if(start.size() == level){
            start.add(order); end.add(order);
        } else {
        	end.set(level, order);
        }
        int cur = end.get(level) - start.get(level) + 1;
        int left = dfs(root.left, level + 1, 2*order, start, end);
        int right = dfs(root.right, level + 1, 2*order + 1, start, end);
        return Math.max(cur, Math.max(left, right));
    }

    private int max = 1;
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        List<Integer> startOfLevel = new LinkedList<>();
        helper(root, 0, 1, startOfLevel);
        return max;
    }
    public void helper(TreeNode root, int level, int index, List<Integer> list) {
        if (root == null) return;
        if (level == list.size()) list.add(index);
        max = Math.max(max, index + 1 - list.get(level));
        helper(root.left, level + 1, index * 2, list);
        helper(root.right, level + 1, index * 2 + 1, list);
    }