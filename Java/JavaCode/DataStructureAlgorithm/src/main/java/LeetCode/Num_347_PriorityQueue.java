package LeetCode;

import java.util.*;

/*
给定一个非空的整数数组，返回其中出现频率前 k 高的元素
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
要求算法复杂度低于O（nlogn）
 */
public class Num_347_PriorityQueue {
    /**
     * nums数组元素作为key，频次作为value
     * Java自身的优先队列是最小堆。
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a,b)->map.get(a)-map.get(b)
        );
        for (int key:map.keySet()){
            if (pq.size()<k){
                pq.add(key);
            }
            else if (map.get(key)>map.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }
        LinkedList<Integer> list = new LinkedList<Integer>();
        while (!pq.isEmpty()){
            list.add(pq.remove());
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,3,3,3,4,4,6,6,6,6,6,6};
        List<Integer> list= topKFrequent(nums,2);
        System.out.println(list);
    }
}
