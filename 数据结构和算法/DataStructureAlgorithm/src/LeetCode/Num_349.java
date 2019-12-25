package LeetCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/*
�����������飬��дһ���������������ǵĽ�����
����: nums1 = [1,2,2,1], nums2 = [2,2]
���: [2]  (�������е�ÿ��Ԫ��һ����Ψһ�ġ�)
 */
public class Num_349 {
    /*
    ��һ�� ����
     */
    public int[] intersection1(int[] nums1,int[] nums2){

        TreeSet<Integer> set1=new TreeSet<>();
        TreeSet<Integer> set2=new TreeSet<>();
        for (int num:nums1) {
            set1.add(num);
        }
        for (int num:nums2) {
            if (set1.contains(num)){
                set2.add(num);
            }
        }
        int i=0;
        int[] arr=new int[set2.size()];
        for (Iterator<Integer> it=set2.iterator();it.hasNext();){
            arr[i++]=it.next();
        }
        return arr;
    }
    /*
    �ڶ��� ����
     */
    public int[] intersection2(int[] nums1,int[] nums2){

        TreeSet<Integer> set1=new TreeSet<>();
        ArrayList<Integer> list=new ArrayList<>();
        for (int num:nums1) {
            set1.add(num);
        }
        for (int num:nums2) {
            if (set1.contains(num)){
                list.add(num);
                set1.remove(num);
            }
        }
        int[] arr=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i]=list.get(i);
        }
        return arr;
    }
    /*
    305�⣺ӳ�䷽��,ͨ��{key��value}��ֱ����valueͳ��Ƶ��
    ����: nums1 = [1,2,2,1], nums2 = [2,2]
    ���: [2,2]
     */
    public int[] intersection3(int[] nums1,int[] nums2) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            }
            else{
                map.put(num, map.get(num) + 1);
            }
        }
        ArrayList<Integer> list =new ArrayList();
        for (int num:nums2){
            if (map.containsKey(num)){
                list.add(num);
                map.put(num,map.get(num)-1);
                if (map.get(num)==0){
                    map.remove(num);
                }
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i]=list.get(i);
        }
        return arr;
    }



        public static void main(String[] args) {
        new Num_349().intersection1(new int[]{1, 2, 2, 1}, new int[]{2,2});
    }
}
