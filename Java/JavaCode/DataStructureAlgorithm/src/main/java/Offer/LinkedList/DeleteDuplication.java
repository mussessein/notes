package Offer.LinkedList;


/**
 * ��ȥ��
 * ɾ���ظ��ڵ�
 * 1->2->3->3->4->4->5 �����Ϊ 1->2->5
 */
public class DeleteDuplication {
    /**
     * ȥ�أ�����һ���ظ��ڵ�
     */
    // 1. �ǵݹ�
    public ListNode delete(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = dummyHead.next;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    // 3. �ǵݹ�ڶ���(�����ظ��ڵ�)
    public static ListNode delete1(ListNode head) {
        ListNode pre = null;
        ListNode node = head;
        while (node != null) {
            if (pre != null && pre.val == node.val)
                pre.next = node.next;//������ȵľ�����������һ��
            else
                pre = node;//����ȵľ�ֱ��ͨ��
            node = node.next;
        }
        return head;
    }

    // 2.�ݹ�
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        else if (head.next != null) {
            ListNode cur = head.next;
            if (cur.val == head.val) {
                head.next = cur.next;
                deleteDuplicates(head);
            }
            deleteDuplicates(head.next);
        }
        return head;
    }
}