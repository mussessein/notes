package LeetCode;

/** leetCode 203
 * ��Ŀ��(���ֽⷨ���ݹ鷨��
 * ����һ�������ͷ�ڵ��Ҫɾ����val��
 * ɾ�������е��ڸ���ֵ val �����нڵ�.
 * ����ɾ���������
 * ����: 1->2->6->3->4->5->6, val = 6
 * ���: 1->2->3->4->5
 */
//��������ķ�����
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
    /*
    �������飬��������
     */
    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("end");
        return res.toString();
    }
}

class Solution1 {
    public ListNode removeElements(ListNode head, int val) {

        /** �ȿ�ͷ�ڵ�
         * 1.���ͷ�ڵ���Ҫɾ���Ľڵ�ֵval
         * 2.����ͷ�ڵ����һ���ڵ�Ϊͷ�ڵ�
         * 3.��������ͷ�ڵ�ָ��գ���������
         */
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;//����head��Ҫ���ص�ͷ�ڵ�
            delNode.next = null;
        }
        if (head == null) {
            return null;
        }
        /**
         * headͷ�ڵ㣬����Ҫɾ���Ľڵ�
         * 1.��head�ڵ㣬��ΪҪ�鿴�Ľڵ��ǰһ���ڵ�
         * 2.�����һ���ڵ���Ҫɾ���Ľڵ�
         * 3.��Ҫɾ���Ľڵ�Ž�delNode
         * 4.������delNode�ڵ����һ���ڵ㣬����prev����
         * 5.ɾ��delNode��ָ��գ���������
         * 6.else�������������
         */
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }
}

/*
����ͷ�ڵ�ķ�����
1.����Ҫ���ж�ͷ�ڵ㣬��Ϊͷ�ڵ�Ҳ�������һ���ڵ㣨prev.next��
 */
class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        ListNode prev = dummyhead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;  //����prev.next��Ҫ���ص�ͷ�ڵ�
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return dummyhead.next;
    }
}

/*
�ݹ�ʵ�֣�
 */
class RecursiveSolution3{

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next= removeElements(head.next,val);
//        if (head.val==val) {
//            return  head.next;
//        }
//        else {
//            return head;
//        }
        return head.val==val ? head.next:head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2};
        ListNode head =new ListNode(arr);
        System.out.println(head);

        ListNode res=(new RecursiveSolution3().removeElements(head,2));
        System.out.println(head);
    }
}