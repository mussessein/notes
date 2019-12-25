package LeetCode;

/*
给定一个数组 nums 和一个值 val，
你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。

给定 nums = [3,2,2,3], val = 3,
函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2
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
    其实不需要交换，直接赋值过去就可以了
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
