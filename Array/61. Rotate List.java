/**
61. Rotate List

Given a linked list, rotate the list to the right by k places.

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
*/

@method
/**
1. Get the length
2. Move node after the (l-k % l)th node to the front
3. rotate
*/

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 1;
        while (head.next != null) {
            head = head.next;
            count++;
        }
        k %= count;
        ListNode cur = dummy.next;
        for (int i = 0; i < count - k - 1; i++) {
            cur = cur.next;
        }
        head.next = dummy.next;
        dummy.next = cur.next;
        cur.next = null;
        return dummy.next;
    }
}