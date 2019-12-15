package text;

/**
 * ���岻�ɱ��࣡                           ����⣡��������������
 */
class Name {
    private String FirstName;
    private String LastName;

    public Name() {

    }

    public Name(String FirstName, String LastName) {
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public String getLastName() {
        return this.LastName;
    }
}

//���岻�ɱ���
class Person {
    private final Name name;

    //������룬ʹ��Person���µ����ֲ��ᱻ�ı�
    public Person(Name name) {
        // ���this.nameָ��final name
        this.name = new Name(name.getFirstName(), name.getLastName());
    }

    //��ȡPerson�����µ�Name
    public Name getName() {

        return new Name(name.getFirstName(), name.getLastName());
    }
}

public class demo_final {

    public static void main(String[] args) {
        Name n = new Name("��", "��");
        Person p = new Person(n);
        System.out.println(p.getName().getFirstName());
        n.setFirstName("��");
        //����n�����ֱ��ı���
        System.out.println(n.getFirstName());
        //���Ƕ���p�����ֲ��ᱻ�ı�
        System.out.println(p.getName().getFirstName());

    }
}