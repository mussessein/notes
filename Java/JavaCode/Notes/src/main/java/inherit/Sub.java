package inherit;

/**
 * ���๹����������Ҫ���뼴����������д�ķ���~~~��������������
 *                                                                 ��ʲô�����أ�����������
 */
class Base {
    public void test() {
        System.out.println("����������д�ķ���");
    }

    public Base() {
       test();
    }

}

public class Sub extends Base {
    private String name;

    public void test() {
        System.out.println("�Ѿ���д�ķ���" + "name����Ϊ��" + name);
    }

    public Sub() {
        System.out.println("Sub����");
        
    }

    public static void main(String[] args) {
        // ���д��룬��ָ���쳣���޷����
        Sub s1 = new Sub();
        // ���д���������
        s1.test();
    }
}
