package Offer.LinkedList;

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class Reverse {

    public ListNode ReverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        while (head != null) {
            ListNode cur = head.next;
            head.next = pre;
            pre = head;
            head = cur;
        }
        return pre;
    }
}
