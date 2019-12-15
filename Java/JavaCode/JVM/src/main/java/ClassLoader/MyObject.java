package ClassLoader;

public class MyObject {
    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(obj.getClass().getClassLoader());// null
        MyObject myObj = new MyObject();
        System.out.println(myObj.getClass().getClassLoader());// null

    }
}
