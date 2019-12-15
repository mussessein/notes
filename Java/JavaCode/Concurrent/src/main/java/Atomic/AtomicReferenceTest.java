package Atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    public static void main(String[] args) {

        AtomicReference<Student> ar = new AtomicReference<>();
        Student stu_1 = new Student("小明",17);
        Student stu_2 = new Student("大明",18);
        ar.set(stu_1);
        System.out.println(ar.get().toString());
        ar.compareAndSet(stu_1,stu_2);
        System.out.println(ar.get().toString());
    }
}
