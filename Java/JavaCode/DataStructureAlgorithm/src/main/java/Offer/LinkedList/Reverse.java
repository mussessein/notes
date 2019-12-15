package Offer.LinkedList;

/**
 * ����һ��������ת��������������ı�ͷ��
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
