package EL;

import java.util.Scanner;

/*
验证手机号 11位
1[0-9]{10}
第一位是1，后面十位任意
(\+86|0086)?\s*1[0-9]{10}
 */
public class demo_Phone {

    public static void main(String[] args){
        System.out.println("请输入一个手机号：");
        Scanner scanner=new Scanner(System.in);
        //输入的内容传递给line变量
        String line=scanner.nextLine();
        String regex="1[0-9]{10}";
        boolean flag=line.matches(regex);
        if (flag){
            System.out.println("是手机号");
        }else {
            System.out.println("不是手机号");
        }
    }
}
