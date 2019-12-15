package DataStructure.Queue;
/*
重新定义一个链表（添加两个‘指针’ head， tail）
实现的队列
 */
public class LinkedListQueue<E> implements Queue<E> {
    private class Node {
        public E element;
        public Node next;

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node(E element) {
            this(element, null);
        }

        public Node() {
            this(null, null);
        }

        public String toString() {
            return element.toString();
        }
    }
    //定义链首，链尾
    private Node head,tail;
    private int size;

    public LinkedListQueue(){
        head=null;
        tail=null;
        size=0;
    }

    @Override
    public int getSize() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    //入队操作 O(1)
    @Override
    public void enQueue(E element) {
        //tail=null即空链表
        if (tail==null) {
            tail = new Node(element);
            head = tail;
        }
        else{  //非空链表
            tail.next=new Node(element);
            tail =tail.next;
        }
        size++;
    }

    //出队操作
    @Override
    public E deQueue() {
        if (isEmpty())
            throw new IllegalArgumentException("空队列");
        //将链头节点脱离，更改链头
        Node retNode=head;
        head=head.next;
        //将retNode出队，脱离链表，指向空
        retNode.next=null;
        size--;
        return retNode.element;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("空队列");
        return head.element;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue:front ");
        Node cur =head;
        /*for循环写法：
        for(Node cur=dummyHead.next;cur!=null;cur=cur.next)
            res.append(cur + "->");
         */
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("tail END");
        return res.toString();
    }
}
