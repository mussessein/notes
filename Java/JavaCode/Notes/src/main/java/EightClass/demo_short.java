package EightClass;

import java.lang.reflect.Field;

public class demo_short {

    public static void main(String[] args) {
        short s1 = 2;
        //s1=s1+1;//�������ܽ�s1ǿתΪint��1Ϊint���ͣ�

        //����Ĳ��ᱨ������ʽ����ת�����൱��s1=(short)(s1+1)
        s1 += 1;

        // float f =1.1 ����(1.1����������double���ͣ���������ת��)
        float f1 = (float) 1.1;
        float f2 = 1.1f;

    }
}
