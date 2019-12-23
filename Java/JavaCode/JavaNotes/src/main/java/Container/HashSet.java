package Container;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Container:     Ԫ�ز����ظ�������
 * �ӿڷ�����Collection һ�¡�
 * 1. Set:
 * HashSet�������ݽṹ�ǹ�ϣ���洢Ԫ�ص�ʱ����HashCode������ȷ��λ��
 * ����֤����˳������
 * 2. LinkedHashSet:��������
 */
class Set_demo {

    public static void main(String[] args) {

        //Set:
        HashSet<String> hs1 = new HashSet<String>();
        hs1.add("abc1");
        hs1.add("abc2");
        hs1.add("abc3");
        hs1.add("abc4");

        Iterator<String> it1 = hs1.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }

        //���֣�һ����ֵ��Ҳ������HashSet��������Ϊ����Ȼ����һ���������ڹ�ϣ���е�ֵ������һ��
        HashSet<Person_1> hs2 = new HashSet<Person_1>();
        hs2.add(new Person_1("p_1", 21));
        hs2.add(new Person_1("p_1", 21));
        hs2.add(new Person_1("p_2", 22));
        hs2.add(new Person_1("p_3", 23));
        hs2.add(new Person_1("p_4", 24));
        for (Iterator<Person_1> it2 = hs2.iterator(); it2.hasNext(); ) {
            Person_1 p = (Person_1) it2.next();
            System.out.println(p.getName() + "--" + p.getAge());

        }
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(3);
        linkedHashSet.add(3);
        linkedHashSet.add(1);
        linkedHashSet.add(9);
        linkedHashSet.add(1);
        for (int val : linkedHashSet) {
            System.out.println(val);
        }
    }
}

