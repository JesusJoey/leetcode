/*
138. Copy List with Random Pointer


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
}

class Solution {
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        HashMap<Integer,Node> hm = new HashMap<Integer,Node>();

        //loop 1: copy all the nodes
        Node cur = head;
        while(cur!=null){
            hm.put(cur.val, new Node(cur.val));
            cur=cur.next;
        }

        //loop 2: assign next and random pointers
        cur=head;
        while(cur!=null){
            if(cur.next!=null) hm.get(cur.val).next=hm.get(cur.next.val);
            if(cur.random!=null) hm.get(cur.val).random=hm.get(cur.random.val);
            cur=cur.next;
        }
        return hm.get(head.val);
    }
}