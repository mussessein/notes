package Atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<Student> atomicStudent =
                AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");
        Student student = new Student("ming", 17);
        System.out.println(atomicStudent.getAndSet(student, 20));
        System.out.println(atomicStudent.get(student));
    }
}
