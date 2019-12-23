package EL;

import java.util.regex.*;
/**
 * 正则表达式，模拟爬虫
 * replace方法，替换字符串
 */
public class demo_Pattern {

    public static void main(String[] args) {
        String str = "xxxxxxxxxxxxx,xxxxx,xxx13500000001" + "java，15844443333" + "出租出租出租xxxxxx，联系15908761234";
        //创建Pattern对象，并用它建立一个Matcher对象，
        //下面正则表达式，表示抓取13x和15x字段的手机号；
        Matcher m1 = Pattern.compile("((13\\d)|(15\\d))\\d{8}").matcher(str);

        //上面语句分开来写：compile：将给定的正则表达式编译为模式。
        Pattern p1 = Pattern.compile("((13\\d)|(15\\d))\\d{8}");//正则表达式
        Matcher m2 = p1.matcher(str);//目标字符串

        while (m1.find()) {
            System.out.println(m1.group());
        }
        System.out.println("-------------------------------");
        while (m2.find()) {
            System.out.println(m2.group());
        }


        //替换指定单词
        String[] msg={
            "Java has regular expression in 1.4",
            "regular expression now expressing in Java",
            "Java represses oracular expressions"
        };
        //找到所有以"re"开头的单词，在后面全部替换成哈哈哈哈哈
        Pattern p2 = Pattern.compile("re\\w*");
        Matcher m3 = null;
        for (int i = 0; i < msg.length; i++) {
            if (m3 == null) {
                m3 = p2.matcher(msg[i]);
            } else {
                m3.reset(msg[i]);
            }
            System.out.println(m3.replaceAll("哈哈:D"));
        }

    }
}