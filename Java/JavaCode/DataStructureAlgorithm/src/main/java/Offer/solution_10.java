package Offer;

/**
 * ��ʾ�����ƣ�
 * �������������ɶ����Ƶ�1�ĸ���
 */
public class solution_10 {
    public static int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 10;
        int count = NumberOf1(n);
        System.out.println(count);
    }
}
