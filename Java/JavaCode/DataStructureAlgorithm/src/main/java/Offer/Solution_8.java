package Offer;

/**
 * ÅÄÂ¥Ìİ£¬ì³²¨ÄÇÆõÊıÁĞ
 */
public class Solution_8 {
    public int JumpFloor(int target) {
        if (target < 3) {
            return target;
        }
        int a = 1, b = 2;
        int sum = 0;
        for (int i = 2; i < target; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }
}
