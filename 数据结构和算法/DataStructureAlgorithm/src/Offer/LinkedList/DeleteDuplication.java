package Offer.LinkedList;


/**
 * 非去重
 * 删除重复节点
 * 1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplication {
    /**
     * 去重，保留一个重复节点
     */
    // 1. 非递归
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

    // 3. 非递归第二版(保留重复节点)
    public static ListNode delete1(ListNode head) {
        ListNode pre = null;
        ListNode node = head;
        while (node != null) {
            if (pre != null && pre.val == node.val)
                pre.next = node.next;//遇到相等的就跳过连到下一个
            else
                pre = node;//不相等的就直接通过
            node = node.next;
        }
        return head;
    }

    // 2.递归
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