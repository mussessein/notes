package text;
/*
数组
*/
import java.util.Arrays;

public class demo_Array {    
    public static void main(String[] args) {
    
    //int[] arr=new int[4];
    int[] arr={5,6,7,10};
    int[] arr1=new int[]{7,10,8};
    int[] arr2=new int[]{12,30,50,3,6,80};
    int[] arr3=new int[]{20,30,40,50,60};
    //直接打印数组
    System.out.println(Arrays.toString(arr1));

    //遍历数组(函数)
    getStr(arr);

    //寻找数组最大值（函数）
    getMax(arr1);

    //冒泡排序 顺序（函数）
    getORDER(arr2);
    System.out.println(Arrays.toString(arr2));
    
    //数组查找（函数）
    //一 遍历法
    System.out.println(getIndex(arr2,30));

    //二 折半查找法 （二分法）前提有序数列
    int index=getIndex_2(arr3,50);
    System.out.println("查找数字的位置："+index);
    }



    //遍历数组(函数)
    public static void getStr(int[] arr)
    {
        for(int x=0;x<arr.length;x++)
        {
            System.out.println("arr[]="+arr[x]+";");
        }
        return;
    }

    
    //获取数组最大值
    public static void getMax(int[] arr) {
        int max=arr[0];
        for(int x=1;x<arr.length;x++)
        {
            if(arr[x]>max)
            max=arr[x];
        }
        System.out.println(max);
    }


    //冒泡排序 顺序
    public static void getORDER(int[] arr) {
        for(int x=0;x<arr.length;x++)
        {
            for(int y=x+1;y<arr.length;y++)
            {
                if(arr[x]>arr[y])
                {
                    int temp=arr[x];
                    arr[x]=arr[y];
                    arr[y]=temp;
                }
            }
        }
    }

    //遍历查找
    public static int getIndex(int[] arr,int key) {
        for(int x=0;x<arr.length;x++)
        {
            if(arr[x]==key)
            return arr[x];    //返回找到的值
        }
        return -1;
    }

    //折半查找
    public static int getIndex_2(int[] arr,int key) {
        int max,min,mid;
        max=arr.length-1;
        min=0;
        mid=(max+min)/2;
        while(arr[mid]!=key)
        {
            if(key>arr[mid])
            {
                min=mid+1;
                mid=(max+min)/2;
            }
            else
            {
                max=mid-1;
                mid=(max+min)/2;
            }
            if(max<min)     //表示没找到
            return -1;
        }                   //循环结束表示找到,即 arr[mid]==key
        return mid;     //返回脚标
    }
}