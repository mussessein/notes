package DataStructure.UnionFind;

/*
并查集实现之二：（森林结构）
UnionFind_3 进行了优化
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
    查找元素 p 所对应的集合的编号(p所在集合)
    目的：找到 p 的根节点
     */
    public int find(int p) {
        /*
         1.查看p是不是指向自己
         2.如果不是，继续向上搜索（看并查集实现.png）
         */
        if (p < 0 && p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        while (p != parent[p])
            p = parent[p];
        return p;

    }

    // o(h)复杂度，h为树高
    @Override
    public boolean isConnected(int p, int q) {

        return find(p) == find(q);
    }

/*
合并元素p和元素q所属的集合
o（h）复杂度
 */
    @Override
    public void unionElements(int p, int q) {

        int pRoot=find(p);
        int qRoot=find(q);

        // p，q根节点相同，本来就是同一个集合，直接返回
        if (pRoot==qRoot)
            return;

        // 如果不是一个集合，把p的根节点指向q的根节点
        parent[pRoot]=qRoot;

    }
}
