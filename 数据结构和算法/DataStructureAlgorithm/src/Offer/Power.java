package Offer;

/**
 * 求base的exponent次方
 * base:浮点数
 * exponent:整数
 */
public class Power {
    public static double Power(double base, int exponent) {
        int p = Math.abs(exponent);
        double result = 1.0;
        while (p > 0) {
            if ((p & 1) == 1) result *= base;
            base *= base;
            p >>= 1;
        }
        return exponent < 0 ? 1 / result : result;
    }

    public static void main(String[] args) {
        double power = Power(3.2, 10);
        System.out.println(power);
        System.out.println(Math.pow(3.2, 10));
    }
}
