package LeetCode.LinkedList;

/**
 * ���������������ڵĽڵ�
 */
public class Num_24 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // �ǵݹ飬���Ҫ��ͷ�ڵ�֮ǰ
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null && node.next.next != null) {
            ListNode pre = node.next;
            ListNode cur = node.next.next;
            node.next = cur;
            pre.next = cur.next;
            cur.next = pre;
            node = pre;
        }
        return dummy.next;
    }

    // �ݹ飬��ⲻ��
    public ListNode swap(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode second = head.next;
        head.next = swapPairs(second.next);
        second.next = head;
        return second;
    }
}
