/*
2. Add Two Numbers
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode curr = dummyHead;
    int carry = 0;
    while (l1 != null || l2 != null) {
        //get the number of each digit
        int x = (l1 != null)?l1.val:0;
        int y = (l2 != null)?l2.val:0;
        //get the sum and carry
        int sum = carry + x + y;
        carry = sum/10;
        //curr point to sum%10
        curr.next = new ListNode(sum % 10);
        //update the pointer
        curr = curr.next;
        if (l1 != null) l1 = l1.next;
        if (l2 != null) l2 = l2.next;
    }
    //last carry
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
}

/*
19. Remove Nth Node From End of List
*/
public ListNode removeNthFromEnd(ListNode head, int n) {
    
    ListNode start = new ListNode(0);
    ListNode slow = start, fast = start;
    slow.next = head;
    
    //Move fast in front so that the gap between slow and fast becomes n
    for(int i=0; i<=n; i++)   {
        fast = fast.next;
    }
    //Move fast to the end, maintaining the gap
    while(fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }
    //Skip the desired node
    slow.next = slow.next.next;
    return start.next;
}

public ListNode FindKthToTail(ListNode head,int k) {
    if (k < 1 || head == null) {
        return null;
    }
    ListNode node = head;
    //first move k-1 spot
    for (int i = 1; i<k; i++) {
        if (node.next != null) {
            node = node.next;
        }
        //k is too large
        else {
            return null;
        }
    }
    // node goes with head
    // 当node走到最后一个结点即，node.next=null时，head就是倒数第k个结点
    while (node.next != null) {
        head = head.next;
        node = node.next;
    }
    return head;
}

/*
21. Merge two sorted list

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/

public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    
    ListNode curr = dummyHead;
    while (l1!=null && l2!=null) {
        if (l1.val<=l2.val){
            curr.next=l1;
            l1=l1.next;
        } else {
            curr.next=l2;
            l2=l2.next;
        }
        curr=curr.next;
    }
    curr.next = l1 == null? l2:l1;
    return dummyHead.next;
}

public ListNode mergeTwoLists(ListNode l1, ListNode l2){
    if(l1 == null) return l2;
    if(l2 == null) return l1;
    if(l1.val < l2.val){
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else{
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
/*
23. merge k sorted lists
*/
public ListNode mergeKLists(ListNode[] lists) {
    if (lists==null || lists.length==0) return null;
    
    PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>
    (lists.length, (a,b)-> a.val-b.val);
    
    ListNode dummy = new ListNode(0);
    ListNode tail=dummy;
    
    for (ListNode node:lists)
        if (node!=null)
            queue.add(node);
        
    while (!queue.isEmpty()){
        tail.next=queue.poll();
        tail=tail.next;
        
        if (tail.next!=null)
            queue.add(tail.next);
    }
    return dummy.next;
}

/*
138. Copy List with Random Pointer
Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},
"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer
points to itself.
*/

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
      if (head == null) return null;

      Map<Node, Node> map = new HashMap<Node, Node>();

      // loop 1. copy all the nodes
      Node node = head;
      while (node != null) {
        map.put(node, new Node(node.val));
        node = node.next;
      }

      // loop 2. assign next and random pointers
      node = head;
      while (node != null) {
        map.get(node).next = map.get(node.next);
        map.get(node).random = map.get(node.random);
        node = node.next;
      }
      return map.get(head);
    }
}

public Node copyRandomList(Node head) {
    if(head == null) return null;
    Node dummy = new Node(0);
    dummy.next = head;
    Node curr = head;
    while(curr != null){                                    
        Node copy = new Node(curr.label);
        copy.next = curr.next;
        curr.next = copy;
        curr = curr.next.next;
    }
    curr = head;
    while(curr != null){
        if(curr.random != null)curr.next.random = curr.random.next;
        curr = curr.next.next;
    }
    dummy.next = head.next;
    curr = head;
    while(curr != null){
        Node next = curr.next;
        curr.next = next.next;
        curr = curr.next;
        if(curr != null) next.next = curr.next;
    }
    return dummy.next;
}

/*
141. Linked List Cycle
*/
public boolean hasCycle(ListNode head) {
    if (head==null || head.next == null) {
        return false;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (slow != fast){
        if (fast == null || fast.next == null){
            return false;
        }
        slow = slow.next;
        fast = fast.next.next;
    }
    return true;    
}
//142. Linked List Cycle II reutrn the index
public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while(fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        if (slow == fast) {
            while (head != slow) {
                head = head.next;
                slow = slow.next;
            }
            return slow;                
        }
    }           
    return null;
}
/*
206.Reverse Linked List
*/
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr!=null) {
        ListNode next = curr.next;
        curr.next = prev; //point to null
        prev = curr;   //move curr and next pointer to the right
        curr = next;
    }
    return prev;
}