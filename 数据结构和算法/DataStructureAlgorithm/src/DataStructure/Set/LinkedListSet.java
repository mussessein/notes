package DataStructure.Set;

import DataStructure.LinkedList.LinkedList;

/*
��������ļ����ࣺԪ�ز����ظ�
��LinkedListSet�У�
    add��O��1���� / contains��O��n���� / remove��O��n����
    �ۺ�ʱ�临�Ӷȶ�Ϊ��O��n��
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;
    public LinkedListSet(){

        list=new LinkedList<>();
    }
    @Override
    public int getSize() {
        return this.list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return getSize()==0;
    }

    @Override
    public void add(E e) {
        //������ͷ���Ԫ�أ����Ӷ�O��1��
        if (!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public void remove(E e) {

        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }


}
