# CopyOnWriteArrayList

解决脏读问题；牺牲写的效率，提高读的效率

CopyOnWriteArrayList是一种读写分离的思想体现的ArrayList；

它将读写的操作对象分离开来；

写的过程中，通过复制出一片新的内存，在新的内存中执行完成写操作，再赋值回去，完成写操作；

在写的过程中，可以进行并发的读，因为操作的并不是同一片内存；

这样就避免了java.util.ConcurrentModificationException并发修改异常了；

下面一步步来看一下源码，怎么实现的？

只分析了部分常用的API的源码：读取get和写入add

## 构造器

直接看源码，不是很复杂；

```java
public class CopyOnWriteArrayList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    
    final transient ReentrantLock lock = new ReentrantLock();
    private transient volatile Object[] array;	//数据都会存储在这个array中
    // 提供set，get方法
    final Object[] getArray() {
        return array;
    }
    final void setArray(Object[] a) {
        array = a;
    }
    // ①无参构造器
    public CopyOnWriteArrayList() {
        setArray(new Object[0]); // 初始化一个长度0的array
    }
    // ②将一个Collection转化为CopyOnWriteArrayList
    public CopyOnWriteArrayList(Collection<? extends E> c) {
        Object[] elements;
        // 此Collection就是CopyOnWriteArrayList，直接赋值
        if (c.getClass() == CopyOnWriteArrayList.class)
            elements = ((CopyOnWriteArrayList<?>)c).getArray();
        else {
        // 其余都转化为Object数组赋值
            elements = c.toArray();
            if (elements.getClass() != Object[].class)
                elements = Arrays.copyOf(elements, elements.length, Object[].class);
        }
        setArray(elements);
    }
    // ③直接传入一个泛型数组
    public CopyOnWriteArrayList(E[] toCopyIn) {
        setArray(Arrays.copyOf(toCopyIn, toCopyIn.length, Object[].class));
    }
```

## add

添加方法，此类的重点方法：即：写操作的实现；

CopyOnWrite容器，即写时复制的容器；

添加元素的时候，并不直接添加，而是先将array复制一份给 Object[ ] newElements，且newElements长度加1，表示要添加一个元素；

添加的操作，都是针对newElements进行的，不对原array进行操作；

这样就将，写操作的内存，与读操作的内存分离开来，写的过程不会影响并发读取；

源码：

```java
// 添加方法
public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();// 枷锁
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);//复制出一个newElements
            newElements[len] = e;// 添加元素
            setArray(newElements);// 将添加完成的newElements赋值给原array
            return true;
        } finally {
            lock.unlock();
        }
    }
final Object[] getArray() {
    return array;
}
final void setArray(Object[] a) {
    array = a;
}
```

## get

读取操作，没有做任何的同步措施；

array并不会发生修改，只会在写操作后，直接替换，不存在数据安全问题；

```java
public E get(int index) {
    return get(getArray(), index);
}
private E get(Object[] a, int index) {
    return (E) a[index];
}
final Object[] getArray() {
    return array;
}
```

