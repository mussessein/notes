package text;
/**
 *                           ***单例设计模式***（同步代码块）
 *  * 保证对象唯一性的用法，
 * 原则： 1. 在本类中创建一个本类实例    
 *       2.不允许其他程序用new创建新的该类对象
 *      3. 对外提供一个方法，让其他程序可以获取该对象，
 */
public class demo_single {

    private int num;
//static只会创建一次
    private static final demo_single s = new demo_single(); 
//私有化该类的构造函数    
    private demo_single(){}   
//定义一个共有方法，将创建对象返回                           
    public static demo_single getInstance() {        
        return s;
    }
    public void setNum(int num) {
        this.num=num;
    }

    public static void main(String[] args) {
        demo_single s1=demo_single.getInstance();
        demo_single s2=demo_single.getInstance();
        s1.setNum(10);
        s2.setNum(20);
        System.out.println(s1.num);
        System.out.println(s2.num);  //此时运行，两个对象其实都是同一个对象，

//多线程同步代码块和单例结合
        single m1 = single.getInstance();
        single m2 = single.getInstance();
        m1.setNum(50);
        m2.setNum(60);
        System.out.println(m1.num);
        System.out.println(m2.num);
    }
    
}
//多线程同步代码块和单例结合 
class single
{
    public int num;
    private static single m = null;
    private single(){}
    public static single getInstance() {
        if(m == null)
        {
            if(m==null)
            synchronized(single.class)
            {
                m = new single();
            }
        }
        return m;        
    }
    public void setNum(int num) {
        this.num=num;
    }
}