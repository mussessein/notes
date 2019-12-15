package DataStructure.UnionFind;

/*
对UnionFind_2 对size进行优化
考虑到 每棵树的size大小，进行限制
优化：在合并的时候，将较小的树，指向较大树的根节点
目的：降低合并之后的树高h

UnionFind_4 继续优化
 */
public class UnionFind_3 implements UF {

    private int[] parent;
    private int[] sz;    // 表示集合中的元素

    public UnionFind_3(int size) {

        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {

            parent[i] = i;
            sz[i] = 1;  // 初始集合元素数量为1
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

        int pRoot = find(p);
        int qRoot = find(q);

        // p，q根节点相同，本来就是同一个集合，直接返回
        if (pRoot == qRoot)
            return;

        // pRoot 树比较小，指向qRoot
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            // 集合成员数量相加
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }
}
