package bean;
/*
2.静态工厂的实例化方式
 */
public class Bean2Factory {

    public static Bean2 ceateBean2(){

        return new Bean2();
    }
    //此对象的实例不会被创建，因为静态方法，只需要类加载就可以
    public Bean2Factory(){
    System.out.println("Bean2工厂实例被创建了");
    }

}
