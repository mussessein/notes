package tags.LinkedList;

/**
 * @author whr
 * @date 2019/12/23 15:21
 * 链表排序
 */
public class No3_Sort {
    /**
     * 利用归并排序思想：
     * 1. 分段拆分（快慢指针）——快指针到终点时慢指针指向中点
     * 2. 归并：归并逻辑相当于——排序合并两个链表
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next, slow = head;
        // 找到中间节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 切割为两个链表,第二节连接以fast为头
        ListNode tmp = slow.next;
        slow.next = null;
        // 分段：left和right是归并后的返回值
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        // 归并：
        ListNode h = new ListNode(0);
        ListNode res = h;
        // 归并逻辑相当于——排序合并两个链表
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
    }

}
