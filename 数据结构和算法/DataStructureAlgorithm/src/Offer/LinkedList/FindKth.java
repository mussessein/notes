package Offer.LinkedList;


import java.util.Stack;

/**
 * 找到链表倒数第K个节点
 */
public class FindKth {
    int count = 0;
    Stack<ListNode> stack = new Stack<>();

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k == 0)
            return null;
        while (head != null) {
            stack.push(head);
            count++;
            head = head.next;
        }
        if (count < k)
            return null;
        for (int i = 0; i < k - 1; i++) {
            stack.pop();
        }
        return stack.pop();
    }
}
