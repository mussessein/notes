package Container;

import java.util.*;

/**
 * Iterator�ӿ�
 * ������������������
 * 1. next();     ���ص�������һ��Ԫ��
 * 2. hasnext();  �������Ԫ�أ��򷵻�true��û�У�����false
 * ListIterator�ӿ�
 * 1.��������ǰ�����Ĺ��ܣ�
 * 2.�������ڵ������������Ԫ�أ�Iteratorֻ��������������ֻ��ɾ��Ԫ�أ�
 */
class Iterator_demo {
    public static void main(String[] args) {

        Collection<String> c1 = new ArrayList<String>();
        c1.add("abc1");
        c1.add("abc2");
        c1.add("abc3");
        c1.add("abc4");
        c1.add("abc5");
        Collection<String> c2 = c1;
        Iterator<String> it1 = c1.iterator();

        //����Ԫ�أ��򷵻�true��
        while (it1.hasNext()) {
            System.out.print(it1.next() + ",");
        }
        System.out.println("\r");
        //���ַ������ڶ��ַ�����΢ʡһ�����ڴ档
        for (Iterator<String> it2 = c2.iterator(); it2.hasNext();) {
            System.out.print(it2.next() + ",");
        }
        System.out.println("\r");
        

        System.out.println("���������෽������:");
        //����ڵ������ĵ��������У�ʹ�ü��ϲ������ɾ��Ԫ�أ����׳��쳣������ʹ��Iterator������ListIterator
        List<String> L1 = new ArrayList<String>();
        L1.add("abc1");
        L1.add("abc2");
        L1.add("abc3");

        //����ʹ��Iterator������ListIterator,ʹ������ListIterator�µķ������ڵ��������У���List���в���
        for (ListIterator<String> it3 = L1.listIterator(); it3.hasNext();) {
            Object obj = it3.next();
            //�������"abc2"�������һ��"abc9"
            if (obj.equals("abc2")) {
                it3.add("abc9");//it3.set("abc8"); 
            }
        }
        System.out.println(L1);

        //�������
        for (ListIterator<String> it4 = L1.listIterator(); it4.hasPrevious();) {
            System.out.print(it4.previous()+",");
        }
    }
}