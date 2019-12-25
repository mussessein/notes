package DataStructure.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
������������ʵ�֣����������ظ�Ԫ�أ�
    �ȸ��״�Ľ����Һ���
    �ȸ���С�Ľ������ӣ��Դ�����
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /*
        ���Ԫ�أ��������ظ���
            1.����һ��Ҫ�����Ԫ�شӸ����������ο�ʼ�Ƚϴ�С
            2.�ȸ��ڵ�󣬽����������ȸ��ڵ�С������������
            3.�Դ����ƣ������ظ�����������
         */
    public void add(E e) {
    /*
    1.�жϸ��ڵ��Ƿ�Ϊ��
    2.��Ϊ��->���÷���add(root e)�Ӹ��ڵ㿪ʼ���e
     */
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    /*
    (�ݹ�)����Ԫ�أ�ֻ��Ҫ����һ�ߵ�������(���νṹֻ�ܲ��뵽��Ҷ�ڵ������)
    δ�򻯰棺
    1.�ж��Ƿ�����ͬԪ�أ��У�ֱ�ӷ���,û�У�����ִ��
    2.�����ں����ڵ㣬ֱ�������ȥ
    3.���ں����ڵ㣺���еݹ飨�����ң�
     */
    private Node add(Node node, E e) {
        /*if (e.equals(node.e))
            return;
        else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }
        if (e.compareTo(node.e) < 0)
            add(node.left.e);
        if (e.compareTo(node.e) > 0) {
            add(node.right, e);
        }*/
        /*
        �򻯴���֮��
        1.����Ҫ�ж��Ƿ���Ԫ�أ��������Ԫ�أ���������ֱ�ӷ���
        2.������ô����Ҫ����Ԫ�أ����ϱȽ���ȥ��������Ԫ�ء�
        �ܶ���֮��һ���ݹ����������֣������ʹ����ֿ����ǣ�
        �������ǵݹ鵽�����ô��
        ��������εݹ飬������ж���������Ҫ��������
         */
        //�ݹ鵽���Ĳ���
        if (node == null) {
            size++;
            return new Node(e);
        }
        //����
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /*
    ���ݹ飩��ѯԪ�أ�
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        /*
        1.�ҵ����null��Ҳû�У�����false�����򷵻�true
        2.�ݹ����
         */
        if (node.e == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /*
   ���ݹ飩������������ǰ�����:
   1.��rootΪ����������ң���root��ʼ��ӡ
    */
    public void preOrder() {

        preOrder(root);
    }

    private void preOrder(Node node) {
    /*
    1.������������󷵻ؿ�
    2.���������ߵ�ͷ�����ߵ�ͷ
     */
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /*
    ���ݹ飩�������:
        ��ӡ��ʼ�ĵط���һ�����������½ǿ�ʼ��ӡ
     */
    public void inOrder() {

        inOrder(root);
    }

    private void inOrder(Node node) {

        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /*
    ���ݹ飩�������:
    Ӧ�ã�Ϊ�����������ͷ��ڴ档
        �ȱ����꺢�ӽڵ㣬�Ϳ����ͷŵ�
     */
    public void postOrder() {

        postOrder(root);
    }

    private void postOrder(Node node) {

        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /*
    �ǵݹ��ǰ�������(�ݹ�ȽϿ�)
        ����ѹ���ջ������
     */
    public void preOrder_NR() {
        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    /*
    bfs���������
    ���ö��У������������ӣ�ʵ��ÿ�����
     */
    public void levelOrder() {
    /*
    1.��Ӣ�->��->��  ;����:��ӡ5
    2.��Ӣ�->��->��->��: ����:��ӡ2
    3.��Ӣ�->��->��->��->��;  ����:��ӡ7
     */
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove(); // ͷ������
            System.out.println(cur.e);
            if (cur.left != null)
                queue.add(cur.left); // ���뵽ĩβ
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    /*
    Ѱ�����ڵ��Ԫ��
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        return maximum(root).e;
    }

    //�������Ԫ�صĽڵ�
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /*
    Ѱ����С�ڵ㣬������С��Ԫ��
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        return minimum(root).e;
    }

    //������СԪ�صĽڵ�
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /*
    ɾ����С�ڵ�,��������С�ڵ��Ԫ��
    1.�ҵ���С�ڵ�
    2.ִ��ɾ������
    3.������С�ڵ�
     */
    public E removeMin() {
        E ret = minimum();
        removeMin(root);
        return ret;
    }

    /*
    ִ��ɾ��������������ڵ�
    1.������������Ľڵ���������node.left=null;ָ��null,��������
            ����ɾ���󣬲�λ���ҽڵ�(�����µĶ����������ĸ������ϲ�ĸ�)
    2.����
     */
    private Node removeMin(Node node) {
        // ���ӽڵ�һ��Ϊ��,��˵����ǰ�ڵ�,����С��
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E ret = maximum();
        removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.right = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /*
    ɾ������Ԫ��:
    1.����Ҫɾ����Ԫ��
    2.����remove������root��e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /*
    1.�ݹ�����Ƚϴ�С��С�����󣬴������ң��ҵ�Ԫ��e�Ľڵ�
    2.�ҵ�e���ж��Ƿ��к����ڵ�
    3.û�У�ֱ��ɾ��ɾ���ң�ɾ�Ҳ���
    4.�к�����:�����ַ�����1.�޸�ָ�룬2.�޸�ֵ��
        1����e�ڵ�����ӽڵ�Ϊ����Ѱ����С�ڵ㣬
        2������С�ڵ��ֵ����e�ڵ㣬�൱��ɾ����eԪ��
        3��ɾ���ҵ�����С�ڵ�
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
        } else { //���ҵ���e
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                Node cur = minimum(node.right);
                node.e = cur.e;
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


    public static void main(String[] args) {

        BinarySearchTree a = new BinarySearchTree();
        int[] arr = {5, 2, 1, 3, 7, 6, 8};
        for (int i : arr) {
            a.add(i);
        }
        a.preOrder();
        System.out.println("===============");
        a.inOrder();
        System.out.println("===============");
        a.postOrder();


        //       a.levelOrder();
//        a.removeMin();
//        a.preOrder();
        //System.out.println("size:"+a.size());
        //�ݹ�ȽϿ�
    }
}


