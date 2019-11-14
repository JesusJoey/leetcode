/*
We just need to form a linked list of all 
odd nodes(X) and another linked list of all even nodes(Y). 
Afterwards, we link Y to the end of X, and return the head of X.
*/

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while(odd.next != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }