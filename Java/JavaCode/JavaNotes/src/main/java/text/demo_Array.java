package text;
/*
����
*/
import java.util.Arrays;

public class demo_Array {    
    public static void main(String[] args) {
    
    //int[] arr=new int[4];
    int[] arr={5,6,7,10};
    int[] arr1=new int[]{7,10,8};
    int[] arr2=new int[]{12,30,50,3,6,80};
    int[] arr3=new int[]{20,30,40,50,60};
    //ֱ�Ӵ�ӡ����
    System.out.println(Arrays.toString(arr1));

    //��������(����)
    getStr(arr);

    //Ѱ���������ֵ��������
    getMax(arr1);

    //ð������ ˳�򣨺�����
    getORDER(arr2);
    System.out.println(Arrays.toString(arr2));
    
    //������ң�������
    //һ ������
    System.out.println(getIndex(arr2,30));

    //�� �۰���ҷ� �����ַ���ǰ����������
    int index=getIndex_2(arr3,50);
    System.out.println("�������ֵ�λ�ã�"+index);
    }



    //��������(����)
    public static void getStr(int[] arr)
    {
        for(int x=0;x<arr.length;x++)
        {
            System.out.println("arr[]="+arr[x]+";");
        }
        return;
    }

    
    //��ȡ�������ֵ
    public static void getMax(int[] arr) {
        int max=arr[0];
        for(int x=1;x<arr.length;x++)
        {
            if(arr[x]>max)
            max=arr[x];
        }
        System.out.println(max);
    }


    //ð������ ˳��
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

    //��������
    public static int getIndex(int[] arr,int key) {
        for(int x=0;x<arr.length;x++)
        {
            if(arr[x]==key)
            return arr[x];    //�����ҵ���ֵ
        }
        return -1;
    }

    //�۰����
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
            if(max<min)     //��ʾû�ҵ�
            return -1;
        }                   //ѭ��������ʾ�ҵ�,�� arr[mid]==key
        return mid;     //���ؽű�
    }
}