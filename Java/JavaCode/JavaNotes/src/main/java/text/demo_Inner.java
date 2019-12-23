package text;


/**�ڲ��ࣺ�Ǿ�̬�ڲ��࣬��̬�ڲ��࣬�ֲ��ڲ��࣬�����ڲ���
 * ��̬�ڲ���:1���ھ�̬�ڲ����У����Է����ⲿ��ľ�̬��Ա�����������Է���ʵ���������Ǿ�̬��
 *           2�����ⲿ���У����Է��ʾ�̬�ڲ�������г�Ա���������Ƿ��ʷ�ʽ��ͬ��������
 *           3������ֱ�Ӵ����ڲ�������ڲ������������ⲿ��
 */
class OuterClass {
    private String oc1 = "�ⲿ��ʵ������";
    private static String oc2 = "�ⲿ�ྲ̬��Ա����";

    //�Ǿ�̬�ڲ��ࣺ�����ⲿ���ʵ��
    public class InnerClass_1{

    }
    // ��̬�ڲ��࣬�����ⲿ���Ա���������ⲿ��ʵ���������ⲿ��
    static class InnerClass_2 {
        public String ic1 = "��̬�ڲ���ʵ������";
        private static String ic2 = "��̬�ڲ��ྲ̬��Ա����";

        // �ھ�̬�ڲ��࣬ͨ�������������ⲿ��ĳ�Ա
        public void accessOuterClass() {
            // System.out.println(oc1);
            // ���б������п���ͨ����
            System.out.println(oc2);
        }
        public InnerClass_2(){}
    }

    // �ⲿ����ʾ�̬�ڲ����Ա
    public void accessInnerClass() {
        // �����ڲ���ľ�̬��Ա����������������
        System.out.println(InnerClass_2.ic2);
        // �����ڲ���ķǾ�̬��Ա��������ʵ��������
        System.out.println(new InnerClass_2().ic1);

        new InnerClass_2().accessOuterClass();
        System.out.println(oc1);
    }
}

public class demo_Inner {

    public static void main(String[] args) {
        OuterClass o = new OuterClass();
        o.accessInnerClass();
        // ����ֱ�Ӵ����ڲ�����󣬾�̬�ڲ������������ⲿ��
        // new InnerClass_2().accessOuterClass();����
        OuterClass.InnerClass_2 in = new OuterClass.InnerClass_2();
        in.accessOuterClass();
    }
}