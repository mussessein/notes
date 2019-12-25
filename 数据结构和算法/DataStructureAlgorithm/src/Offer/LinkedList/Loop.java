package Offer.LinkedList;

import java.util.LinkedHashMap;

/**
 * 给一个链表，若其中包含环，
 * 请找出该链表的环的入口结点，否则，输出null。
 */
public class Loop {
    // 1. 使用API，LinkedHashMap，
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        LinkedHashMap<ListNode, Integer> lmp = new LinkedHashMap<>();
        while (pHead != null) {
            if (lmp.containsKey(pHead)) {
                return pHead;
            } else {
                lmp.put(pHead, null);
                pHead = pHead.next;
            }
        }
        return null;
    }

    /**
     * 2. 不使用API
     * 解释看图片loop
     * 首节点是A，快慢指针相遇点B，起点到A == AB
     */
    public ListNode loop(ListNode pHead) {
        ListNode p1 = pHead;
        ListNode p2 = pHead;
        boolean flag = false;
        while (p1.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                flag = true;
                break;
            }
        }
        if (flag) {
            ListNode p = pHead;
            while (p != p1) {
                p = p.next;
                p1 = p1.next;
            }
            return p;
        } else
            return null;
    }
}
