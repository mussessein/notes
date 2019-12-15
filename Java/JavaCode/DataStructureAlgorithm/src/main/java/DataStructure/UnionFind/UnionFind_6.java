package DataStructure.UnionFind;

/*
·��ѹ���Ż�
�����Ż�find����
�ݹ�ʵ��find����
 */
public class UnionFind_6 implements UF {
    private int[] parent;
    private int[] rank;    // ��ʾ�����е�Ԫ��

    public UnionFind_6(int size) {

        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {

            parent[i] = i;
            rank[i] = 1;  // ��ʼ����Ԫ������Ϊ1
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
         2.������ǣ�������������
         3.�Ե������ߵ�����ҵ����ڵ㣨���ڵ�ָ������p=parent[p]��
         4.���ݵĹ����У�ÿ���ڵ㶼ָ���˸��ڵ�parent[p] = find(parent[p]);
            �����find(parent[p])�Ѿ�����˸��ڵ�
         */
        if (p < 0 && p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p]; // �Ե������ߵ�����ҵ����ڵ㣨���ڵ�ָ������p=parent[p]��
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

        /*
         pRoot ���Ƚ�С��ָ��qRoot
         �� �߶Ƚ�С�������ϲ����߶Ƚϴ�����У�rank�ǲ���Ҫ�ı��
         */
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }

    }
}
