package DataStructure.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
二分搜索树的实现：（不包含重复元素）
    比父亲大的进入右孩子
    比父亲小的进入左孩子，以此排序
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
        添加元素：（不可重复）
            1.给定一个要插入的元素从根结点出发依次开始比较大小
            2.比根节点大，进入右树，比根节点小，进入右树。
            3.以此类推，如有重复，不做操作
         */
    public void add(E e) {
    /*
    1.判断根节点是否为空
    2.不为空->调用方法add(root e)从根节点开始添加e
     */
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    /*
    (递归)插入元素（只需要访问一边的树）：(树形结构只能插入到子叶节点的下面)
    未简化版：
    1.判断是否有相同元素，有，直接返回,没有，继续执行
    2.不存在后续节点，直接添加上去
    3.存在后续节点：进行递归（分左右）
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
        简化代码之后：
        1.不需要判断是否含有元素，如果含有元素，不做处理，直接返回
        2.不管怎么样都要插入元素：不断比较下去，最后插入元素。
        总而言之：一个递归代码分两部分：遍历和处理，分开考虑！
        处理：就是递归到最后，怎么办
        遍历：如何递归，如果有判断条件，需要考虑条件
         */
        //递归到最后的操作
        if (node == null) {
            size++;
            return new Node(e);
        }
        //遍历
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /*
    （递归）查询元素：
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        /*
        1.找到最后（null）也没有，返回false，否则返回true
        2.递归遍历
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
   （递归）二分搜索树的前序遍历:
   1.以root为根，先左后右，从root开始打印
    */
    public void preOrder() {

        preOrder(root);
    }

    private void preOrder(Node node) {
    /*
    1.处理：遍历到最后返回空
    2.遍历：左走到头，右走到头
     */
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /*
    （递归）中序遍历:
        打印开始的地方不一样，从最左下角开始打印
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
    （递归）后序遍历:
    应用：为二分搜索树释放内存。
        先遍历完孩子节点，就可以释放掉
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
    非递归的前序遍历：(递归比较快)
        利用压入堆栈，遍历
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
    bfs层序遍历：
    利用队列，按层次依次入队，实现每层遍历
     */
    public void levelOrder() {
    /*
    1.入队⑤->②->⑦  ;出队:打印5
    2.入队②->⑦->①->③: 出队:打印2
    3.入队⑦->①->③->⑥->⑧;  出队:打印7
     */
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove(); // 头结点出队
            System.out.println(cur.e);
            if (cur.left != null)
                queue.add(cur.left); // 加入到末尾
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    /*
    寻找最大节点的元素
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        return maximum(root).e;
    }

    //返回最大元素的节点
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /*
    寻找最小节点，返回最小的元素
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        return minimum(root).e;
    }

    //返回最小元素的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /*
    删除最小节点,并返回最小节点的元素
    1.找到最小节点
    2.执行删除操作
    3.返回最小节点
     */
    public E removeMin() {
        E ret = minimum();
        removeMin(root);
        return ret;
    }

    /*
    执行删除操作：传入根节点
    1.操作：将最左的节点脱离树：node.left=null;指向null,就脱离了
            返回删除后，补位的右节点(返回新的二分搜索树的根，最上层的根)
    2.遍历
     */
    private Node removeMin(Node node) {
        // 左子节点一旦为空,就说明当前节点,是最小的
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
    删除任意元素:
    1.传入要删除的元素
    2.调用remove，传入root和e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /*
    1.递归遍历比较大小，小于走左，大于走右，找到元素e的节点
    2.找到e，判断是否有后续节点
    3.没有：直接删，删左补右，删右补左
    4.有后续树:（两种方案：1.修改指针，2.修改值）
        1）以e节点的右子节点为根，寻找最小节点，
        2）将最小节点的值赋给e节点，相当于删除了e元素
        3）删除找到的最小节点
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
        } else { //当找到了e
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
        //递归比较快
    }
}


