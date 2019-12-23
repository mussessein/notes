package Container;

import java.util.*;

/**
 * Collection ���Ͽ�ܵ�һ�����ӿ�
 * Collecrions���߲���List����
 */
class Set_notes {
    public static void main(String[] args) {
        Collection<String> c1 = new ArrayList<String>();
        Collection<String> c2 = new ArrayList<String>();
        show_1(c1);
        show_2(c2);
        System.out.println("c1 =" + c1);
        System.out.println("c2 =" + c2);
        //ɾ��Ԫ��
        c1.remove("abc3");
        //removeAll
        show_1(c1);
        show_2(c2);
        c1.removeAll(c2); //������������ͬ��Ԫ�أ���c1��ɾ����
        System.out.println(c1);
        //retainAll
        show_1(c1);
        show_2(c2);
        c1.retainAll(c2); //����������������ͬ��Ԫ�ء�
        System.out.println(c1);


        //ʹ��Collections������������List����
        ArrayList<Integer> AL1 = new ArrayList<>();
        AL1.add(2);
        AL1.add(90);
        AL1.add(70);
        AL1.add(15);
        System.out.println(AL1);
        //����
        Collections.reverse(AL1);
        //��Ȼ����
        Collections.sort(AL1);
        //������Ԫ��
        System.out.println(Collections.max(AL1));//min
        //�滻
        Collections.replaceAll(AL1, 70, 80);
        //ͳ��ĳԪ�س��ִ���
        System.out.println(Collections.frequency(AL1, 15));
        //����ĳԪ��λ��
        System.out.println(AL1);
        System.out.println(AL1.indexOf(15));
    }


    private static void show_2(Collection<String> c2) {
        c2.clear();
        c2.add("abc2");
        c2.add("abc5");
        c2.add("abc6");
    }

    private static void show_1(Collection<String> c1) {
        c1.clear();
        c1.add("abc1");
        c1.add("abc2");
        c1.add("abc3");
        c1.add("abc4");
    }


}