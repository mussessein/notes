package DataStructure.Map;

public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {

            this(key, null, null);
        }

        public Node() {

            this(null, null, null);
        }

        public String toString() {

            return key.toString() + ":" + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    // ��������:����������ͨ��key�õ������е�Node
    private Node getNode(K key) {
        // ָ������ĵ�һ���ڵ�
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    //���ҵ���Ӧ��Node��������
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    /*
    ��ӣ��ȱ����鿴�Ƿ���ڴ�key

     */
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        // û�ҵ���key��������ͷ��Ӵ�{key,value}
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }
        // �ҵ��˴�key�����¸���ԭ����value
        else
            node.value = value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + "doesnot exist");
        } else
            node.value = value;
    }

    @Override
    public V remove(K key) {
        Node pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.key.equals(key)){
                Node delNode =pre.next;
                pre.next=delNode.next;
                delNode.next=null;
                size--;
                return delNode.value;
            }
            else
                pre.next=pre.next;
        }
        return null;
    }
}
