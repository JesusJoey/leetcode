/**
124. Binary Tree Maximum Path Sum

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from 
some starting node to any node in the tree along the parent-child 
connections. The path must contain at least one node and does not 
need to go through the root.

*/

class Solution {
	int max = 0;

	public int maxPathSum(TreeNode root) {
		helper(root);
		return max;
	}

	private int helper(TreeNode root) {
		if (root == null) return 0;

		int left = Math.max(helper(root.left), 0);
		int right = Math.max(helper(root.right), 0);

		max = Math.max(max, root.val + left + right);

		return root.val + Math.max(left,right);
	}
}

/**
112. Path Sum

Given a binary tree and a sum, determine if the tree has a root-to-leaf 
path such that adding up all the values along the path equals the given sum.
*/

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null)
        	return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}

/**
113. Path Sum ||

Given a binary tree and a sum, find all root-to-leaf paths where 
each path's sum equals the given sum.
*/
1.recursive
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList();
        if (root == null) return res;
        dfs(root, sum, new ArrayList(), res);
        return res;
    }

    private void dfs(TreeNode root, int target, List<Integer> list, List<List<Integer>> res) {
    	if (root == null) return;
    	list.add(root.val);
    	if (root.left == null && root.right == null && target == root.val)
    		res.add(new ArrayList(list));
    	dfs(root.left, target - root.val, list, res);
    	dfs(root.right, target - root.val, list ,res);
    	list.remove(list.size() - 1); 
    }
}

2.iterative
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> list = new ArrayList<>();
		if (root == null) return list;
		List<Integer> path = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		int pathSum = 0;
		TreeNode prev = null, curr = root;
		while (curr != null || !stack.isEmpty()) {
			// add all left nodes to the stack
			while (curr != null) {
				stack.push(curr);
				path.add(curr.val);
				pathSum += curr.val;
				curr = curr.left;
			}
			//check left leaf node's right subtree
			//why peek: if it has right subtree, don't push it back
			curr = stack.peek();
			if (curr.right != null && curr.right != prev) {
				curr = curr.right;
				continue;
			}
			//check leaf
			if (curr.left == null && curr.right == null && pathSum == sum) {
				list.add(new ArrayList<Integer>(path));
				//why new : path will be cleared after the traversal
			}
			stack.pop();
			prev = curr;
			//remove the node from the current path
			pathSum -= curr.val;
			path.remove(path.size() - 1);
			curr = null;
		}
		return list;
	}

/**
257. Binary Tree Paths
Given a binary tree, return all root-to-leaf paths.
*/

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if (root != null) searchBT(root,"",res);
        return res;
    }

    private void searchBT(TreeNode root, String path, List<String> res) {
    	if (root.left == null && root.right == null) res.add(path+root.val);
    	if (root.left != null) searchBT(root.left, path + root.val + "->", res);
    	if (root.right != null) searchBT(root.right, path + root.val + "->", res);
    }

	//without helper function
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> paths = new LinkedList<>();
		if (root == null) return paths;
		if (root.left == null && root.right == null) {
			paths.add(root.val + "");
			return paths;
		}
		for (String path : binaryTreePaths(root.left)) {
			paths.add(root.val + "->" + path);
		}

		for (String path: binaryTreePaths(root.right)) {
			paths.add(root.val + "->" + path);
		}
		return paths;
	}
}