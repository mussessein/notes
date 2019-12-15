package text;
/**
 *                           ***�������ģʽ***��ͬ������飩
 *  * ��֤����Ψһ�Ե��÷���
 * ԭ�� 1. �ڱ����д���һ������ʵ��    
 *       2.����������������new�����µĸ������
 *      3. �����ṩһ��������������������Ի�ȡ�ö���
 */
public class demo_single {

    private int num;
//staticֻ�ᴴ��һ��
    private static final demo_single s = new demo_single(); 
//˽�л�����Ĺ��캯��    
    private demo_single(){}   
//����һ�����з��������������󷵻�                           
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
        System.out.println(s2.num);  //��ʱ���У�����������ʵ����ͬһ������

//���߳�ͬ�������͵������
        single m1 = single.getInstance();
        single m2 = single.getInstance();
        m1.setNum(50);
        m2.setNum(60);
        System.out.println(m1.num);
        System.out.println(m2.num);
    }
    
}
//���߳�ͬ�������͵������ 
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