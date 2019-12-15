package LeetCode;

public class Num_11 {
    public int maxArea(int[] height) {
        int k = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j <height.length ; j++) {
                int min=height[i]>height[j]?height[j]:height[i];
                k = Math.max(k,(j-i)*min);
            }
        }
        return k;
    }
}
