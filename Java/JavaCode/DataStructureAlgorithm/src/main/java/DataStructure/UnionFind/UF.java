package DataStructure.UnionFind;

/*
���鼯 �ӿ�
 */
public interface UF {

    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
