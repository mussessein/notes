package LinkedList;

/**
 * @author whr
 * @date 2019/12/23 13:26
 * 链表去重
 */
public class No2_DeleteDuplication {
    /**
     * 去重保留一个重复节点
     * 1->2->3->3->4->4->5 处理后为 1->2->3->4->5
     * 一前一后双指针
     */
    public ListNode deleteDuplication(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            if (pre != null && pre.val == cur.val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 不保留重复节点
     * 1->2->3->3->4->4->5 处理后为 1->2->5
     * (1)一前一后双指针
     * (2)虚拟头节点(可能从第一个就是重复节点，需要虚拟头节点)
     */
    public ListNode deleteAllDuplication(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                // 这里pre是不动的
                pre.next = cur;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

}
