//104. Maximum Depth of Binary Tree

public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    int leftMax = root.left == null ? 0 : maxDepth(root.left);
    int rightMax = root.right == null ? 0 : maxDepth(root.right);
    return 1 + Math.max(leftMax, rightMax);
}

public int maxDepth(TreeNode root) {
    if(root == null) {
        return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int count = 0;
    while(!queue.isEmpty()) {
        int size = queue.size();
        while(size-- > 0) {
            TreeNode node = queue.poll();
            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        count++;
    }
    return count;
}

//111. Minimum Depth of Binary Tree
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (root.left == null || root.right == null) ?
            left + right + 1 : Math.min(right, left) + 1; 
    }

    public static int minDepth(TreeNode root) {
        if (root == null)   return 0;
        if (root.left == null)  return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left),minDepth(root.right)) + 1;
    }
}