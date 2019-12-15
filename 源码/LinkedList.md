# LinkedList

- 双向链表，可以当队列、栈来使用。

部分核心代码,自行调整了顺序

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
	// 静态内部类:泛型节点类
	private static class Node<E> {
   /**
     * item:相当于value,值
     * next:下一个节点
     * prev:上一个节点
     */
        E item;
        Node<E> next;
        Node<E> prev;
		
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
	// 链表长度
    transient int size = 0;
	// 头结点
    transient Node<E> first;
	// 尾节点
    transient Node<E> last;
    // 几率改变大小的次数,如果modCount改变的不符合预期，那么就会抛出异常
    // 用于检测混乱的情况
    protected transient int modCount = 0;
    
   /**
     * 构造器
     */
    public LinkedList() {
    }
	// 传入Collection对象,调用addAll方法
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }
    
   /**
     * 一些常用辅助方法
     */
   
    // 返回链表长度size
    public int size() {
        return size;
    }
    
    // 拿到第一个节点,返回item
	public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }
    // 拿到最后一个节点,返回item
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }
    // 返回头结点,并不删除节点
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }
    // 返回头结点,并删除节点,unlinkedFirst在后面有提到
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }
    public void push(E e) {
        addFirst(e);
    }
    public E pop() {
        return removeFirst();
    }
    
    // 查询元素位置
    // for (Node<E> x = first; x != null; x = x.next) 从头开始遍历链表
    // 没找到返回-1
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    
    // 判断index是否合法,不合法抛出异常
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
    
    .....
    
}
```



- 添加的方法

```java
 /**
     * add方法,从结尾添加元素
     * 每次添加元素,调用linkLast方法,从最后添加
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }
    // 从最后添加节点
    void linkLast(E e) {
        final Node<E> l = last;
        // 先新建一个节点 item=e,next=null,prev=last
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        // 如果此链表是空的,就将新节点定义为头节点
        if (l == null)
            first = newNode;
        else
        	// 链表不为空,则添加节点到最后
            l.next = newNode;
        // 更新长度
        size++;
        // 更新改变次数
        modCount++;
    }
   /**
     * add方法,插入元素
     * 在index位置插入元素
     */
    public void add(int index, E element) {
    	// 检测index是否异常 return index >= 0 && index <= size
        checkPositionIndex(index);
        // 如果index指向链表长度,直接在最后添加元素
        if (index == size)
            linkLast(element);
        else
        	// 否则
            linkBefore(element, node(index));
    }
    // node方法:找到index位置的节点(通过遍历)
    Node<E> node(int index) {
    // 将链表从中间切开,提高查找的效率 (size>>1相当于size/2)
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    
    void linkBefore(E e, Node<E> succ) {
        // succ即:node方法返回的index节点
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        // 添加新节点作为succ节点的prev
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }


	// 同样是 调用node方法,进行遍历,找到index节点,返回index节点的value
	public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }
    // node方法,找到index节点,赋新值,返回旧值
	public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }
        
   /**
     * 从头添加元素
     */
    public void addFirst(E e) {
        linkFirst(e);
    }
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        // 如果此链表为空,将新节点作为尾节点
        if (f == null)
            last = newNode;
        else
        // 否则,新节点作为头结点的prev
            f.prev = newNode;
        size++;
        modCount++;
    }
```





- 删除的方法

```java
/**
     * 删除头节点
     */

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }
	// 传入要删除节点
    private E unlinkFirst(Node<E> f) {
        // 
        final E element = f.item;
        final Node<E> next = f.next;
        // 直接将此节点分离,item赋值null,官方文档解释有助于GC垃圾收集
        f.item = null;
        f.next = null; // help GC
        // f节点的next作为头节点
        first = next;
        // next==null说明,此链表就只有f节点一个节点
        if (next == null)
            last = null;
        else
            // 作为头结点,prev为null
            next.prev = null;
        size--;
        modCount++;
        return element;
    }
    /**
     * 删除尾节点,同上
     */
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }
    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }

/**
     * remove方法,删除任意节点
     * for (Node<E> x = first; x != null; x = x.next)
     * 从头遍历到结尾
     */
    public boolean remove(Object o) {
    // 删除null节点
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);	// 删除节点
                    return true;
                }
            }
        }
        return false;
    }
    // 删除某个节点的操作
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;
		// 如果是此节点是头结点,
        if (prev == null) {
            first = next;
        } else {
        // 如果不是头结点,分离此节点
            prev.next = next;
            x.prev = null;
        }
		// 如果没有next节点,就将prev作为尾节点
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
		
        x.item = null;
        size--;
        modCount++;
        // 返回删除节点的value
        return element;
    }

```

