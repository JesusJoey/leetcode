/*
426. Convert Binary Search Tree to Sorted Doubly Linked List
*/
//version without changing origin node
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node dummy = new Node(0, null, null);
        Node prev = dummy;
        prev = inorder(root,prev);
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }
    
    private Node inorder(Node cur, Node prev) {
        if (cur == null) return prev;
        prev = inorder(cur.left, prev);
        prev.right = cur;
        cur.left = prev;
        prev = inorder(cur.right, cur);
        return prev;
    }
}

//version using global variable
class Solution {
	Node prev = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node dummy = new Node(0, null, null);
        prev = dummy;
        helper(root);
        //connect head and tail
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }

    private void helper (Node cur) {
        if (cur == null) return;
        helper(cur.left);
        prev.right = cur;
        cur.left = prev;
        prev = cur;
        helper(cur.right);
    }
}

//iterative solution using stack
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        Node start = root;
        while (start.left != null) {
            start = start.left;
        }
        Node prev = null;
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (prev != null) {
                prev.right = cur;
                cur.left = prev;
            }
            prev = cur;
            cur = cur.right;
        }
        start.left = prev;
        prev.right = start;
        return start;
    }
}