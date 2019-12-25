package DataStructure.Map;

/*
���ڶ�����������Mapӳ��ʵ�֣�
K����ʵ��Comparable�ӿڣ����ԱȽ�
 */
public class BSTMap<K extends Comparable, V> implements Map<K, V> {

    private class Node {

        public K key;
        public V value;
        public Node right, left;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
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

    @Override
    /*
    �Ӹ��ڵ㿪ʼ��� {key,value}
    1.�����ʼ���ڵ㣬�������֮��ĸ��ڵ㣬�ͷ�����һ���µ��������֮�������
    2.��������key�Ѿ����ڣ��򸲸�ԭ����value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {

        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else // key.compareTo(node.key) = 0
            node.value = value;
        return node;
    }

    /*
    ��������:����������ͨ��key�õ������е�Node
    1.ͨ���Ƚϴ�С������һֱ���ҵ���ͬkey��ִ��return node��
    2.����ߵ����null��Ҳû���ҵ���ͬkey��ִ��return null��
     */
    private Node getNode(Node node, K key) {

        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        else
            return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }
    @Override
    public void set(K key, V newValue) {
        Node node =getNode(root,key);
        //û�ҵ���key��������һ��null��node
        if (node==null)
            throw new IllegalArgumentException(key+"doesn't exist!");
        // �ҵ��˴�node������valueΪ newValue
        node.value=newValue;
    }

    //������СԪ�صĽڵ�
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }
    /*
    ִ��ɾ��������������ڵ㣬ɾ����С�ڵ�
    1.������������Ľڵ���������node.right=null;ָ��null,��������
        ����ɾ���󣬲�λ���ҽڵ�(�����µĶ����������ĸ������ϲ�ĸ�)
    2.����
    */
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }
    @Override
 /*
    ɾ������key:
    1.����Ҫɾ����key��ʹ��getNode������ҵ�key������key��Ӧ��node�����û�ҵ�������null
    2.�ҵ��ˣ�����ɾ���������ش�node��value
    3.û�ҵ�����ɾ����return null
     */
    public V remove(K key){

        Node node=getNode(root,key);
        if (node!=null){
            root=remove(root,key);
            return node.value;
        }
        return null;
    }
    /*
    1.�ݹ�����Ƚϴ�С��С�����󣬴������ң��ҵ�key�Ľڵ�
    2.�ҵ�key���ж��Ƿ��к����ڵ�
    3.û�У�ֱ��ɾ��ɾ���ң�ɾ�Ҳ���
    4.�к�����:�����ַ�����1.�޸�ָ�룬2.�޸�ֵ��
        1����key�ڵ�����ӽڵ�Ϊ����Ѱ����С�ڵ㣬
        2������С�ڵ��ֵ����key�ڵ㣬�൱��ɾ����key
        3��ɾ���ҵ�����С�ڵ�
     */
    private Node remove(Node node,K key){
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key)<0){
            node.left=remove(node.left,key);
        }
        else if (key.compareTo(node.key)>0){
            node.right=remove(node.right,key);
        }
        else { // ���ҵ���key,��
            if (node.left==null){
                Node rightNode = node.right;
                node.right=null;
                size--;
                return rightNode;
            }
            else if (node.right==null){
                Node leftNode = node.left;
                node.left=null;
                size--;
                return leftNode;
            }
            else {
                Node cur=minimum(node.right);
                node.key=cur.key;
                removeMin(node.right);
                return node;
                /*
                //���淽���������������淽������ͨ��ֵɾ���ڵ�ķ���
                //ͨ��e�ڵ��������С�ڵ��½�һ��cur�ڵ㣬����ԭ��e�ڵ㣬����e�ڵ�����
                Node cur = minimum(node.right);
                cur.right = removeMin(node.right);
                cur.left = node.left
                node.left=node.right=null;
                return cur;
                 */
            }
        }
        return node;
    }





}
