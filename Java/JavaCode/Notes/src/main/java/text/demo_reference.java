package text;

/**
 * 一：引用传递（pass by reference）
 * 1.引用传递本身是局部变量通过引用全局变量的内存地址，拿到全局变量的值
 *   如果不针对局部变量进行处理，此局部变量引用的地址就是全局变量的地址
 * 2.如果函数内部对局部变量进行处理，
 *   则java自动创建一个新的内存地址，保存处理过的局部变量，全局变量仍然不会受到影响
 * */

/**
 * 二：值传递（pass by value）
 *
 */
public class demo_reference {

  public static void demo_1(int num) {

    int hashcode = System.identityHashCode(num);
    System.out.println("不进行处理的局部变量num地址:" + hashcode);
  }

  public static void demo_2(int num) {
    num += 1;
    int hashcode = System.identityHashCode(num);
    System.out.println("进行计算之后的局部变量num地址：" + hashcode);
  }


  public static void main(String[] args) {

    /*
    一：引用传递：
      1.定义一个全局变量  ，并打印内存地址
      2.查看两个局部变量的内存地址
     */
    int gl_num = 99;
    int hashcode = System.identityHashCode(gl_num);
    System.out.println("全局变量num地址:" + hashcode);

    demo_1(gl_num);
    demo_2(gl_num);
    System.out.println(gl_num);


  }
}
