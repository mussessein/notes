package DataStructure.LinkedList;

/*
链表的实现：(索引的实现)
    包括了以索引方式的add方法实现（链表一般不以索引来实现）

链表的增删改查，时间复杂度都是O（n）

链表如何发挥优势：
    1.尽量不用修改的方法。
    2.增删查，尽量只对链表头操作。
    3.如果操作次数很多，链表需要new很多次，变慢
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

    //虚拟头节点，在真正的头节点前设置一个dummyhead
    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    //获取链表元素个数
    public int getSize() {
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    以索引方式 在链表中间添加元素
     */
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed,Illegal incex.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            //执行完之后，prev所处位置为index-1
            prev = prev.next;
        }
           /*
            Node node = new Node(element);
            将prev的下一个元素，放入node的next中
            node.next=prev.next;
            将node放进prev的next
            prev.next=node;
            */
        prev.next = new Node(element, prev.next);
        size++;
    }

    /*
  链表头添加新元素
   */
    public void addFirst(E element) {

        add(0, element);
    }

    /*
    以索引方式链表末尾添加元素
     */
    public void addLast(E element) {

        add(size, element);
    }

    /*
    通过索引获得对应值
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
    查找元素
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
    按索引删除，返回删除元素
     */
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed,Illegal incex.");
        Node prev = dummyHead;
        //拿到cur（要删除的元素）之前的元素
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        //cur为要删除的元素
        Node cur = prev.next;
        prev.next = cur.next;
        //将删除的元素指向空，即从链表脱离
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
    按值，删除元素
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
        /*for循环写法：
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
