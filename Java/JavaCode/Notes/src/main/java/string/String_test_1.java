package string;

import java.util.Arrays;

/** ×Ö·û´®ÅÅÐò °´ASSICÂë */
class String_test_1 {

  public static void main(String[] args) {

    String[] arr = {"nba", "abc", "cba", "zz", "qq", "haha"};
    // System.out.println(Arrays.toString(arr));
    printArray(arr);
    sortString(arr);
    printArray(arr);
  }

  public static void printArray(String[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (i != arr.length) {
        System.out.print(arr[i] + ",");
      }
    }
    System.out.println("\r");
  }

  public static void sortString(String[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i].compareTo(arr[j]) > 0) {
          swap(arr, i, j);
        }
      }
    }
  }

  public static void swap(String[] arr, int i, int j) {
    String temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
