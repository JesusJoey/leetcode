/*
117. Populating next right pointers
*/

O(N) O(N)
class Solution {
    public Node connect(Node root) {
        if (root == null)
            return root;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        queue.add(null); //indicate a level is complete;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                if (!queue.isEmpty())
                    queue.add(null);
                continue;
            }
            node.next = queue.peek();
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
            
        }
        return root;
    }
}

public void connect(Node root) {
    Node dummyHead = new Node(0);
    Node pre = dummyHead;
    while (root != null) {
	    if (root.left != null) {
		    pre.next = root.left;
		    pre = pre.next;
	    }
	    if (root.right != null) {
		    pre.next = root.right;
		    pre = pre.next;
	    }
	    root = root.next;
	    if (root == null) {
		    pre = dummyHead;
		    root = dummyHead.next;
		    dummyHead.next = null;
	    }
    }
}

public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode level_start=root;
        while(level_start!=null){
            TreeLinkNode cur=level_start;
            while(cur!=null){
                if(cur.left!=null) cur.left.next=cur.right;
                if(cur.right!=null && cur.next!=null) cur.right.next=cur.next.left;
                
                cur=cur.next;
            }
            level_start=level_start.left;
        }
    }
}