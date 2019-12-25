package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Êä³öÑî»ÔÈı½Ç
 */
public class Num_118_YangHui {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows >= 1) {
            List<Integer> row_1 = new ArrayList();
            row_1.add(1);
            triangle.add(row_1);
        }
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList();
            List<Integer> preRow = triangle.get(i - 1);
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(preRow.get(j - 1) + preRow.get(j));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }

    public static void main(String[] args) {
        int numRows = 3;
        List<List<Integer>> trangle = generate(3);
        System.out.println(trangle);
    }
}
