package Offer;

/**
 * 同8
 * 跳n阶台阶，每次可以跳1-n级
 */
public class Solution_9 {
    public int JumpFloorII(int target) {
        return 1 << target - 1;
    }
}
