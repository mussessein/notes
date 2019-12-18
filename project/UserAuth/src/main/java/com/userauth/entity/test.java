package com.userauth.entity;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@ToString
public class test {

    Boolean flag;
    String name;




    public boolean testFilter1() throws IllegalAccessException {
        Class stuCla = (Class) this.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(this);// 得到此属性的值
            if(val!=null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean testFilter(){
        if (this.flag==null&&this.name==null){
            return false;
        }
        else return true;
    }
    public static void main(String[] args) {
        test t = new test();
        ArrayList<com.userauth.entity.test> tests = new ArrayList<test>();
        tests.add(t);
        Stream<com.userauth.entity.test> testStream = tests.stream().filter(x ->x.testFilter());
        List<com.userauth.entity.test> collect = testStream.collect(Collectors.toList());
        for(test x : collect){
            System.out.println(x);
        }
    }
}
