package LeetCode.LinkedList;

/**
 * 删除重复节点,不保留重复节点
 */
public class Num_82 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val)
                    cur = cur.next;
                res.next = cur.next;
                cur = cur.next;
            } else {
                res.next = cur;
                res = res.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
