package LinkedList;

/**
 * @author whr
 * @date 2019/12/23 17:20
 * 判断是否有环，并找出环的入口节点
 */
public class No8_FindLoop {

    /**
     * 判断是否有环，并找到环的入口
     * 快慢指针，参考图示（AB=CB）
     */
    public ListNode findLoop(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean flag = false;
        // 如果不是环，就会跳出while，且 flag=false
        while (slow.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                flag = true;
                break;
            }
        }
        // 相遇了，说明是环
        if (flag) {
            ListNode cur = head;
            while (cur != fast) {
                cur = cur.next;
                fast = fast.next;
            }
            return cur;
        } else {
            return null;
        }
    }
}
