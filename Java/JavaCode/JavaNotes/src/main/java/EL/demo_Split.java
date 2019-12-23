package EL;
/*
String[] split(String regex);
按当前正则表达式拆分字符串，返回拆分后的每段内容

 */
public class demo_Split {

    public static void main(String[] args){
        String str="asD123aFd434fHsf145";
        //切掉所有数字
        String regex_1="[0-9]+";
        String[] array_1=str.split(regex_1);
        for (String s:array_1){
            System.out.println(s);
        }
        //切掉字母
        String regex_2="[a-zA-Z]+";
        String[] array_2=str.split(regex_2);
        for (String s:array_2){
            System.out.println(s);
        }
    }
}
