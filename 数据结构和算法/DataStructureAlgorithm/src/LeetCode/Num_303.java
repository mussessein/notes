package LeetCode;

/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，
 * 包含 i,  j 两点
 * <p>
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 */
public class Num_303 {
    /**
     * 第一种方法：用自己定义的线段树，比较复杂，因为Java没有内置线段树类。
     *          此方法用线段树，并没有优势，线段树的优势在于更新操作。
     * 第二种方法：sum（2-5）=sum（0-5）-sum（0-2）+nums【2】；
     *           效率较低和内存占用也挺多
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
