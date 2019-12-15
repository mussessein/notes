package LeetCode.LinkedList;

/**
 * ɾ������ĵ����� n ���ڵ�
 */
public class Num_19 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    /**
     * ������ȣ����ҵ�n�Ľڵ�
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        int len = 0;
        // �󳤶�
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        len = len - n;
        cur = dummy;
        // ����������n�Ľڵ�
        while (len > 0) {
            len--;
            cur = cur.next;
        }
        // ɾ��
        cur.next = cur.next.next;
        return dummy.next;
    }
}
