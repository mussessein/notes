package LeetCode;

/**非波那契
 * 每次你可以爬 1 或 2 个台阶,需要 n 阶你才能到达楼顶。
 * 一共几种方法?
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class Num_70_ClimbStairs {
    /**
     * 想象一个递归情况:爬n层的方法有f(n)
     * 每次都有两种选择:
     * 1.爬一层--->剩余的n-1个台阶有f(n-1)种方法
     * 2.爬两层--->剩余的n-2个台阶有f(n-2)种方法
     * 然后无脑递归
     * 可以得出 f(n)=f(n-1)+f(n-2)----->斐波那契数列
     * 现在就只要解方程就可以了
     * 比如5层,n=5,共有f(5)中方法--->
     * f(5)=f(4)+f(3)
     * f(4)=f(3)+f(2)
     * f(3)=f(2)+f(1)
     *     =8种
     * 1,2,3,5,8,11,....满足斐波那契数列,问题转化为求出此数列的第n项的值
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
     * (超时)
     * 无脑递归方法:参考树型结构
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
