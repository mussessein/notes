package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
Lambda�����Ĳ���
 */
public class Lambda_Object {
    static class GDX{
        String name;
        String job;
        int age;
        int salary;

        public GDX(String name, String job, int age, int salary) {
            this.name = name;
            this.job = job;
            this.age = age;
            this.salary = salary;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "����:"+this.getName()+
                    ",����:"+getJob()+
                    ",����:"+getAge()+
                    ",����"+getSalary();
        }
    }

    public static void main(String[] args) {
        List<GDX> persons = new ArrayList<GDX>()
        {
            {
                add(new GDX("J", "java",18, 20000));
                add(new GDX("P", "PHP", 40,10000));
                add(new GDX("S", "Scala", 20,30000));
                add(new GDX("C", "c", 70, 50000));
                add(new GDX("Cpp", "c++", 50,40000));
            }
        };
        // foreach����
        System.out.println("forEach����:");
        persons.forEach((p)-> System.out.println(
                p.toString()
        ));

        /*
         ����������:
         */
        // 1.�ȶ��弸��filters
        Predicate<GDX> salaryFilter = (p)->(p.getSalary()>100);
        Predicate<GDX> ageFilter =(p)->(p.getAge()>18);

        System.out.println("====================================");
        System.out.println("����֮��:");
        persons.stream()
                .filter(salaryFilter)
                .filter(ageFilter)
                .forEach((p)-> System.out.println(
                        p.toString()
                ));

        /*
        stream����
         */
        System.out.println("====================================");
        System.out.println("����Salary����֮��:");
        persons.stream()
                .sorted((p1,p2)->(p1.getSalary()-p2.getSalary()))
                .collect(Collectors.toList())
                .forEach((p)-> System.out.println(
                        p.toString()
                ));


        // min max
        System.out.println("====================================");
        System.out.println("�ҵ�������͵�:");

        GDX min=persons.stream()
                .min((p1,p2)->(p1.getSalary()-p2.getSalary()))
                .get();
        System.out.println(min.toString());


        // ��߹���
        System.out.println("====================================");
        System.out.println("�ҵ�������gao��:");
        OptionalInt max =persons.stream()
                        .mapToInt(p->p.getSalary())
                        .max();
        System.out.println(max);


        // �¹����ܺ�
        System.out.println("====================================");
        System.out.println("�¹����ܺ�:");
        int sum=persons.stream()
                .mapToInt(p->p.getSalary())
                .sum();
        System.out.println(sum);
    }

}
