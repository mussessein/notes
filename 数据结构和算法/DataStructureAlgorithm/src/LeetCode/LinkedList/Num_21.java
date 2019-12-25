package LeetCode.LinkedList;

/*
ƴ����������
���룺1->2->4, 1->3->4
�����1->1->2->3->4->4
��������������ϲ�Ϊһ���µ������������ء���������ͨ��ƴ�Ӹ�����������������нڵ���ɵġ�
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
    �ǵݹ飺
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
    �ݹ�,�޵�
     */
    public ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // l1,l2������ÿһ�ζ���l1�������
        if (l1.val > l2.val) {
            ListNode tmp = l1;
            l1 = l2;
            l2 = tmp;
        }
        l1.next = mergeTwoLists_2(l1.next, l2);
        return l1;
    }
}
