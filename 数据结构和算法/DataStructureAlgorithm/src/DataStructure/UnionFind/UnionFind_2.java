package DataStructure.UnionFind;

/*
���鼯ʵ��֮������ɭ�ֽṹ��
UnionFind_3 �������Ż�
 */
public class UnionFind_2 implements UF {

    private int[] parent;

    public UnionFind_2(int size) {

        parent = new int[size];
        for (int i = 0; i < size; i++) {

            parent[i] = i;
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

        int pRoot=find(p);
        int qRoot=find(q);

        // p��q���ڵ���ͬ����������ͬһ�����ϣ�ֱ�ӷ���
        if (pRoot==qRoot)
            return;

        // �������һ�����ϣ���p�ĸ��ڵ�ָ��q�ĸ��ڵ�
        parent[pRoot]=qRoot;

    }
}
