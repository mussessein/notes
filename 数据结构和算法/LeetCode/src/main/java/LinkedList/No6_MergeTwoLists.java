package LinkedList;

/**
 * @author whr
 * @date 2019/12/23 16:43
 * 合并链表
 */
public class No6_MergeTwoLists {
    /**
     * 只能合并有序的两个链表
     */
    public ListNode mergeSortedLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 == null && l2 != null) {
            cur.next = l2;
        }
        if (l2 == null && l1 != null) {
            cur.next = l1;
        }
        return dummyHead.next;
    }
}
