package LeetCode.LinkedList;

import java.util.Stack;

public class Num_61 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    int count = 0;
    Stack<ListNode> stack = new Stack<>();

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int len = 1;
        ListNode node = head;
        ListNode tail = new ListNode(0);
        while (head.next != null) {
            stack.push(head);
            len++;
            head = head.next;
        }
        head.next = node;
        stack.push(head);
        while (!stack.isEmpty()) {
            count++;
            if (count == (k % len) + 1) {
                tail = stack.pop();
                break;
            }
            stack.pop();
        }
        head = tail.next;
        tail.next = null;
        return head;
    }
}
