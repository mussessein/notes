package Offer.LinkedList;

import java.util.ArrayList;

/**
 * 反转链表打印
 */
public class PrintTailToHead {
    ArrayList<Integer> res = new ArrayList<Integer>();

    // 非递归
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        while (listNode != null) {
            res.add(0, listNode.val);
            listNode = listNode.next;
        }
        return res;
    }

    // 递归
    public ArrayList<Integer> printListNode(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            res.add(listNode.val);
        }
        return res;
    }
}
