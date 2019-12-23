package IO.NIO;

import java.nio.CharBuffer;
/*
Bufer：缓冲区
Channel：通道
发送给一个通道的所有数据都必须首先放到缓冲区中，同样地，从通道中读取的任何数据都要先读到缓冲区中。
也就是说，不会直接对通道进行读写数据，而是要先经过缓冲区。
 */
public class demo_Buffer {

    public static void main(String[] args){
        //创建Buffer,CharBuffer是一个抽象类，allocate是创建一个缓冲区,分配空间
        CharBuffer buff=CharBuffer .allocate(8);
        System.out.println("Buffer容量："+buff.capacity());
        System.out.println("limit指针的位置："+buff.limit());
        System.out.println("position的位置："+buff.position());

        //放入元素
        buff.put("a");
        buff.put("b");
        buff.put("c");
        System.out.println("加入三个元素之后，position="+buff.position());
        //不执行flip，position位置指向最后，无法get
        //System.out.println(buff.get());

        //flip方法，封锁缓冲区，将limit指针指向最后一个字符
        buff.flip();
        System.out.println("执行flip之后，limit="+buff.limit());
        System.out.println("执行flip之后，position= "+buff.position());

        //取出第一个元素
        System.out.println("取出第一个元素"+buff.get());
        System.out.println("取出第一个元素后，position="+buff.position());

        //clean方法,并非清空缓冲区，而是修改limit指针，与flip对应，
        // 并把position重新指向第一个元素
        buff.clear();
        System.out.println("执行clean后，limit="+buff.limit());
        System.out.println("执行clean后，position="+buff.position());
        System.out.println(buff.get());
        System.out.println("执行clean后，用绝对读取取得第三个元素:"+buff.get(2));
    }
}
