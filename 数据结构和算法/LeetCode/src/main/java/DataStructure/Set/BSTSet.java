package DataStructure.Set;

import DataStructure.Tree.BinarySearchTree;

/*
���ڶ���������BinarySearchTree��Set
���������������֧�ּ��ϵĲ���
��BSTSet�У�
    ��������h:(ÿ�α�������ֻ��ӷֲ洦��һ�������ߣ�����ȫ������)
    add��O��h���� / contains��O��h���� / remove��O��h����

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
