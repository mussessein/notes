package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
Lambda与对象的操作
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
            return "名字:"+this.getName()+
                    ",工作:"+getJob()+
                    ",年龄:"+getAge()+
                    ",工资"+getSalary();
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
        // foreach遍历
        System.out.println("forEach遍历:");
        persons.forEach((p)-> System.out.println(
                p.toString()
        ));

        /*
         过滤器方法:
         */
        // 1.先定义几个filters
        Predicate<GDX> salaryFilter = (p)->(p.getSalary()>100);
        Predicate<GDX> ageFilter =(p)->(p.getAge()>18);

        System.out.println("====================================");
        System.out.println("过滤之后:");
        persons.stream()
                .filter(salaryFilter)
                .filter(ageFilter)
                .forEach((p)-> System.out.println(
                        p.toString()
                ));

        /*
        stream排序
         */
        System.out.println("====================================");
        System.out.println("根据Salary排序之后:");
        persons.stream()
                .sorted((p1,p2)->(p1.getSalary()-p2.getSalary()))
                .collect(Collectors.toList())
                .forEach((p)-> System.out.println(
                        p.toString()
                ));


        // min max
        System.out.println("====================================");
        System.out.println("找到工资最低的:");

        GDX min=persons.stream()
                .min((p1,p2)->(p1.getSalary()-p2.getSalary()))
                .get();
        System.out.println(min.toString());


        // 最高工资
        System.out.println("====================================");
        System.out.println("找到工资最gao的:");
        OptionalInt max =persons.stream()
                        .mapToInt(p->p.getSalary())
                        .max();
        System.out.println(max);


        // 月工资总和
        System.out.println("====================================");
        System.out.println("月工资总和:");
        int sum=persons.stream()
                .mapToInt(p->p.getSalary())
                .sum();
        System.out.println(sum);
    }

}
