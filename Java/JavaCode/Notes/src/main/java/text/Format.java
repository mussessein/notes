package text;

/**
 * printf ��ʽ�����
 */
public class Format {

    public static void main(String[] args) {

        // 1.��ʽ���ַ������
        String s ="aaa";
        System.out.printf("%13s\n",s);
        System.out.printf("%-13s\n",s);
        // 2.��ʽ�����int
        int a=123;
        System.out.printf("%13d\n",a);
        System.out.printf("%-13d\n",a);

        // 3.��ʽ�����double
        double d = 12.3;
        System.out.printf("%13f\n",d);
        System.out.printf("%-13f",d);

    }
}
