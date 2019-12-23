package Generics;

import java.util.Arrays;


/**
 * ���ͣ������������͵����βδ���,
 *          ����<T>���ڵ��ô����ʱ��T��дΪָ�����ͣ��������ඨ��Ϊָ������
 * ͨ���<?>������ƥ�����͡�
 */

//������
public class demo_Apple<T> {

    private T color;


    public demo_Apple(T color) {
        this.color = color;
    }
    //���ͷ������������Ϳɵ���
    public T getColor() {
        return color;
    }
    
    public static void main(String[] args) {
        //ʵ����Apple�࣬��ָ����������String
        demo_Apple<String> a1 = new demo_Apple<String>("��ɫ");
        System.out.println(a1.getColor());

        //ʵ����Apple�࣬��ָ����������Integer
        demo_Apple<Integer> a2 = new demo_Apple<Integer>(1);
        System.out.println(a2.getColor());

        Integer[] arr1 = { 1, 34, 5, 7};
        String[] arr2={
            "aaa",
            "bbb",
            "cccc",
            "ddddd"
        };
        swap_1(arr1, 1, 2);
        System.out.println(Arrays.toString(arr1));
        swap_1(arr2, 1, 2);
        System.out.println(Arrays.toString(arr2));


    }
    //���ͷ���,��������������͵������ڵĽ���
    public static <T> void swap_1(T[] arr,int a,int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

