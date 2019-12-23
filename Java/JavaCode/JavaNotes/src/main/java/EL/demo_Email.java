package EL;
/*
[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\.[a-zA-Z0-9]+)+
任意大小写数字+@+任意大小写数字+ （. +任意大小写出现一次或多次）整体可以出现一次或多次

 */
public class demo_Email {

    public static void main(String[] args){
        //java中\. 会报错，\\.相当于转译"\."
        String regex="[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9]+)+";
        String email="wadsda@eas.com.cn";
        boolean flag=email.matches(regex);
        if (flag){
            System.out.println("是邮箱");
        }else{
            System.out.println("不是邮箱");
        }
    }

}
