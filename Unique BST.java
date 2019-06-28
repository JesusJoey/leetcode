/**
95. Unique BST ||
Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
96. Unique Binary Search Trees
Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

I start by noting that 1..n is the in-order traversal for any BST 
with nodes 1 to n. So if I pick i-th node as my root, the left subtree 
will contain elements 1 to (i-1), and the right subtree will contain 
elements (i+1) to n. 
*/

class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0)
            return new ArrayList<>();
        return generateTrees(1,n);
    }
    //recursive
    public List<TreeNode> generateTrees(int start, int end) {
    	List<TreeNode> list = new ArrayList<TreeNode>();
    	if (start > end) {
    		list.add(null);
    		return list;
    	}
    	if (start == end) {
    		list.add(new TreeNode(start));
    		return list;
    	}
    	List<TreeNode> left,right;
    	for (int i = start; i <= end; i++) {
    		left = generateTrees(start, i-1);
    		right = generateTrees(i+1, end);
    		for (TreeNode lnode : left) {
    			for (TreeNode rnode : right) {
    				TreeNode root = new TreeNode(i);
    				root.left = lnode;
    				root.right = rnode;
    				list.add(root);
    			}
    		}
    	}
    	return list;    	
    }

 	/**
	G(n): the number of unique BST for a sequence of length n.
	F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root, 
	and the sequence ranges from 1 to n.
	G(n) = F(1, n) + F(2, n) + ... + F(n, n). 
	G(0)=1, G(1)=1. 
	F(i, n) = G(i-1) * G(n-i)	1 <= i <= n 
	F(3,7) = G(2) * G(4).
 	*/

    public int numTrees(int n) {
    	int[] dp = new int[n + 1];
    	dp[0] = 1;
    	dp[1] = 1;
    	for (int i = 2; i <= n; i++) {
    		for (int j = 1; j <= i; j++) {
    			dp[i] += dp[j-1] * dp[i-j];
    		}
    	}
    	return dp[n];
    }
}
