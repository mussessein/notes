package string;

/**
 * StringBuffer （线程同步，用于多线程）
 * 就是一个字符缓冲区；
 * 初始容量为16个字符；相当于封装了数组，创建该对象相当于创建一个16字符数组；
 * 用于存储数据的容器；
 * 特点：
 * 1. 长度是可变的；
 * 2. 可以存储不同类型的数据；
 * 3. 最终要转成字符串进行处理；
 * 
 * 功能：
 * 1. 添加 ：append(); 返回StringBuffer;
 *          insert( , );插入
 * 2. 删除 ：delete( start,end );含头，不含尾
 *          deleteCharAt(index);删除指定位置的元素
 * 3. 查找 ：char  charAt(index);
 *          int   indexOf(String);
 *          int  lastIndexOf(String)
 * 4. 修改 ：StringBuffer  replace(start,end,String);
 *           void setCharAt(index,char);
 * 
 * StrigBuilder（线程不同步，用于单线程，运行更快）
 * JDK 5 开始，新出与StringBuffer想同功能的API；
 * StringBuffer不实现线程同步，所以运行速度更快。
 *           
 * 为什么会有线程安全隐患？
 *      在StringBuffer的方法中如果同时存在添加和删除元素，两个线程就会存在安全隐患问题。
 *    所以StringByffer内自动完成线程同步，每次调用方法都会判断锁！
 *    StringBuilder多用于单线程，不需要同步，运行速度更快，效率更高 ！
 */
class String_buffer {

    public static void main(String[] args) {

        StringBuffer s = new StringBuffer();
        StringBuffer s1 = s.append(4);
        s.append(true);
        System.out.println(s == s1);
    //缓冲区可以存储任何类型的数据
        System.out.println(s);
    //指定位置插入字符串：
        s.insert(1, "haha");
        System.out.println(s);
    //设置缓冲区长度,长度不够自动添加空格
        s.setLength(15);
        System.out.println("s=" + s);
    // 清空缓冲区
        s.delete(0, s.length());
    //如果放置的字符串超过16个字符，缓冲区会自动增加长度
        StringBuffer s2 = new StringBuffer("asdfghjkloiuytrewqwxcv");
        System.out.println(s2);
        
    }
}