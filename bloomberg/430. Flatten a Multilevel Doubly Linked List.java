/*
430. Flatten a Multilevel Doubly Linked List

Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
*/

class Solution {
    public Node flatten(Node head) {
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        
        while (cur != null) {
        	//if cur node has a child,push cur.next, point next to the child, push cur.next to stack to get them later.
            if (cur.child != null) {
                stack.push(cur.next);
                cur.next = cur.child;
                if (cur.next != null) {
                    cur.next.prev = cur;
                }
                cur.child = null;
            } else if (cur.next == null && !stack.isEmpty()) {
                cur.next = stack.pop();
                if (cur.next != null) cur.next.prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}

/*
The idea is simple. We always keep a pre global variable to keep track of the last node we visited and 
connect current node head with this pre node. So for each recursive call, we do Connect 
last visited node with current node by letting pre.next point to current node head and 
current node's prev point to pre
Mark current node as pre. pre = head If there is head.child, we recursively visit and flatten its child node
Recursively visit and flatten its next node
*/

class Solution {
	/*Global variable pre to track the last node we visited */
    Node pre = null;
    public Node flatten(Node head) {
        if (head == null) {
            return null; 
        }
		/*Connect last visted node with current node */
        if (pre != null) {
            pre.next = head; 
            head.prev = pre;
        }

        pre = head; 
		/*Store head.next in a next pointer in case recursive call to flatten head.child overrides head.next*/
        Node next = head.next; 

        flatten(head.child);
        head.child = null;

        flatten(next);        
        return head; 
    }
}