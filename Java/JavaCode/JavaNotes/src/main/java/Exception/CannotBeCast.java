package Exception;

import java.util.ArrayList;
import java.util.List;
/*
* �������ͨ���������׳��쳣
* 12�У�b����ǿתΪa
* ����ǿת���࣬����
* ����ǿת���࣬�з���
*
* */
public class CannotBeCast {
    public static void main(String[] args){
        List<cat> cats=new ArrayList<cat>();
        cats.add(new cat());
        Animal b=new Animal();
        cat a =null;
        // ֻ�и�����������������new������ʱ��, �ſ����ڽ�����ǿ��ת��Ϊ�������
        if (b instanceof Animal)a=(cat)b;  //(����ʱ�쳣)Animal cannot be cast to class Exception.cat
        if (a!=null)cats.add(a);
        System.out.println(cats.size()+"cats");
    }
}
class Animal{}
class cat extends Animal{}