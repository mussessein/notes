package EL;
/*
String[] split(String regex);
����ǰ������ʽ����ַ��������ز�ֺ��ÿ������

 */
public class demo_Split {

    public static void main(String[] args){
        String str="asD123aFd434fHsf145";
        //�е���������
        String regex_1="[0-9]+";
        String[] array_1=str.split(regex_1);
        for (String s:array_1){
            System.out.println(s);
        }
        //�е���ĸ
        String regex_2="[a-zA-Z]+";
        String[] array_2=str.split(regex_2);
        for (String s:array_2){
            System.out.println(s);
        }
    }
}
