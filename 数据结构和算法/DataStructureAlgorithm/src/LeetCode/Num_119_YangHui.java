package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 直接输出第k行
 */
public class Num_119_YangHui {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (rowIndex == 0) {
            return new ArrayList<>();
        }
        if (rowIndex >= 1) {
            List<Integer> row_1 = new ArrayList();
            row_1.add(1);
            triangle.add(row_1);
        }
        for (int i = 1; i < rowIndex; i++) {
            List<Integer> row = new ArrayList();
            List<Integer> preRow = triangle.get(i - 1);
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(preRow.get(j - 1) + preRow.get(j));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle.get(rowIndex);
    }
}
