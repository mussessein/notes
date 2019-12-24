package base.search;

/**
 * @author whr
 * @date 2019/12/23 11:30
 * 二分查找——返回key的索引
 */
public class BinarySearch {

    public static int search(int[] arr, int key) {
        if (arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 5, 6, 7};
        int res = search(arr, 7);
        System.out.println(res);
    }
}
