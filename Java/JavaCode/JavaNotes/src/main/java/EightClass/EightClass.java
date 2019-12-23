package EightClass;
/**
 * 八类基本数据类型，
 * 为了操作方便，将八类基本数据类型封装成对象，在对象中定义了属性和行为，
 * 用于描述该对象的类就成为基本数据类型对象包装类。
 * 主要用于在基本数据类型和字符串之间的转换
 * byte         Byte    
 * short        Short
 * int          Integer (-128~127)
 * long         Long
 * float        Float
 * double       Double
 * char         Character
 * boolean      Boolean
 * 
 * 基本数据类型---->字符串
 *          1. 基本数据类型的值+" ";
 *          2. 用String类中的静态方法valueOf(基本数据类型数值)； 
 * 字符串----->基本数据类型
 *          1. 使用包装类中的静态方法   xxx.parsexxx(" "); 转换类型！！！！
 *                              例如：Integer.parseInt(" ");   （Character除外）
 *
 */
class EightClass {
    public static void main(String[] args) {
        //查看此类型最大值
        System.out.println(Integer.MAX_VALUE);
        //Integer的构造器
        System.out.println("123" + 1);
        //将字符串"123”变成带符号整数123，再进行相加
        System.out.println(Integer.parseInt("123") + 1);
        System.out.println(Boolean.parseBoolean("true"));
        //查看二进制数
        System.out.println(Integer.toBinaryString(-10));
        System.out.println(Integer.toOctalString(60));//八进制
        System.out.println(Integer.toHexString(60));//十六进制
        System.out.println(Integer.toString(60, 4));//转化为四进制
        //其他进制--->十进制
        System.out.println(Integer.parseInt("1c", 16));//十六进制的"1c"转化为十进制

        //自动装箱成对象，直接将基本数据类型赋值给对象 i 
        Integer i = 4;
        //进行运算时，自动拆箱运算
        i = i + 4;
        //基本数据类型转化为字符串！！
        System.out.println(String.valueOf(568)+1);
        System.out.println(String.valueOf(2.345f)+1);
        System.out.println(123+""+1);
    }
}