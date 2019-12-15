package LeetCode.LinkedList;

/**
 * 删除链表的倒数第 n 个节点
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
     * 求出长度，再找到n的节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        int len = 0;
        // 求长度
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        len = len - n;
        cur = dummy;
        // 锁定倒数第n的节点
        while (len > 0) {
            len--;
            cur = cur.next;
        }
        // 删除
        cur.next = cur.next.next;
        return dummy.next;
    }
}
