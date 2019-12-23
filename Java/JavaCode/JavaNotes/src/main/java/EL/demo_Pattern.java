package EL;

import java.util.regex.*;
/**
 * ������ʽ��ģ������
 * replace�������滻�ַ���
 */
public class demo_Pattern {

    public static void main(String[] args) {
        String str = "xxxxxxxxxxxxx,xxxxx,xxx13500000001" + "java��15844443333" + "����������xxxxxx����ϵ15908761234";
        //����Pattern���󣬲���������һ��Matcher����
        //����������ʽ����ʾץȡ13x��15x�ֶε��ֻ��ţ�
        Matcher m1 = Pattern.compile("((13\\d)|(15\\d))\\d{8}").matcher(str);

        //�������ֿ���д��compile����������������ʽ����Ϊģʽ��
        Pattern p1 = Pattern.compile("((13\\d)|(15\\d))\\d{8}");//������ʽ
        Matcher m2 = p1.matcher(str);//Ŀ���ַ���

        while (m1.find()) {
            System.out.println(m1.group());
        }
        System.out.println("-------------------------------");
        while (m2.find()) {
            System.out.println(m2.group());
        }


        //�滻ָ������
        String[] msg={
            "Java has regular expression in 1.4",
            "regular expression now expressing in Java",
            "Java represses oracular expressions"
        };
        //�ҵ�������"re"��ͷ�ĵ��ʣ��ں���ȫ���滻�ɹ���������
        Pattern p2 = Pattern.compile("re\\w*");
        Matcher m3 = null;
        for (int i = 0; i < msg.length; i++) {
            if (m3 == null) {
                m3 = p2.matcher(msg[i]);
            } else {
                m3.reset(msg[i]);
            }
            System.out.println(m3.replaceAll("����:D"));
        }

    }
}