package Offer.Array;

/**
 * 有序二维数组的查找
 * 每列每行升序
 */
public class DimArrayFind {
    // 从左下角开始找
    public boolean find1(int target, int[][] array) {
        int row = array.length - 1;
        int col = array[0].length - 1;
        if (row <= 0 || col <= 0)
            return false;
        int i = row, j = 0;
        while (i >= 0 && j <= col) {
            if (array[i][j] == target) {
                return true;
            } else if (target > array[i][j]) {
                j++;
                continue;
            } else if (target < array[i][j]) {
                i--;
                continue;
            }
        }
        return false;
    }

    public boolean find2(int target, int[][] array) {
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
}
