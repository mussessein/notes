package bean;

public class Bean3Factory {

    public Bean3 createBean3(){
        return new Bean3();
    }

    //此工厂的实例也会被IOC自动创建，因为不是静态方法，需要实例才能创建Bean3的实例
    public Bean3Factory(){
    System.out.println("bean3工厂实例被创建了");
    }
}
