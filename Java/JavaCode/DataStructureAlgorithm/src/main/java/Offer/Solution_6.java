package Offer;

public class Solution_6 {
    public int minNumberInRotateArray(int[] array) {
        int min = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[min] > array[i])
                min = i;
        }
        return array[min];
    }


}
