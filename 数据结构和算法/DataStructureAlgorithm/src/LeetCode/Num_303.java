package LeetCode;

/**
 * ����һ����������  nums�������������� i �� j  (i �� j) ��Χ��Ԫ�ص��ܺͣ�
 * ���� i,  j ����
 * <p>
 * ���� nums = [-2, 0, 3, -5, 2, -1]����ͺ���Ϊ sumRange()
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 */
public class Num_303 {
    /**
     * ��һ�ַ��������Լ�������߶������Ƚϸ��ӣ���ΪJavaû�������߶����ࡣ
     *          �˷������߶�������û�����ƣ��߶������������ڸ��²�����
     * �ڶ��ַ�����sum��2-5��=sum��0-5��-sum��0-2��+nums��2����
     *           Ч�ʽϵͺ��ڴ�ռ��Ҳͦ��
     */
    private int[] sums;
    private int[] nums;

    public Num_303(int[] nums) {
        this.nums = nums;
        sums = new int[nums.length];
        int sum = 0;
        int j = 0;
        for (int i = 0; i < sums.length; i++) {
            sum += nums[j];
            sums[i] = sum;
            j++;
        }
    }

    public int sumRange(int i, int j) {
        return sums[j] - sums[i] + nums[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        Num_303 s = new Num_303(nums);
        System.out.println(s.sumRange(2, 5));
    }
}
