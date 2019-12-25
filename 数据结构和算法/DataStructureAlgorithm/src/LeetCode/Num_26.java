package LeetCode;

import java.util.Arrays;

/*
����һ���������飬
����Ҫ��ԭ��ɾ���ظ����ֵ�Ԫ�أ�ʹ��ÿ��Ԫ��ֻ����һ�Σ������Ƴ���������³��ȡ�
(����������˫ָ��)
��ԭ�����н��в�����
�ռ临�Ӷ�O��1��
ʱ�临�Ӷ�O��n��
 */
public class Num_26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args){

        int[] nums = {1,1,1,2};
        new Num_26().removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }
}
