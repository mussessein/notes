package DataStructure.Hash;

import java.util.HashMap;
import java.util.HashSet;

class Student {
    int age;
    String name;
    int grade;

    Student(String name, int age, int grade) {
        this.age = age;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj != null && getClass() == obj.getClass()) {
            Student another = (Student) obj;
            return this.grade == another.grade &&
                    this.age == another.age &&
                    this.name.toLowerCase().equals(another.name.toLowerCase());
        } else
            return false;
    }

    @Override
    public int hashCode() {
        int B = 31;
        int hash = 0;
        hash = hash * B + age;
        hash = hash * B + grade;
        hash = hash * B + name.hashCode();
        return hash;
    }
}

public class HashTest {


    public static void main(String[] args) {
        Integer a = 42;
        System.out.println(a.hashCode());
        String b = "areyouok";
        System.out.println(b.hashCode());

        Student stu1 = new Student("Ly", 17, 3);
        Student stu2 = new Student("Ly", 17, 3);
        System.out.println(stu1.hashCode());
        System.out.println(stu2.hashCode());

        /*
        1.经由equals和哈希值判断，是同一个对象，只存储一次
        2.如果重写了equals不重写哈希值，会存储两次，当成不同对象对待
         */
        HashSet<Student> set = new HashSet<>();
        set.add(stu1);
        set.add(stu2);
        System.out.println(set.size());

        HashMap<Student, Integer> scores = new HashMap();
        scores.put(stu1, 100);
        scores.put(stu2, 100);
        System.out.println(scores.size());
    }
}
