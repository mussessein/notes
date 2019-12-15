package text;
/**
 * �����ࣺ�����ʾ���һ��ģ�壡������������������࣡��
 * 1. ���󷽷�ֻ������ ��û�о���ʵ�֡����󷽷������ڳ�������
 * 2. �����಻���Ա�ʵ����������û������ 
 * 3. ���еĳ�������������า�����г��󷽷��󣬸��������ʵ���� ���������໹�ǳ�����
 * 4. ����ؼ��ֲ����Ժ�private/static/final
 * 5. ������һ����һ������
 */

/*������ʵ����
����Ա��
        ���ԣ����������ţ�нˮ
        ��Ϊ������
����
        ���ԣ����������ţ�нˮ��    ����˽�����ԣ�
        ��Ϊ������
�����ڹ�Ա
��ͬ������Ϊ������
*/
abstract class Employee {

	//���г��󷽷����࣬�����ǳ����࣬���󷽷���û�з����壡
    public abstract void work();
    public String name;
    public String id;
    public double salary;
    Employee(){}
    Employee(String name,String id,double salary)    
    {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
}
//���ࣺ����Ա
class programmer extends Employee{
//���캯��
    programmer(String name,String id,double salary){
        super(name, id, salary); //ֱ�Ӽ̳�
    }
    public void work() {
        System.out.println("code....");   
    }
}


class Manager extends Employee{
    public int bonus;
    Manager(String name,String id,double salary,int bonus){
        super(name, id, salary);
        this.bonus=bonus;
    }
    public void work(){
        System.out.println("manage....");
    }
}
public class demo_abstract{	
	public static void main(String[] args) {
        Employee p1=new programmer("name", "id", 300000);
        Employee m1=new Manager("name", "id", 3000000, 1000000);
        p1.work();
        m1.work();
	}
}