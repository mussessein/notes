package Atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        AtomicIntegerArray atomicnums = new AtomicIntegerArray(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(atomicnums.get(i));
        }
        int ori = atomicnums.getAndSet(0, 10);
        System.out.println("original(原值)：" + ori + "=====i(结果):" + atomicnums.get(0));
        ori = atomicnums.getAndIncrement(0);
        System.out.println("original(原值)：" + ori + "=====i(结果):" + atomicnums.get(0));
        ori = atomicnums.getAndAdd(0, 5);
        System.out.println("original(原值)：" + ori + "=====i(结果):" + atomicnums.get(0));
    }
}
