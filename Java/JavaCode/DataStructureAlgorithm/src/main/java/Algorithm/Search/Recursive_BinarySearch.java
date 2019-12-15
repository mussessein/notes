package Algorithm.Search;

/*
二分查找的递归实现
 */
public class Recursive_BinarySearch {

    public static int rank(int key, int[] arr) {
        return rank(key, arr, 0, arr.length - 1);
    }

    private static int rank(int key, int[] arr, int low, int high) {

        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (key < arr[mid])
            return rank(key, arr, low, mid - 1);

        else if (key > arr[mid])
            return rank(key, arr, mid + 1, high);
        else
            return mid;
    }
    public static void main(String[] args) {

        int[] arr ={1,3,4,5,6,7};
        int res=rank(5,arr);
        System.out.println(res);
    }
}
