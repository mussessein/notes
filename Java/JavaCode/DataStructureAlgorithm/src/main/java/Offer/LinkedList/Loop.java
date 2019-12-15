package Offer.LinkedList;

import java.util.LinkedHashMap;

/**
 * ��һ�����������а�������
 * ���ҳ�������Ļ�����ڽ�㣬�������null��
 */
public class Loop {
    // 1. ʹ��API��LinkedHashMap��
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
     * 2. ��ʹ��API
     * ���Ϳ�ͼƬloop
     * �׽ڵ���A������ָ��������B����㵽A == AB
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
