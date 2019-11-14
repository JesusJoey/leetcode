/*
109. Convert Sorted List to BST
*/
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }

 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
	class Solution {
	/**
	 * 方法1：快慢指针+递归方法；
	 * 不会破坏原链表结构
	 */
	public TreeNode sortedListToBST(ListNode head) {
	    return helper(head,null);
	}
	/**
	 * 递归方法: dummyTail作为结束标志
	 */
	public TreeNode helper(ListNode head,ListNode dummyTail){
	    if (head == dummyTail) return null;
	    if (head.next == dummyTail) return new TreeNode(head.val);//减少递归次数，删除也可以。
	    //利用快慢指针寻找链表的中间节点
	    ListNode slow = head,fast = head.next;//fast初始化head或head.next都可以
	    while (fast != dummyTail && fast.next != dummyTail) {
	        slow = slow.next;
	        fast = fast.next.next;
	    }
	    //转化为树的root节点
	    TreeNode root = new TreeNode(slow.val);
	    root.left = helper(head,slow);//递归寻找左子树
	    root.right = helper(slow.next,dummyTail);//递归寻找右子树
	    return root;
	}
}

/*
1. Iterate over the linked list to find out it's length.
2. We can find out the middle element by using (start + end) / 2.
3. Recurse on the left half by using start, mid - 1 as the starting and ending points.
4. whenever we are done building the left half of the BST, the head pointer in the linked list 
will point to the root node or the middle node
5. We recurse on the right hand side using mid + 1, end as the starting and ending points.
*/

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int
 * x) { val = x; } }
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode(int x) { val = x; } }
 */
class Solution {

  private ListNode node;

public TreeNode sortedListToBST(ListNode head) {
	if(head == null){
		return null;
	}
	
	int size = 0;
	ListNode runner = head;
	node = head;
	
	while(runner != null){
		runner = runner.next;
		size ++;
	}
	
	return inorderHelper(0, size - 1);
}

	public TreeNode inorderHelper(int start, int end){
		if(start > end){
			return null;
		}
		
		int mid = start + (end - start) / 2;
		// First step of simulated inorder traversal. Recursively form
	    // the left half
		TreeNode left = inorderHelper(start, mid - 1);
		
		// Once left half is traversed, process the current node
		TreeNode treenode = new TreeNode(node.val);
		treenode.left = left;
		// Maintain the invariance mentioned in the algorithm
		node = node.next;
		// Recurse on the right hand side and form BST out of them
		TreeNode right = inorderHelper(mid + 1, end);
		treenode.right = right;
		
		return treenode;
	}
}




