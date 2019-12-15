package Generics;

import java.util.Arrays;


/**
 * 泛型：即将参数类型当成形参传入,
 *          例如<T>，在调用此类的时候将T改写为指定类型，即将此类定义为指定类型
 * 通配符<?>，可以匹配类型。
 */

//泛型类
public class demo_Apple<T> {

    private T color;


    public demo_Apple(T color) {
        this.color = color;
    }
    //泛型方法，任意类型可调用
    public T getColor() {
        return color;
    }
    
    public static void main(String[] args) {
        //实例化Apple类，并指定参数类型String
        demo_Apple<String> a1 = new demo_Apple<String>("红色");
        System.out.println(a1.getColor());

        //实例化Apple类，并指定参数类型Integer
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
    //泛型方法,可以完成任意类型的数组内的交换
    public static <T> void swap_1(T[] arr,int a,int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

