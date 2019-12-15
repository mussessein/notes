package IO.Serializable;
/*
���л��Ķ������ʵ�������ӿ���һ��Serializable / Externalizable
�����Ҫ���л��Ķ����������˱�Ķ�����ô���������󶼱���ʵ������Ľӿ�
 */
public class Person implements java.io.Serializable{

    //Ϊ����ָ��һ��serialVersionUID,���ڱ�ʾ��java������л��汾���Դ˷�ֹ���л�����
    public static final long serialVersionUID=512L;

    //��transient����ʱ�ģ����ݵģ����ε����ԣ�����������л�
    private transient int ID;
    private String name;
    private int age;

    public Person(int ID,String name,int age){
        System.out.println("��������ʼ������");
        this.ID=ID;
        this.name=name;
        this.age=age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
