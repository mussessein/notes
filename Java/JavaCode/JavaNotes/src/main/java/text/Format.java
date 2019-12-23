package text;

/**
 * printf 格式化输出
 */
public class Format {

    public static void main(String[] args) {

        // 1.格式化字符串输出
        String s ="aaa";
        System.out.printf("%13s\n",s);
        System.out.printf("%-13s\n",s);
        // 2.格式化输出int
        int a=123;
        System.out.printf("%13d\n",a);
        System.out.printf("%-13d\n",a);

        // 3.格式化输出double
        double d = 12.3;
        System.out.printf("%13f\n",d);
        System.out.printf("%-13f",d);

    }
}
