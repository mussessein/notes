package Lambda;

/*
ʹ��Lambda��ʵ��Runnable�ӿ�

 */
public class Lambda_Thread {

    public static void main(String[] args) {

        // ��ͳ����
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("�����ڲ��෽��");
            }
        }).start();

        // Lambda����:
        new Thread(()-> System.out.println("ʹ�� lambda expression")).start();

    }
}
