package EL;

import java.util.Scanner;

/*
��֤�ֻ��� 11λ
1[0-9]{10}
��һλ��1������ʮλ����
(\+86|0086)?\s*1[0-9]{10}
 */
public class demo_Phone {

    public static void main(String[] args){
        System.out.println("������һ���ֻ��ţ�");
        Scanner scanner=new Scanner(System.in);
        //��������ݴ��ݸ�line����
        String line=scanner.nextLine();
        String regex="1[0-9]{10}";
        boolean flag=line.matches(regex);
        if (flag){
            System.out.println("���ֻ���");
        }else {
            System.out.println("�����ֻ���");
        }
    }
}
