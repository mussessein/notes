package DataStructure.Tree;

/*
�߶�������һ������ȫ������
����һ��ƽ�������
ƽ���������ÿ������֮��Ż������һ�㣬��û�в����ĵ����ڶ���
��ȫ��������ƽ����������Ӽ�
 */
public class SegmentTree<E> {

    /*
    ����һ��������߶���
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

    // ��treeIndexλ�ô������䡾l-r�����߶νڵ�
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // �ݹ鵽β
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        // �ҵ������Ӻ���
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // �ݹ鴴������
        int mid = l + (r - l) / 2;
        // �����ӽڵ㣬����l��mid����
        buildSegmentTree(leftTreeIndex, l, mid);
        // ���Һ��ӽڵ㣬����mid+1��r����
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
�ҵ��˽ڵ��µ�leftChild������
    */
    private int leftChild(int index) {

        return index * 2 + 1;
    }

    /*
�ҵ��˽ڵ��µ�rightChild������
 */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // �������䡾queryl��queryr����ֵ
    public E query(int queryL, int queryR) {

        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("�Ƿ�����");
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // �ڸ��ڵ�0����0��data,length��ʼ���� ���䡾queryl��queryr����ֵ
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // �ҵ���Ҫ��Ľڵ㣬�򷵻�
        if (queryL == l && queryR == r) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        /**
         * ��Ϊ���������
         * 1.ȫ�ڵ�ǰ�ڵ������
         * 2.ȫ�ڵ�ǰ�ڵ���Һ���
         * 3.��ǰ�ڵ����Ҷ���
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
     * ���²���
     * ���������ĳ��ֵ������Ҫ��������߶���
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("�Ƿ�����");
        }
        // ���������ֵ
        data[index] = e;
        // ��ʼ�����߶���
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
