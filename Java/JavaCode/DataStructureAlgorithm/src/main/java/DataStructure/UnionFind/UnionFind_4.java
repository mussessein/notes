package DataStructure.UnionFind;

/*
���İ沢�鼯
��ÿһ�����ĸ��ڵ� ��¼�������
�Ż��� �ϲ�Union����
����rank �� ֮ǰ��sz����������
sz���ڵ���
rank���߶�
1.�� �߶Ƚ�С�������ϲ����߶Ƚϴ�����У�rank�ǲ���Ҫ�ı��
2.�� �������߶���ͬ���ϲ�֮�󣬸��ڵ�rank+1�����һ�£�
 */
public class UnionFind_4 implements UF{

    private int[] parent;
    private int[] rank;    // ��ʾ�����е�Ԫ��

    public UnionFind_4(int size) {

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

        /*
         pRoot ���Ƚ�С��ָ��qRoot
         �� �߶Ƚ�С�������ϲ����߶Ƚϴ�����У�rank�ǲ���Ҫ�ı��
         */
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        }
        else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }
        else {
            parent[qRoot] = pRoot;
            rank[pRoot]+=1;
        }

    }
}
