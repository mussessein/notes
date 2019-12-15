package DataStructure.Set;

import DataStructure.LinkedList.LinkedList;

/*
基于链表的集合类：元素不可重复
在LinkedListSet中：
    add【O（1）】 / contains【O（n）】 / remove【O（n）】
    综合时间复杂度都为：O（n）
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
        //在链表头添加元素，复杂度O（1）
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
