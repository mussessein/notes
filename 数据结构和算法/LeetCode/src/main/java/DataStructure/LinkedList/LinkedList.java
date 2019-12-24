package DataStructure.LinkedList;

/*
�����ʵ�֣�(������ʵ��)
    ��������������ʽ��add����ʵ�֣�����һ�㲻��������ʵ�֣�

�������ɾ�Ĳ飬ʱ�临�Ӷȶ���O��n��

������η������ƣ�
    1.���������޸ĵķ�����
    2.��ɾ�飬����ֻ������ͷ������
    3.������������ܶ࣬������Ҫnew�ܶ�Σ�����
 */

public class LinkedList<E> {

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

    //����ͷ�ڵ㣬��������ͷ�ڵ�ǰ����һ��dummyhead
    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    //��ȡ����Ԫ�ظ���
    public int getSize() {
        return size;
    }

    //���������Ƿ�Ϊ��
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    ��������ʽ �������м����Ԫ��
     */
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed,Illegal incex.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            //ִ����֮��prev����λ��Ϊindex-1
            prev = prev.next;
        }
           /*
            Node node = new Node(element);
            ��prev����һ��Ԫ�أ�����node��next��
            node.next=prev.next;
            ��node�Ž�prev��next
            prev.next=node;
            */
        prev.next = new Node(element, prev.next);
        size++;
    }

    /*
  ����ͷ�����Ԫ��
   */
    public void addFirst(E element) {

        add(0, element);
    }

    /*
    ��������ʽ����ĩβ���Ԫ��
     */
    public void addLast(E element) {

        add(size, element);
    }

    /*
    ͨ��������ö�Ӧֵ
     */
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed,Illegal incex.");

        Node cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.element;
    }

    public E getFirst() {

        return get(0);
    }

    public E getLast() {

        return get(size - 1);
    }

    /*
    ����Ԫ��
     */
    public boolean contains(E element) {

        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.element.equals(element)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /*
    ������ɾ��������ɾ��Ԫ��
     */
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed,Illegal incex.");
        Node prev = dummyHead;
        //�õ�cur��Ҫɾ����Ԫ�أ�֮ǰ��Ԫ��
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        //curΪҪɾ����Ԫ��
        Node cur = prev.next;
        prev.next = cur.next;
        //��ɾ����Ԫ��ָ��գ�������������
        cur.next = null;
        size--;
        return cur.element;
    }
    public E removeFirst(){

        return remove(0);
    }
    public E removeLast(){

        return remove(size-1);
    }
    /*
    ��ֵ��ɾ��Ԫ��
     */
    public void removeElement(E element){
        Node prev = dummyHead;
        while (prev.next!=null){
            if (prev.next.element.equals(element))
                break;
            prev=prev.next;
        }
        if (prev.next!=null){
            Node delNode = prev.next;
            prev.next=delNode.next;
            delNode.next=null;
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        /*forѭ��д����
        for(Node cur=dummyHead.next;cur!=null;cur=cur.next)
            res.append(cur + "->");
         */
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("END");
        return res.toString();
    }


}
