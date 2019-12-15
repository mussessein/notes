package Generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
* ���ͷ�����
* ���ͷ����е�ͨ�����<? extends Object> ��ʾ�����������Ǽ̳���Object
*                     <? super String > ��ʾ��������String�ĸ���
*
* ������� ʵ��List_1���Ƶ�List_2��
* */
public class GenericMethod {
    public static <T> void test(Collection<? extends T> List_1,Collection<T> List_2){
        for(T ele:List_1){
            List_2.add(ele);
        }
    }
    public static void main(String[] args){
        List<Object> ao=new ArrayList<>();
        List<String> as=new ArrayList<>();
        as.add("asd");
        test(as,ao);
        System.out.println(ao);
    }
}
