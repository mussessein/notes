package Container;
public class Person_1 {
    
    String name;
    int age;
    Person_1(){}

    Person_1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {

        return age;
    }

    public String getName() {
        return name;
    }

    //��дtoString���������������ӡ��ʱ���Դ˷�ʽ��ӡ��
    //�������д�����Թ�ϣ��Ϊ�����ļ����У�����ӡ��ϣֵ��
    public String toString() {
        return "[name:" + name + "," + "age:" + age + "]";
    }
    /*
    public boolean equals(Object obj) {
        return true;
    }*/

}