//剑指offer:链表的倒数第k个结点

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        if (k < 1 || head == null) {
            return null;
        }
        ListNode node = head;
        //node先移动k-1个位置
        for (int i = 1; i<k; i++) {
            if (node.next != null) {
                node = node.next;
            }
            //i没有达到k-1说明k太大，链表中没有那么多元素
            else {
                return null;
            }
        }
        // node还没有走到链表的末尾，那么node和head一起走，
        // 当node走到最后一个结点即，node.next=null时，head就是倒数第k个结点
        while (node.next != null) {
            head = head.next;
            node = node.next;
        }
        return head;
    }
}