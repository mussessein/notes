package DataStructure.UnionFind;


/*UnionFind
���鼯����һ�ֹ���Ԫ�ط�����������ݽṹ�����νṹʵ�֣�
���Խ������¸�Ч����������Union��Find
1.Find����ѯԪ��a��Ԫ��b�Ƿ�����ͬһ��
2.Union���ϲ�Ԫ��a��Ԫ��b������
 */
/*
���鼯��ʵ��֮һ��Quick Find
���Ӷȣ�
��ѯ�Ƿ����ӣ�O��1��
�ϲ����ϣ�O��n��
 */
public class UnionFind_1 implements UF {

    private int[] id;

    // (UnionFind_1.png)
    public UnionFind_1(int size) {

        id = new int[size];
        for (int i = 0; i < id.length; i++) {

            id[i] = i;
        }
    }

    @Override
    public int getSize() {

        return id.length;
    }

    /*
    ����Ԫ�� p ����Ӧ�ļ��ϵı��
     */
    public int find(int p) {

        if (p < 0 && p >= id.length)
            throw new IllegalArgumentException("p is out of bound.");
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {

        return find(p) == find(q);
    }

    /*
    �ϲ�Ԫ��p��Ԫ��q�����ļ���
     */
    @Override
    public void unionElements(int p, int q) {

        int pID = find(p);
        int qID = find(q);

        if (pID == qID)
            return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID)
                id[i] = qID;
        }
    }
}
