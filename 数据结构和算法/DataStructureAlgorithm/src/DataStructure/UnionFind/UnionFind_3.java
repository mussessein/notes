package DataStructure.UnionFind;

/*
��UnionFind_2 ��size�����Ż�
���ǵ� ÿ������size��С����������
�Ż����ںϲ���ʱ�򣬽���С������ָ��ϴ����ĸ��ڵ�
Ŀ�ģ����ͺϲ�֮�������h

UnionFind_4 �����Ż�
 */
public class UnionFind_3 implements UF {

    private int[] parent;
    private int[] sz;    // ��ʾ�����е�Ԫ��

    public UnionFind_3(int size) {

        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {

            parent[i] = i;
            sz[i] = 1;  // ��ʼ����Ԫ������Ϊ1
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /*
    ����Ԫ�� p ����Ӧ�ļ��ϵı��(p���ڼ���)
    Ŀ�ģ��ҵ� p �ĸ��ڵ�
     */
    public int find(int p) {
        /*
         1.�鿴p�ǲ���ָ���Լ�
         2.������ǣ��������������������鼯ʵ��.png��
         */
        if (p < 0 && p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        while (p != parent[p])
            p = parent[p];
        return p;

    }

    // o(h)���Ӷȣ�hΪ����
    @Override
    public boolean isConnected(int p, int q) {

        return find(p) == find(q);
    }

    /*
    �ϲ�Ԫ��p��Ԫ��q�����ļ���
    o��h�����Ӷ�
     */
    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        // p��q���ڵ���ͬ����������ͬһ�����ϣ�ֱ�ӷ���
        if (pRoot == qRoot)
            return;

        // pRoot ���Ƚ�С��ָ��qRoot
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            // ���ϳ�Ա�������
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }
}
