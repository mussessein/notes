package LeetCode;
/*
������ת
����: -123
���: -321
 */
public class Num_7 {
    /*
    �ٷ���⣺�ж����
    ������Χ��2147483647~ -2147483648
        ���� 1111111113�������
     */
    public static int reverse(int x) {
        int res = 0;
        while (x!=0){
            int k = x%10;
            x/=10;
            if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE / 10 && k > 7)) return 0;
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE / 10 && k < -8)) return 0;
            res =res*10+k;
        }
        return res;
    }

    public static void main(String[] args) {
        int x =129;
        int res =reverse(x);
        System.out.println(res);
    }
}
