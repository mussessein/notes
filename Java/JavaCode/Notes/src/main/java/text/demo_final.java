package text;

/**
 * 定义不可变类！                           不理解！！！！！！！！
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

//定义不可变类
class Person {
    private final Name name;

    //下面代码，使得Person类下的名字不会被改变
    public Person(Name name) {
        // 这个this.name指向final name
        this.name = new Name(name.getFirstName(), name.getLastName());
    }

    //获取Person对象下的Name
    public Name getName() {

        return new Name(name.getFirstName(), name.getLastName());
    }
}

public class demo_final {

    public static void main(String[] args) {
        Name n = new Name("冰", "李");
        Person p = new Person(n);
        System.out.println(p.getName().getFirstName());
        n.setFirstName("火");
        //对象n的名字被改变了
        System.out.println(n.getFirstName());
        //但是对象p的名字不会被改变
        System.out.println(p.getName().getFirstName());

    }
}