package LeetCode;

/**
 * ����һ����������ɾ�������ظ���Ԫ�أ�ʹ��ÿ��Ԫ��ֻ����һ��
 * ����: 1->1->2
 * ���: 1->2
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
     * �ȵݹ� ��
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates_1(ListNode head) {
        ListNode pre = null;
        ListNode node = head;
        while(node != null){
            if(pre != null && pre.val == node.val)
                pre.next = node.next;//������ȵľ�����������һ��
            else
                pre = node;//����ȵľ�ֱ��ͨ��
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
