/*
105. Construct Binary Tree from Preorder and Inorder Traversal

The basic idea is here:
Say we have 2 arrays, PRE and IN.
Preorder traversing implies that PRE[0] is the root node.
Then we can find this PRE[0] in IN, say it's IN[5].
Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
Recursively doing this on subarrays, we can build a tree out of it :)

Hope this helps.
*/

public TreeNode buildTree(int[] preorder, int[] inorder) {
	return helper(0,0,inorder.length - 1, preorder, inorder);  
}

public TreeNode helper(int preStart, int inStart, int inEnd,
	int[] preorder, int[] inorder) {
	if (preStart > preorder.length - 1 || inStart > inEnd) return null;
	
	TreeNode root = new TreeNode(preorder[preStart]);
	int index = 0;
	for (int i = inStart; i <= inEnd; i++) {
		if (inorder[i] == root.val) {
	  		index = i;
		} 
	}
	root.left = helper(preStart + 1, inStart, index - 1, preorder, inorder);
	root.right = helper(preStart+index-inStart+1, index + 1,inEnd, preorder, inorder);
	
	return root;
}
//add memory
public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
    
    for(int i = 0; i < inorder.length; i++) {
        inMap.put(inorder[i], i);
    }

    TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    return root;
}

public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
    if(preStart > preEnd || inStart > inEnd) return null;
    
    TreeNode root = new TreeNode(preorder[preStart]);
    int inRoot = inMap.get(root.val);
    int numsLeft = inRoot - inStart;
    
    root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
    root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);
    
    return root;
}


/**
 * Idea is to keep tree nodes in a stack from preorder traversal till their counterpart (same element) is not
 * found in inorder traversal. When found, all children in the left sub-tree of the node must have been already
 * visited.
 */
public TreeNode buildTreeIterative(int[] pre, int[] in) {
	if (pre.length == 0)
		return null;
	Stack<TreeNode> stack = new Stack<>();
	TreeNode root = new TreeNode(pre[0]);
	stack.push(root);
	int preOrderIndex = 1;
	int inOrderIndex = 0;

	while (!stack.isEmpty()) {
		TreeNode top = stack.peek();
		TreeNode node;
		if (top.val != in[inOrderIndex]) {
			// Top node in the stack has not yet encountered its counterpart in inOrder,
			// so next element in pre must be left child of this node
			node = new TreeNode(pre[preOrderIndex++]);
			top.left = node;
		} else {
			// if all the elements in inOrder have been visted, we are done
			if (++inOrderIndex == in.length)
				break;
			stack.pop();
			// Check if there are still some unvisited nodes in the left sub-tree of the top node in the stack
			if (!stack.isEmpty() && (stack.peek().val == in[inOrderIndex]))
				continue;
			// As top node in stack still has not encontered its counterpart
			// in inOrder, so next element in pre must be right child of the removed node
			node = new TreeNode(pre[preOrderIndex++]);
			top.right = node;
		}
		stack.push(node);
	}
	return root;
}