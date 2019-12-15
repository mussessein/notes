package IO.Serializable;

/*
   ���л��������л��Ķ������ʵ�������ӿ���һ��Serializable / Externalizable

   ���л���1.��һ��java����д��IO����
         2.���������л�ͬһ������ֻ������һ�����У���ͬһ����ֻ�����л�һ��
             ������һ�����л�per����֮������޸Ĵ˶����name���ٴ����л��˶���
             �ٷ����л���name��û�иı䣡������

   �����л� �� 1.��IO���лָ�java����,
               2.����ָ���������ʱ�򣬱��밴д���˳��ָ�,�����׳�InvalidClassException�쳣
               3.������ù�������ʼ������

 */
import java.io.*;
public class demo_ObjectOutputStream {

    public static void main(String[] args){
        try(
                //����һ��ObjectOutputStream���������Ҫ�����ڽڵ�����
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("./Notes/src/main/java/IO/Serializable/object.txt"));
                //����һ��ObjectInputStream����������Ҫ�����ڽڵ�����
                ObjectInputStream ois=new ObjectInputStream(new FileInputStream("./Notes/src/main/java/IO/Serializable/object.txt"))
                )
        {
            //��person����д�������
            Person per1=new Person(1,"Zhang",19);
            oos.writeObject(per1);

            //���������ж�ȡjava���󣬲�ǿתΪPerson��
            Person per2=(Person)ois.readObject();
            //��person����д�������,�޷��õ�ID���ԣ���ΪID���Ա��ؼ���transient����
            System.out.println("ID:"+per2.getID()+"\n"+"���֣�"+per2.getName()+"\n"+"���䣺"+per2.getAge());
        }catch (Exception e){e.printStackTrace();}
    }
}
