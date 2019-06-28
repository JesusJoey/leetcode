/**
86. Partition List

Given a linked list and a value x, partition it such that 
all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes
in each of the two partitions.

Explanation: 
the basic idea is to maintain two queues, the first one stores 
all nodes with val less than x , and the second queue stores 
all the rest nodes. Then concat these two queues. Remember to 
set the tail of second queue a null next, or u will get TLE.

*/

class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(0), largeHead = new ListNode(0);
        ListNode smallNode = smallHead, largeNode = largeHead;
        while (head != null) {
        	if (head.val < x) {
        		smallNode.next = head;
        		smallNode = head;
        	} else {
        		largeNode.next = head;
        		largeNode = head;
        	}
        	head = head.next;
        }
        largeNode.next = null; //avoid cycle
        smallNode.next = largeHead.next; //connect two linkedlist
        return smallHead.next;
    }
}
