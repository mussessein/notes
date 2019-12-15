package LeetCode;

/*
����һ������ nums ��һ��ֵ val��
����Ҫԭ���Ƴ�������ֵ���� val ��Ԫ�أ������Ƴ���������³��ȡ�

���� nums = [3,2,2,3], val = 3,
����Ӧ�÷����µĳ��� 2, ���� nums �е�ǰ����Ԫ�ؾ�Ϊ 2
 */
public class Num_27 {
    public int removeElement(int[] nums, int val) {

        if (nums.length==0)return 0;
        int i = 0;
        int tmp;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j]!=val){
               tmp = nums[i];
               nums[i]=nums[j];
               nums[j] =tmp;
               i++;
            }
        }
        return i;
    }
    /*
    ��ʵ����Ҫ������ֱ�Ӹ�ֵ��ȥ�Ϳ�����
     */
    public int removeElement1(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

}
