package LeetCode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次
 * 输入: 1->1->2
 * 输出: 1->2
 */
public class Num_83_LinkedList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        else if (head.next != null) {
            ListNode cur = head.next;
            if (cur.val == head.val) {
                head.next = cur.next;
                deleteDuplicates(head);
            }
            deleteDuplicates(head.next);
        }
        return head;
    }

    /**
     * 比递归 快
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates_1(ListNode head) {
        ListNode pre = null;
        ListNode node = head;
        while(node != null){
            if(pre != null && pre.val == node.val)
                pre.next = node.next;//遇到相等的就跳过连到下一个
            else
                pre = node;//不相等的就直接通过
            node = node.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(1);
        ListNode l3=new ListNode(1);
        head.next=l1;
        l1.next=l2;
        l2.next=l3;
        deleteDuplicates_1(head);
    }
}
