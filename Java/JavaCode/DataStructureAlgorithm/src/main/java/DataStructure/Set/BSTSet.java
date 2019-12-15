package DataStructure.Set;

import DataStructure.Tree.BinarySearchTree;

/*
基于二分搜索树BinarySearchTree的Set
二分搜索树本身就支持集合的操作
在BSTSet中：
    假设树高h:(每次遍历，都只会从分叉处往一个方法走，不会全部遍历)
    add【O（h）】 / contains【O（h）】 / remove【O（h）】

 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTree<E> bst;

    public BSTSet(){

        bst=new BinarySearchTree<>();
    }
    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return getSize()==0;
    }
    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }


}
