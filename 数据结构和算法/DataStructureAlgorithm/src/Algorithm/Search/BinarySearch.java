package Algorithm.Search;

/*
二分查找
数组必须有序
 */
public class BinarySearch {

    public static int rank(int key, int[] arr) {

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < arr[mid]) high = mid - 1;
            else if (key > arr[mid]) low = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] arr ={1,3,4,5,6,7};
        int res=rank(7,arr);
        System.out.println(res);
    }
}
