package Lambda;

/*
使用Lambda来实现Runnable接口

 */
public class Lambda_Thread {

    public static void main(String[] args) {

        // 传统方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类方法");
            }
        }).start();

        // Lambda方法:
        new Thread(()-> System.out.println("使用 lambda expression")).start();

    }
}
