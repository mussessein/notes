package Offer;

import java.util.ArrayList;

/**
 * 矩阵从外向里输出
 */
public class solution_17 {
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int n = matrix.length;
        int m = matrix[0].length;
        if (m == 0) {
            return arr;
        }
        if (n == 0) {
            return arr;
        }
        int lay = (Math.min(n, m)) - 1 / 2 + 1;
        for (int i = 0; i < lay; i++) {
            for (int k = i; k < m - i; k++) arr.add(matrix[i][k]);
            for (int k = i + 1; k < n - i; k++) arr.add(matrix[k][m - i - 1]);
            for (int k = m - i - 2; k >= i && (n - i - 1 != i); k--) arr.add(matrix[n - i - 1][k]);
            for (int k = n - i - 2; k > i && (m - 1 - i != i); k--) arr.add(matrix[k][i]);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] matrix =
                {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}
                };

        ArrayList<Integer> res = printMatrix(matrix);
        System.out.println(res);
    }
}
