package Offer.LinkedList;

import java.util.HashMap;

/**
 * �ҳ���������ĵ�һ���������
 */
public class CommonNode {
    //1. ��HashMap
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        HashMap<ListNode, Integer> hm = new HashMap<>();
        while (pHead1 != null) {
            hm.put(pHead1, null);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            if (hm.containsKey(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }
}
