package Offer.LinkedList;

/**
 * 合并排序链表，返回排序链表
 */
public class Merge {

    // 非递归
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 == null && list2 != null) {
            cur.next = list2;
        }
        if (list2 == null && list1 != null) {
            cur.next = list1;
        }
        return dummyHead.next;
    }

    // 递归第一版
    public ListNode Merge1(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

    // 递归第二版
    public ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        // l1,l2互换，每一次都是l1后面添加
        if (list1.val > list2.val) {
            ListNode tmp = list1;
            list1 = list2;
            list2 = tmp;
        }
        list1.next = Merge(list1.next, list2);
        return list1;
    }
}
