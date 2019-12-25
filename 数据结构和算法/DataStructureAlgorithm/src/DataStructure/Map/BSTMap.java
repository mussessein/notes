package DataStructure.Map;

/*
基于二分搜索树的Map映射实现：
K必须实现Comparable接口，可以比较
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
    从根节点开始添加 {key,value}
    1.传入初始根节点，返回添加之后的根节点，就返回了一个新的树（添加之后的树）
    2.如果传入的key已经存在，则覆盖原来的value
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
    辅助方法:（遍历链表）通过key拿到链表中的Node
    1.通过比较大小遍历，一直到找到相同key，执行return node；
    2.如果走到最后null，也没有找到相同key，执行return null；
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
        //没找到此key，返回了一个null的node
        if (node==null)
            throw new IllegalArgumentException(key+"doesn't exist!");
        // 找到了次node，设置value为 newValue
        node.value=newValue;
    }

    //返回最小元素的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }
    /*
    执行删除操作：传入根节点，删除最小节点
    1.操作：将最左的节点脱离树：node.right=null;指向null,就脱离了
        返回删除后，补位的右节点(返回新的二分搜索树的根，最上层的根)
    2.遍历
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
    删除任意key:
    1.传入要删除的key，使用getNode，如果找到key，返回key对应的node，如果没找到，返回null
    2.找到了，进行删除，并返回次node的value
    3.没找到，不删除，return null
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
    1.递归遍历比较大小，小于走左，大于走右，找到key的节点
    2.找到key，判断是否有后续节点
    3.没有：直接删，删左补右，删右补左
    4.有后续树:（两种方案：1.修改指针，2.修改值）
        1）以key节点的右子节点为根，寻找最小节点，
        2）将最小节点的值赋给key节点，相当于删除了key
        3）删除找到的最小节点
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
        else { // 当找到了key,且
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
                //下面方法是链表方法。上面方法是普通赋值删除节点的方法
                //通过e节点的右树最小节点新建一个cur节点，代替原来e节点，并将e节点脱离
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
