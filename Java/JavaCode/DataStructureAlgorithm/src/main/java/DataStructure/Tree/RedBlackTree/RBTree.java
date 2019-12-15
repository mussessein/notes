package DataStructure.Tree.RedBlackTree;

/*
�����(����"��ƽ��"�Ķ�����) ��2-3���ȼ�
���߶ȣ�2logn
ʱ�临�Ӷ�(��ɾ�Ĳ�)��o��logn��
���ڶ���������

�ص㣺
1.ÿ���ڵ�Ҫô�죬Ҫô��
2.���ڵ��Ǻ�ɫ��
3.ÿһ��Ҷ�ӽڵ㶼�Ǻ�ɫ��
4.���һ���ڵ��Ǻ�ɫ�ģ���ô���ĺ��ӽڵ㶼�Ǻ�ɫ��
5.������һ���ڵ㵽Ҷ�ӽڵ㣬�����ĺ�ɫ�ڵ���һ���ģ���Ϊ������κ�ʱ�̶�����ȫƽ������

�޸�ԭ�еĶ���������
1.�����ɫ����boolean�������½ڵ�����ΪRED
2.add����������޸�root����ɫΪ��ɫ
3.���������ӹ���
    (1)����ת
    (2)����ת
    (3)�����ڵ�������Ԫ�صĹ��̣���ɫ��תflipcolors��
4.����������Ԫ��(������ת,������ת,����ɫ��ת)
    �޸�add����

����:ͳ�����ܸ�����(�ۺ���ɾ�Ĳ����еĲ���)
java.util�е�TreeMap��TreeSet���ǻ��ں������ʵ��
 */
public class RBTree<K extends Comparable, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // �жϽڵ�node����ɫ
    private boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color;
    }

    //   node                     x
    //  /   \     ����ת         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {

        Node x = node.right;

        // ����ת
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // ������������µ�Ԫ��(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // ���ո��ڵ�Ϊ��ɫ�ڵ�
    }

    // ����nodeΪ���ĺ�����в���Ԫ��(key, value)���ݹ��㷨
    // ���ز����½ڵ�������ĸ�
    private Node add(Node node, K key, V value) {

        if (node == null) {
            size++;
            return new Node(key, value); // Ĭ�ϲ����ɫ�ڵ�
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        return node;
    }

    // ������nodeΪ���ڵ�Ķ����������У�key���ڵĽڵ�
    private Node getNode(Node node, K key) {

        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // ������nodeΪ���Ķ�������������Сֵ���ڵĽڵ�
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // ɾ������nodeΪ���Ķ����������е���С�ڵ�
    // ����ɾ���ڵ���µĶ����������ĸ�
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

    // �Ӷ�����������ɾ����Ϊkey�Ľڵ�
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {

        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {   // key.compareTo(node.key) == 0

            // ��ɾ���ڵ�������Ϊ�յ����
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // ��ɾ���ڵ�������Ϊ�յ����
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // ��ɾ���ڵ�������������Ϊ�յ����

            // �ҵ��ȴ�ɾ���ڵ�����С�ڵ�, ����ɾ���ڵ�����������С�ڵ�
            // ������ڵ㶥���ɾ���ڵ��λ��
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }
}
