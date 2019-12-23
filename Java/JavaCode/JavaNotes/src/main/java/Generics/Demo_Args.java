package Generics;

/**
 * parameterTypes
 * 可变参数，自动当成一个数组传入方法内
 *1. 如果多个参数，可变参数必须放在方法参数列表的最后。
  2. 参数列表中只能有一个可变参数  
 */
public class Demo_Args {

    public static void add_1(int... nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        add_1(4, 5, 7);
        add_1(7,8,9,0,67,6);    
    }
}