package LeetCode;

public class Num_152 {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1; //һ���������ģ�һ��������С�ġ�
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            } //�����������Ǹ�������ô�ᵼ�����ı���С�ģ���С�ı����ġ���˽���������ֵ��
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }

    public static void main(String[] args) {
         System.out.println(new Num_152().maxProduct(new int[]{2,3,65,-8,3,0,32,5,-188,99}));
    }
}
