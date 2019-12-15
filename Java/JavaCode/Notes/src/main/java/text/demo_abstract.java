package text;
/**
 * 抽象类：（本质就是一种模板！！！便于生成设计子类！）
 * 1. 抽象方法只有声明 ，没有具体实现。抽象方法必须在抽象类中
 * 2. 抽象类不可以被实例化！调用没有意义 
 * 3. 所有的抽象类必须有子类覆盖所有抽象方法后，该子类可以实例化 ，否则子类还是抽象类
 * 4. 抽象关键字不可以和private/static/final
 * 5. 抽象类一定是一个父类
 */

/*抽象类实例：
程序员：
        属性：姓名，工号，薪水
        行为：工作
经理：
        属性：姓名，工号，薪水，    奖金（私有属性）
        行为：工作
都属于雇员
共同抽象行为：工作
*/
abstract class Employee {

	//含有抽象方法的类，必须是抽象类，抽象方法，没有方法体！
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
//子类：程序员
class programmer extends Employee{
//构造函数
    programmer(String name,String id,double salary){
        super(name, id, salary); //直接继承
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