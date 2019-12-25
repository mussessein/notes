package DataStructure.Queue;
/*
���¶���һ���������������ָ�롯 head�� tail��
ʵ�ֵĶ���
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
    //�������ף���β
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
    //��Ӳ��� O(1)
    @Override
    public void enQueue(E element) {
        //tail=null��������
        if (tail==null) {
            tail = new Node(element);
            head = tail;
        }
        else{  //�ǿ�����
            tail.next=new Node(element);
            tail =tail.next;
        }
        size++;
    }

    //���Ӳ���
    @Override
    public E deQueue() {
        if (isEmpty())
            throw new IllegalArgumentException("�ն���");
        //����ͷ�ڵ����룬������ͷ
        Node retNode=head;
        head=head.next;
        //��retNode���ӣ���������ָ���
        retNode.next=null;
        size--;
        return retNode.element;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("�ն���");
        return head.element;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue:front ");
        Node cur =head;
        /*forѭ��д����
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
