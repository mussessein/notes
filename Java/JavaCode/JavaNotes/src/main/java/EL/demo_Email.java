package EL;
/*
[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\.[a-zA-Z0-9]+)+
�����Сд����+@+�����Сд����+ ��. +�����Сд����һ�λ��Σ�������Գ���һ�λ���

 */
public class demo_Email {

    public static void main(String[] args){
        //java��\. �ᱨ��\\.�൱��ת��"\."
        String regex="[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9]+)+";
        String email="wadsda@eas.com.cn";
        boolean flag=email.matches(regex);
        if (flag){
            System.out.println("������");
        }else{
            System.out.println("��������");
        }
    }

}
