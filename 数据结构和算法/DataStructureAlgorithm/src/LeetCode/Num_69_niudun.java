package LeetCode;

/**
 * ���㲢���� x ��ƽ���������� x �ǷǸ�������
 * ����: 8
 * ���: 2
 * ˵��: 8 ��ƽ������ 2.82842...
 */
public class Num_69_niudun {
    /**
     * ���ֲ���
     * @param x
     * @return
     */
    public static int mySqrt(int x) {

        int left=1;
        int right=x/2+1;
        while (left<=right) {

            int mid =left+(right-left)/2;
            if (mid*mid==x)
                return mid;
            else if (mid < x / mid)
                left=mid+1;
            else
                right=mid-1;
        }
        return right;
    }
    public static int niudun(int x) {
        if (x<=1) return x;
        long r = x;
        while(r>x/r){
            r=(r+x/r)/2;
        }
        return (int)r;
    }

    public static void main(String[] args) {
        System.out.println(niudun(2147483647));

    }
}
