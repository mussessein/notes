package text;

public class ClassLoad {
    public static void main(String[] args) {
        Son son = new Son("son����");
        son.speak();
    }

}
class father {

    private String name;
    A a = new A();
    static {
        System.out.println("����ľ�̬�����");
    }

    {
        System.out.println("����ķǾ�̬�����");
    }

    father() {
        System.out.println("������޲ι��캯��");
    }

    father(String name) {
        this.name = name;
        System.out.println("������вι��캯��" + this.name);
    }


    public void speak() {
        System.out.println("����ķ���");
    }

}

class Son extends father {
    private String name;
    B b = new B();
    static {
        System.out.println("����ľ�̬�����");
    }

    {
        System.out.println("����ķǾ�̬�����");
    }

    Son() {
        System.out.println("������޲ι��캯��");
    }

    Son(String name) {
        this.name = name;
        System.out.println("������вι��캯��" + this.name);
    }

    @Override
    public void speak() {
        System.out.println("����Override�˸���ķ���");
    }
}
class A{
    A(){
        System.out.println("�����˸��������A");
    }
}
class B{
    B(){
        System.out.println("���������������B");
    }
}
