package DataStructure.Tree;

/*
线段树：不一定是完全二叉树
但是一个平衡二叉树
平衡二叉树：每层满了之后才会进行下一层，即没有不满的倒数第二层
完全二叉树是平衡二叉树的子集
 */
public class SegmentTree<E> {

    /*
    定义一个数组和线段树
     */
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在treeIndex位置创建区间【l-r】的线段节点
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归到尾
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        // 找到左右子孩子
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 递归创建区间
        int mid = l + (r - l) / 2;
        // 在左孩子节点，创建l～mid区间
        buildSegmentTree(leftTreeIndex, l, mid);
        // 在右孩子节点，创建mid+1～r区间
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal!");
        return data[index];
    }

    /*
找到此节点下的leftChild的索引
    */
    private int leftChild(int index) {

        return index * 2 + 1;
    }

    /*
找到此节点下的rightChild的索引
 */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 返回区间【queryl～queryr】的值
    public E query(int queryL, int queryR) {

        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("非法参数");
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在根节点0，从0～data,length开始查找 区间【queryl～queryr】的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 找到了要查的节点，则返回
        if (queryL == l && queryR == r) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        /**
         * 分为三种情况：
         * 1.全在当前节点的左孩子
         * 2.全在当前节点的右孩子
         * 3.当前节点左右都有
         */
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        } else {
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            return merger.merge(rightResult, leftResult);
        }
    }

    /**
     * 更新操作
     * 更新数组的某个值，就需要更新这个线段树
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("非法数组");
        }
        // 更新数组的值
        data[index] = e;
        // 开始更新线段树
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index>=mid+1){
            set(rightTreeIndex,mid+1,r,index,e);
        }
        else
            set(leftTreeIndex,mid+1,r,index,e);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");
            if (i != tree.length - 1)
                res.append(',');
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> st = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(st);
        System.out.println(st.query(2, 5));
    }
}
