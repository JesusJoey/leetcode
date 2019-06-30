/**
101. Symmetric Tree

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3
*/

class TreeNode {
	int left;
	int right;
	int val;
	TreeNode(x) {int this.val = x;}
}

class Solution {
	public boolean isSymmetric(TreeNode root) {
		return root == null || isSymmetric(root.left, root.right);
	}

	private boolean isSymmetric(TreeNode left, TreeNode right) {
		if (left == null || right == null)
			return left == right;
		if (left.val != right.val) 
			return false;
		return isSymmetric(left.left, right.right) && isSymmetric(left.right, righrt.left);
	}

	//iterative
	public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root.left);
		stack.push(root.right);
		while (!stack.empty()) {
			TreeNode node1 =stack.pop(), node2 = stack.pop();
			if (node1 == null && node2 == null) continue;
			if (node1 == null || node2 == null || node1.val != node2.val)
				return false;
			stack.push(node1.left);
			stack.push(node2.right);
			stack.push(node1.right);
			stack.push(node2.left);
		}
		return true;
	}
}