package DataStructure.UnionFind;


/*UnionFind
并查集：是一种管理元素分组情况的数据结构（树形结构实现）
可以进行以下高效操作：即：Union和Find
1.Find：查询元素a和元素b是否属于同一组
2.Union：合并元素a和元素b所在组
 */
/*
并查集的实现之一：Quick Find
复杂度：
查询是否连接：O（1）
合并集合：O（n）
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
    查找元素 p 所对应的集合的编号
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
    合并元素p和元素q所属的集合
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
