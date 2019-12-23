package IO.Serializable;
/*
序列化的对象必须实现两个接口其一：Serializable / Externalizable
如果将要序列化的对象内引用了别的对象，那么这两个对象都必须实现上面的接口
 */
public class Person implements java.io.Serializable{

    //为该类指定一个serialVersionUID,用于表示该java类的序列化版本，以此防止序列化出错
    public static final long serialVersionUID=512L;

    //被transient（临时的，短暂的）修饰的属性，不会进行序列化
    private transient int ID;
    private String name;
    private int age;

    public Person(int ID,String name,int age){
        System.out.println("构造器初始化对象");
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
