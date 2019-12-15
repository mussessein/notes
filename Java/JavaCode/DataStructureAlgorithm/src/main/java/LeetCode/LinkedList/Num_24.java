package LeetCode.LinkedList;

/**
 * 两两交换其中相邻的节点
 */
public class Num_24 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 非递归，起点要在头节点之前
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

    // 递归，理解不了
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
