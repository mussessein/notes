package LeetCode.LinkedList;

/**
 * ɾ���ظ��ڵ�,����һ���ظ��ڵ�
 */
public class Num_83 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

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
}
