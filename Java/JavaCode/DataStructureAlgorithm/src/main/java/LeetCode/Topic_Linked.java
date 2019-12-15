package LeetCode;

/** leetCode 203
 * 题目：(三种解法，递归法）
 * 传入一个链表的头节点和要删除的val，
 * 删除链表中等于给定值 val 的所有节点.
 * 返回删除后的链表
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
//创建链表的方法：
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
    /*
    传入数组，建立链表
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

        /** 先看头节点
         * 1.如果头节点是要删除的节点值val
         * 2.设置头节点的下一个节点为头节点
         * 3.将本来的头节点指向空，脱离链表
         */
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;//这里head是要返回的头节点
            delNode.next = null;
        }
        if (head == null) {
            return null;
        }
        /**
         * head头节点，不是要删除的节点
         * 1.将head节点，设为要查看的节点的前一个节点
         * 2.如果下一个节点是要删除的节点
         * 3.将要删除的节点放进delNode
         * 4.将本来delNode节点的下一个节点，挂在prev后面
         * 5.删除delNode，指向空，脱离链表
         * 6.else：继续向后搜索
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
虚拟头节点的方法：
1.不需要再判断头节点，因为头节点也变成了下一个节点（prev.next）
 */
class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        ListNode prev = dummyhead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;  //这里prev.next是要返回的头节点
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return dummyhead.next;
    }
}

/*
递归实现：
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