package LeetCode.LinkedList;

/*
拼接有序链表
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Num_21 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /*
    非递归：
     */
    public ListNode mergeTwoLists_1(ListNode l1, ListNode l2) {

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

    /*
    递归,无敌
     */
    public ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // l1,l2互换，每一次都是l1后面添加
        if (l1.val > l2.val) {
            ListNode tmp = l1;
            l1 = l2;
            l2 = tmp;
        }
        l1.next = mergeTwoLists_2(l1.next, l2);
        return l1;
    }
}
