package LeetCode.LinkedList;

/**
 * 双链表求和(顺序求和)
 */
public class Num_2 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    /**
     * 递归
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int flag = 0;

        ListNode l3 = new ListNode(0);
        ListNode res = l3;
        add(l3, l1, l2, flag);
        return res.next;
    }

    private void add(ListNode l3, ListNode l1, ListNode l2, int flag) {

        if (l1 == null && l2 == null && flag == 0) {
            return;
        }
        if (l1 == null) {
            l1 = new ListNode(0);
        }
        if (l2 == null) {
            l2 = new ListNode(0);
        }
        int sum = l1.val + l2.val + flag;
        flag = sum / 10;
        l3.next = new ListNode(sum % 10);
        l3 = l3.next;
        l1 = l1.next;
        l2 = l2.next;
        add(l3, l1, l2, flag);
    }

    /**
     * 非递归（好理解）
     */
    public ListNode add(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        int flag = 0;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + flag;
            flag = sum / 10;
            res.next = new ListNode(sum % 10);
            res = res.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (flag != 0) {
            res.next = new ListNode(flag);
        }
        return cur.next;
    }
}
