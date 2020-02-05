package tags.LinkedList;

import java.util.LinkedHashMap;

/**
 * @author whr
 * @date 2019/12/23 17:01
 * 判断单链表是否有环？
 */
public class No7_HasLoop {
    /**
     * 单纯判断是否有环
     * （1）双指针
     * （2）哈希表（HashMap，HashSet）
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    // hashMap
    public ListNode hasLoop(ListNode pHead) {
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


}
