package decorator;

/**   (װ�����ģʽ) ���������������ǿ���ȼ̳з�ʽ�����
 *              Composite����ϣ��Ǽ̳У�
 * ���ʵ�ֵ������ڼ̳е�һ�ָ���ģʽ��������
 */
class Animal {
    private void beat() {

        System.out.println("����");
    }

    public void breath() {
        beat();
        System.out.println("����");
    }
}

class Bird {
    //��Animal��ֱ�� ��� ��Bird����~��
    private Animal a;

    public Bird(Animal a) {

        this.a = a;
    }

    //ֱ��ʹ��Animal�ṩ��breath()��������
    public void breath() {

        a.breath();
    }

    public void fly() {

        System.out.println("��");
    }
}

public class Composite {
    public static void main(String[] args) {
        //��ʾ��������ϵĶ��󣡣�
        Animal a1 = new Animal();
        Bird b1 = new Bird(a1);    //Bird b1 = new Bird(new Animal());
        //ʵ���������ڡ����ࡱ�ķ���
        b1.breath();
        b1.fly();
    }
    
}