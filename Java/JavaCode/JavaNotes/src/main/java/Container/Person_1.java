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

    //重写toString方法，会在输出打印的时候，以此方式打印，
    //如果不重写，在以哈希表为基础的集合中，将打印哈希值。
    public String toString() {
        return "[name:" + name + "," + "age:" + age + "]";
    }
    /*
    public boolean equals(Object obj) {
        return true;
    }*/

}