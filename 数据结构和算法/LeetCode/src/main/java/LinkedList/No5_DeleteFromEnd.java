package LinkedList;

/**
 * @author whr
 * @date 2019/12/23 16:29
 * 删除倒数第n个节点
 */
public class No5_DeleteFromEnd {

    public ListNode delete(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        int len = 0;
        // 求链表总长度
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        len = len - n;
        // 这里是从虚拟节点开始
        cur = dummy;
        // 锁定倒数第n的节点
        while (len > 0) {
            len--;
            cur = cur.next;
        }
        // 删除
        cur.next = cur.next.next;
        return dummy.next;
    }

}
