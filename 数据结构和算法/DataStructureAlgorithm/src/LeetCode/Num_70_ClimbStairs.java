package LeetCode;

/**�ǲ�����
 * ÿ��������� 1 �� 2 ��̨��,��Ҫ n ������ܵ���¥����
 * һ�����ַ���?
 * ���룺 3
 * ����� 3
 * ���ͣ� �����ַ�����������¥����
 * 1.  1 �� + 1 �� + 1 ��
 * 2.  1 �� + 2 ��
 * 3.  2 �� + 1 ��
 */
public class Num_70_ClimbStairs {
    /**
     * ����һ���ݹ����:��n��ķ�����f(n)
     * ÿ�ζ�������ѡ��:
     * 1.��һ��--->ʣ���n-1��̨����f(n-1)�ַ���
     * 2.������--->ʣ���n-2��̨����f(n-2)�ַ���
     * Ȼ�����Եݹ�
     * ���Եó� f(n)=f(n-1)+f(n-2)----->쳲���������
     * ���ھ�ֻҪ�ⷽ�̾Ϳ�����
     * ����5��,n=5,����f(5)�з���--->
     * f(5)=f(4)+f(3)
     * f(4)=f(3)+f(2)
     * f(3)=f(2)+f(1)
     *     =8��
     * 1,2,3,5,8,11,....����쳲���������,����ת��Ϊ��������еĵ�n���ֵ
     */
    public static int climbStairs(int n) {

        if (n < 3) {
            return n;
        }
        int a = 1;
        int b = 2;
        int c = 0;
        for (int i = 2; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }


    /**
     * (��ʱ)
     * ���Եݹ鷽��:�ο����ͽṹ
     * https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode/
     */
    public static int climbStairs_1(int n) {

        int i = 0;
        return climbStairs_2(i, n);
    }
    private static int climbStairs_2(int i, int n) {

        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climbStairs_2(i + 1, n) + climbStairs_2(i + 2, n);
    }

    public static void main(String[] args) {

        int kinds = climbStairs(44);
        System.out.println(kinds);
    }
}
