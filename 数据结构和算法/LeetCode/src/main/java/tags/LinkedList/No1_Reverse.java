package tags.LinkedList;

/**
 * @author whr
 * @date 2019/12/23 11:54
 * 反转链表
 */
public class No1_Reverse {
    /**
     * 从头到尾反转一个单链表（三层：rep，head，next）
     * 1. pre保存前一个节点
     * 2. head为当前节点，head.next = pre 反转逻辑
     * 3. 每次结束head = next;
     */
    public ListNode reverseList_1(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


}
