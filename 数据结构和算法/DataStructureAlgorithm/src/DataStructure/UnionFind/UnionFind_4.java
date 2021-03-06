package DataStructure.UnionFind;

/*
第四版并查集
在每一棵树的根节点 记录树的深度
优化了 合并Union过程
这里rank 和 之前的sz是两个概念
sz：节点数
rank：高度
1.当 高度较小的树，合并到高度较大的树中，rank是不需要改变的
2.当 两棵树高度相同，合并之后，根节点rank+1（体会一下）
 */
public class UnionFind_4 implements UF {

    private int[] parent;
    private int[] rank;    // 表示集合中的元素

    public UnionFind_4(int size) {

        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {

            parent[i] = i;
            rank[i] = 1;  // 初始集合元素数量为1
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

        /*
         pRoot 树比较小，指向qRoot
         当 高度较小的树，合并到高度较大的树中，rank是不需要改变的
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
