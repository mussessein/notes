package IO.Serializable;

/*
   序列化对象：序列化的对象必须实现两个接口其一：Serializable / Externalizable

   序列化：1.将一个java对象，写入IO流中
         2.如果多次序列化同一个对象，只会生成一次序列，对同一对象，只会序列化一次
             即：第一次序列化per对象之后，如果修改此对象的name，再次序列化此对象
             再反序列化，name并没有改变！！！！

   反序列化 ： 1.从IO流中恢复java对象,
               2.如果恢复多个对象的时候，必须按写入的顺序恢复,否则抛出InvalidClassException异常
               3.不会调用构造器初始化对象

 */
import java.io.*;
public class demo_ObjectOutputStream {

    public static void main(String[] args){
        try(
                //创建一个ObjectOutputStream输出流，需要建立在节点流上
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("./Notes/src/main/java/IO/Serializable/object.txt"));
                //创建一个ObjectInputStream输入流，需要建立在节点流上
                ObjectInputStream ois=new ObjectInputStream(new FileInputStream("./Notes/src/main/java/IO/Serializable/object.txt"))
                )
        {
            //将person对象写入输出流
            Person per1=new Person(1,"Zhang",19);
            oos.writeObject(per1);

            //从输入流中读取java对象，并强转为Person类
            Person per2=(Person)ois.readObject();
            //将person对象写入输出流,无法得到ID属性，因为ID属性被关键字transient修饰
            System.out.println("ID:"+per2.getID()+"\n"+"名字："+per2.getName()+"\n"+"年龄："+per2.getAge());
        }catch (Exception e){e.printStackTrace();}
    }
}
