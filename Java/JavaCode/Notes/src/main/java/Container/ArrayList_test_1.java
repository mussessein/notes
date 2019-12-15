package Container;

import java.util.*;

/**
 * **********      1.ArrayList����Ӷ���Ԫ��
 * 2.����һ��������ȥ��ArrayList���ظ�Ԫ��getSingleElement������
 * 3.ArrayList���ж϶����Ƿ���ͬ��ֻͨ��equals�����жϹ�ϣ��
 * ��ͬ������
 * <p>
 * ���ֱ������Ϸ�ʽ��forEach///for///Iterator
 */
class ArrayList_test_1 {

    public static void main(String[] args) {

        ArrayList<Person_1> a1 = new ArrayList<Person_1>();
        a1.add(new Person_1("Num_1", 21));
        a1.add(new Person_1("Num_1", 21));
        a1.add(new Person_1("Num_1", 21));
        a1.add(new Person_1("Num_1", 21));
        a1.add(new Person_1("Num_2", 22));
        a1.add(new Person_1("Num_3", 23));
        a1.add(new Person_1("Num_4", 24));
        
        /*java8������Predicate�������ϣ�����ɾ������������Ԫ��
        a1.removeIf(ele -> ((String) ele).length() < 20); ɾ������С��20��Ԫ��
        a1.remove(new Person_1()); ֱ��ɾ����һ������
        ǰ�ᣬ��������дequals����,�˷�������ѵ�һ��Ԫ�ؽ���equals�Ƚϣ�����trueʱ��ɾ����һ��Ԫ�أ���û������
        */
        a1.removeIf(ele -> ele.getAge() < 22);//()�ڵĴ����ʾ�Ѿ�������a1��ȫ��Ԫ�أ����ж�����

        //��ǿforѭ��
        for (Person_1 p : a1) {
            System.out.println(p);
        }
        //forEach��������,Lambda
        a1.forEach(p -> System.out.println(p.getName() + "--" + p.getAge()));
        //a1.forEach(System.out::println); �����ڱ�������

        //Iterato����ɾ��Ԫ�أ���ǰ��������԰�����ɾ����Ԫ�أ��´α�����Ԫ�ر�ɾ��
        for (Iterator<Person_1> it = a1.iterator(); it.hasNext(); ) {
            Person_1 p = (Person_1) it.next();
            if (p.getAge() == 22)
                it.remove();
            System.out.println(p.getName() + "--" + p.getAge());
        }
        System.out.println("\r");
        for (Iterator<Person_1> it = a1.iterator(); it.hasNext(); ) {
            Person_1 p = (Person_1) it.next();
            System.out.println(p.getName() + "--" + p.getAge());
        }
        /* for�������ϣ�������ɾ��Ԫ��
        for (Person_1 p : a1) {
            System.out.println(p);
        }
        */

        //����һ��������ȥ��ArrayList���ظ�Ԫ�أ�
        ArrayList<String> a2 = new ArrayList<>();
        a2.add("abc1");
        a2.add("abc1");
        a2.add("abc2");
        a2.add("abc1");
        a2.add("abc3");
        System.out.println("ȥ���ظ�֮ǰ�� " + a2);
        a2 = getSingleElement(a2);
        System.out.println("ȥ���ظ�֮�� " + a2);
        //�����ó�����Ԫ��
        System.out.println("������Ԫ��Ϊ �� " + a2.get(2));

        //����forѭ����������Ԫ��
        /*   ������for������ɾ��Ԫ�� �׳�java.util.ConcurrentModificationException
        System.out.println("for�������� �� ");
        Iterator<String> it = a2.iterator();
        for (String s : a2) {
            if (s.equals("abc3"))  
                a2.remove(s);
            System.out.println(it.next());
        }*/
        for (Object o : a2) {
            //String s = (String) o;
            System.out.println(o);
        }
    }


    public static ArrayList<String> getSingleElement(ArrayList<String> a2) {
        //�ȶ���һ����������
        ArrayList<String> temp = new ArrayList<String>();
        for (Iterator<String> it = a2.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (!temp.contains(obj)) {
                temp.add((String) obj);
            }
        }
        return temp;
    }

}

