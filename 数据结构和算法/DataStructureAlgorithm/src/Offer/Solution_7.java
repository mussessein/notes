package Offer;

/**
 * ì³²¨ÄÇÆõÊýÁÐ
 * 0,1,1,2,3,5,8,13,21£¬¡£¡£¡£¡£
 */
public class Solution_7 {
    public static int Fibonacci(int n) {
        if (n == 0)
            return 0;
        else if (n <= 2)
            return 1;
        int a = 1, b = 1;
        int sum = 0;
        for (int i = 2; i < n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 7;
        int res = Fibonacci(7);
        System.out.println(res);
    }
}
