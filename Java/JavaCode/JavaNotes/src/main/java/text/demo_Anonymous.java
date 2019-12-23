package text;

/**                                       �����ڲ���
 * 1.ʵ�ֽӿڵ������ڲ��ࡣ
 * 2.ʵ�ֳ�����������ڲ��ࡣ
 * 
 * ��Ҫ���ĳ���ӿڷ�����ֻ����һ�Σ�����Ҫ�����˽ӿڵ�ʵ����
 * ���Զ��������ڲ��࣬����ɷ����塣
 */

//1.ʵ�ֽӿڵ������ڲ��ࡣ
interface Product {
    public double getPrice();

    public String getName();
}

//2.ʵ�ֳ�����������ڲ��ࡣ
abstract class Device {

    public abstract double getPrice();

    public abstract String getName();

    public Device(){}
    public Device(String name) {
    }
}

public class demo_Anonymous {

    //����һ����������Ҫһ��Product������Ϊ����
    public void test(Product p) {
        System.out.println("����һ��" + p.getName() + "������" + p.getPrice());
    }
    public void test(Device d) {
        System.out.println("����һ��" + d.getName() + "������" + d.getPrice());
    }

    public static void main(String[] args) {
    // 1.�ӿ�
        demo_Anonymous da1 = new demo_Anonymous();
        //��ʱ�����Ҫ��test()��������Ҫһ��Product����
        //����˽ӿ�ʵ�����ʹ��һ��
        //�ڲ���Ҫ����Product���������£�����ʹ�������ڲ���
        //���ڲ�����ʵ�ַ����壡����
        da1.test(new Product() {
            public double getPrice() {
                return 6666;
            }
            public String getName() {
                return "GDX";
            }
        });
    //2.������
        demo_Anonymous da2 = new demo_Anonymous();
        

        da2.test(new Device("ʾ����") {
            public double getPrice() {
                return 666;
            }
            public String getName() {
                return "ʾ����";
            }
        });

        Device d = new Device() 
        {
            //��ʼ����
            {
                System.out.println("------��ʼ�������ڲ���-------");
            }
            //ʵ�ֳ��󷽷�
            public double getPrice() {
                return 777;
            }
            public String getName() {
                return "΢��¯";
            }
        };
        da2.test(d);
        
    }
}