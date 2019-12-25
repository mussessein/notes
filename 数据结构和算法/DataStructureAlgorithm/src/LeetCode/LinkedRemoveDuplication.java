package LeetCode;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * 无序单链表去重
 * 输入：【1,9,3,1,3,7,7,8】
 * 输出：【1,9,3,7,8】
 */
public class LinkedRemoveDuplication {
    public static class Node {
        int val;
        Node next;

        Node(int x) {
            val = x;
        }
    }
    public Node fun(Node head) {
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        ArrayList arrayList= new ArrayList();
        while (head != null) {
            int val = head.val;
            set.add(val);
            head=head.next;

        }
        Node cur = new Node(0);
        Node res = cur;
        for (int val:set){
            arrayList.add(val);
        }
        return res.next;
    }
}


