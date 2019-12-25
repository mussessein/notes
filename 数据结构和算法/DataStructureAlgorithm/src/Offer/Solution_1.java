package Offer;

public class Solution_1 {
    public boolean Find(int target, int[][] array) {
/*      int len_1=array.length;
        int len_2=array[0].length;
        for (int i = 0; i < len_1; i++) {
            for (int j = 0; j < len_2; j++) {
                if (array[i][j] == target){
                    return true;
                }
            }
        }
        return false;
*/
        int row = array.length - 1;
        int col = array[0].length - 1;
        if (row <= 0 || col <= 0)
            return false;
        int i = 0;
        while (i <= row) {
            if (array[i][0] <= target && target <= array[i][col]) {
                for (int j = 0; j <= col; j++) {
                    if (target == array[i][j]) {
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }

    public boolean Find_2(int target, int[][] array) {

        int len_1 = array.length;
        int len_2 = array[0].length;
        if (len_2 == 0)
            return false;
        for (int i = 0; i < len_1; i++) {
            if (array[i][0] <= target && target <= array[i][len_2 - 1]) {
                int low = 0;
                int high = len_2 - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (array[i][mid] < target)
                        low = mid + 1;
                    else if (array[i][mid] > target)
                        high = mid - 1;
                    else
                        return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {

        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 14}, {6, 8, 11, 15}};
        int[][] array1 = {{}};
        boolean flag = new Solution_1().Find(7, array);

        System.out.println(flag);

    }
}
