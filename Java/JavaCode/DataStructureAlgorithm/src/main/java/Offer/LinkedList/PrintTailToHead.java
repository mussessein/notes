package Offer.LinkedList;

import java.util.ArrayList;

/**
 * ��ת�����ӡ
 */
public class PrintTailToHead {
    ArrayList<Integer> res = new ArrayList<Integer>();

    // �ǵݹ�
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        while (listNode != null) {
            res.add(0, listNode.val);
            listNode = listNode.next;
        }
        return res;
    }

    // �ݹ�
    public ArrayList<Integer> printListNode(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            res.add(listNode.val);
        }
        return res;
    }
}
