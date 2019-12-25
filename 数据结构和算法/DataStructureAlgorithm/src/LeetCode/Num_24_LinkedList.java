package LeetCode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 给定 1->2->3->4,
 * 返回 2->1->4->3.
 */
public class Num_24_LinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode swapPairs(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode second = head.next;
        head.next=swapPairs(second.next);
        second.next=head;
        return second;
    }
}