package Generics;

/**
 * parameterTypes
 * �ɱ�������Զ�����һ�����鴫�뷽����
 *1. �������������ɱ����������ڷ��������б�����
  2. �����б���ֻ����һ���ɱ����  
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