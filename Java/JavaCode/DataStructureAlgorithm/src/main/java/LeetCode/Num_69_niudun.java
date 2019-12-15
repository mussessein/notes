package LeetCode;

/**
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...
 */
public class Num_69_niudun {
    /**
     * 二分查找
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
