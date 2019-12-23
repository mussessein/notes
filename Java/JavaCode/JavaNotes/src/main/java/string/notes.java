package string;
/*
String构造器源码：
public String(String original) {
    this.value = original.value;
    this.hash = original.hash;
}
 */

/**
 * String 类 ：
 * 1. 字符串为常量，字符串对象一旦被初始化就不会被改变，可以共享
 * 2.创建字符串的时候，会先在字符串常量池中找所创建的字符串，如果没有找到，则创建， 如果找到，则不会创建新的字符串，直接使用已有的字符串。
 * String s2= s1.intern()从常量池中拿到s1赋值给s2
 * <p>
 * String类的方法：(String s;)
 * 1. 获取字符串长度 int s.length();
 * 2. 获取指定位置的字符 char charAt(int index);
 * 3. 获取指定字符第一次出现的位置 int indexOf(int ch);
 * 从指定位置开始查找 int indexOf(int ch,int fromindex);
 * 获取指定字符串第一次出现的位置 int indexOf(String str); int indexOf(String str,int fromindex); 反向索引： int lastIndexOf();
 * 4. 获取字符串中的子串 String substring(int beginIndex,int endindex);
 * 字串从begin开始（begin可以不写，默认0），end-1 处结束！！
 * <p>
 * 5. 字符串变成字符串数组（字符串切割） String[] split(String regex);正则表达式
 * 6. 将字符串变成字符数组 char[] toCharArray();
 * 7. 字符串转换大小写 大写 ：String toUpperCase();
 * 小写 : String toLowerCase();
 * 8. 字符(串)替换 String replace(char oldch,char newcha);
 * 9. 拆分字符串：
 * str.split(" ",2) 以正则表达式拆分，见空格就拆，2表示应用次数，可以不写次数，表示拆到底
 * *****
 * 9. 自动去除字符串两端的空格 .trim();
 * 10. 字符串连接 ： "str".concat(String)
 * 11. 改变基本数据类型 String.valueOf();
 * ***** 判断：
 * 1. 比较字符串内容： .equals( ); 区分大小写 .equalsIgnoreCase();不区分大小写
 * 2. 判断是是否含有某字符串 boolean startsWith(String);
 * 3. 判断字符串是否以指定字符串开头，是否以指定字符串结尾 boolean stratWith(str); boolean endsWith(str);
 * <p>
 * *****
 * 比较：
 * str1.compareTo(str2);
 */
/*
String被声明为final，不可被继承，不可变
好处：
    1.可以缓存 hash 值：
        因为 String 的 hash 值经常被使用，
        例如 String 用做 Container.Map.HashMap 的 key。不可变的特性可以使得 hash 值也不可变，因此只需要进行一次计算。
    2.String Pool 的需要：
        如果一个 String 对象已经被创建过了，那么就会从 String Pool 中取得引用。
        只有 String 是不可变的，才可能使用 String Pool。
    3.安全性：
        String经常被用作参数，不可变可以保证参数不可变
    4.线程安全：
        天然线程安全
 */
/*
String 不可变
StringBuffer  可变，非线程安全
StringBuilder 可变，线程安全，内部使用synchronized同步
 */
class notes {

    public static void main(String[] args) {
        // 在常量池中创建一个对象：
        String s = "abc"; // 创建对象
        String s1 = "abc";// 调用对象

        // 使用构造函数创建了两个对象，一个new，一个字符串对象在堆内存中
        String s2 = new String("abc");
        // 将s的字符串入池，再返回s9
        String s9 = s2.intern();

        System.out.println(s9 == s);
        System.out.println(s == s1); // 常量池中的同一字符串只有一个，指向的地址相同
        System.out.println(s == s2); // 地址不一样
        System.out.println(s.equals(s2)); // String类重写了equals方法，仅比较字符串的值

        // String构造函数 可以将数组变成字符串
        byte[] arr = {97, 66, 67, 68};
        String s3 = new String(arr);
        System.out.println("s3= " + s3);

        char[] arr1 = {'w', 'a', 'b', 'c', 'd'};
        String s4 = new String(arr1);
        // 将数组的一部分，变成字符串：
        String s5 = new String(arr1, 1, 3);
        System.out.println("s4= " + s4);
        System.out.println("s5= " + s5);

        System.out.println("s4.length= " + s4.length());
        System.out.println("s4第四个字符为： " + s4.charAt(3));
        System.out.println("s4中'a'在哪里：" + s4.indexOf('a'));
        System.out.println("s中ab出现的位置：" + s.indexOf("ab"));
        System.out.println("s4中'a'在哪里：" + s4.lastIndexOf('a'));
        // indexOf 结果为-1的时候说明所查找的字符不存在
        System.out.println("s4中是否有'k'字符: " + s4.indexOf('k'));

        // 切割字符串：
        String s6 = "张三,李四,牛五";
        String[] arr3 = s6.split(",");
        for (int i = 0; i < arr3.length; i++) {
            System.out.println(arr3[i]);
        }
        // 字符串转换为字符数组：
        char[] arr4 = s6.toCharArray();
        for (int j = 0; j < arr4.length; j++) {
            System.out.println(arr4[j]);
        }

        // 将字符串变为byte序列 储存到byte数组中（ASSIC码表示）
        String s7 = "ab我";
        byte[] b1 = s7.getBytes();
        for (int i = 0; i < b1.length; i++) {
            System.out.print(b1[i] + ",");
        }
        System.out.println("\r"); // 换行，相当于键入 Enter
        // 转换大小写
        System.out.println(s.toUpperCase());

        // 消除两端空格
        String s8 = "    -   a d   -      ";
        System.out.println(s8);
        System.out.println(s8.trim());
        // 字符串连接
        System.out.println(s.concat(s4));
        // String.valueOf
        System.out.println(String.valueOf(4) + 1);// 进行字符串相连接

        // 判断字符串是否以指定字符串开头，是否以指定字符串结尾
        String str = "Arraydemo.java";
        System.out.println(str.startsWith("Array")); // 返回boolean型
        System.out.println(str.endsWith(".java"));
        System.out.println(str.contains("demo"));
        // 比较：
        System.out.println("abc".compareTo("defg"));// 返回int类型，比较ASSIC码

    }
}