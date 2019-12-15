package text;

/**
 * ��̬��ͬһ�����͵Ķ��ʵ������ִ��ͬһ��������ʱ��
 * ���ֳ����ֵ���Ϊ�������ͽж�̬��
 * 
 * Ϊʲô���ж�̬��
 * java��ִ�з���ʱ��
 * ������ִ���Ƕ�̬�󶨵ģ���������ִ�иñ���ʵ����ָ�����ķ���
 * 
 * ���������ͣ�
 * 1.����ʱ���ͣ������ñ���ʱָ�������͡�
 *              ��java����ı���׶Σ�java������ֻ�ϱ���ʱ���͡�
 * 2.����ʱ���ͣ���ʵ�����ͣ����ñ���ʵ�������õĶ��������
 * 
 */

 //��̬���� 1
class Bird {
    public void fly() {
        System.out.println("�������Ϸ�");
    }
}

class Sparrow extends Bird {

}

class Ostrich extends Bird {
    public void fly() {
        System.out.println("�����ڵ�����");
    }
}

//��̬���� 2
class Shape {
    public void draw() {
        System.out.println("����һ��ͼ��");
    }
}

class Rectangle extends Shape {
    public void info() {
        System.out.println("����һ������");
    }

    public void draw() {
        System.out.println("����һ������");
    }
}

class Circle extends Shape {
    public void draw() {
        System.out.println("��Բ");
    }
}


public class demo_Duotai {

    public static void main(String[] args) {

        //����ת��:����������ֱ�Ӹ�ֵ���������
        Bird b1 = new Sparrow();
        Bird b2 = new Ostrich();
        b1.fly(); //��̬�İ�Sparrow��fly����������Sparrowû��fly���Զ��ص����෽��
        b2.fly(); //��̬��Ostrich��fly����

        // s1��ʵ��������Rectangle�����Ǳ���û��ͨ��
        Shape s1 = new Rectangle();
        Shape s2 = new Circle();

        //s1�ı���������Shape��Shapeû��info���������Ա��벻��ͨ��
        //s1.info();

        s1.draw();//���е�ʱ�򣬷���ΪRectangle�������������е�ʱ�򿴵���ʵ�����ͣ�Rectangle��
    
        //����ǿ��ת����
        Rectangle r1 = (Rectangle) s1;
        //instanceofֻ���ڱ������;��м̳й�ϵ֮������жϣ�������뱨��
        System.out.println(r1 instanceof Rectangle);
        r1.info();
        //�Ǹ��ӹ�ϵ��ǿת���������ͨ��������ʱ����ClassCastException(����ת���쳣)
        Rectangle r2 = (Rectangle) s2;
        r2.draw();
    }
}



