/**
110. Balanced Binary Tree

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
*/

class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = height(root.left);
        int right = height(root.right);
        return Math.abs(left-right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

	public boolean isBalanced(TreeNode root) {
	    if(root == null){
	        return true;
	    }
	    return helper(root) != -1;
	}
	private int helper(TreeNode root){
	    if(root == null){
	        return 0;
	    }
	    int left = helper(root.left);
	    int right = helper(root.right);
	    if(left == -1 || right == -1 || Math.abs(left - right) > 1){
	        return -1;
	    }
	    return Math.max(left, right) + 1;
	}
}