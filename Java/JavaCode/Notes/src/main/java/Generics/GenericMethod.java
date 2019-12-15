package Generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
* 泛型方法：
* 泛型方法中的通配符；<? extends Object> 表示此数据类型是继承与Object
*                     <? super String > 表示此类型是String的父类
*
* 下面代码 实现List_1复制到List_2中
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
