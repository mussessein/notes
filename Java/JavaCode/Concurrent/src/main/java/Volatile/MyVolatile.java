package Volatile;

import java.util.concurrent.TimeUnit;

/**
 * ����volatile�ؼ��ֵ��ڴ�ɼ�
 * �߳�A�ڸ�����flag������while��ѭ����
 * ������volatile����ʹ�޸�heap�е�flag���߳�AҲ�޷�����flag��ֵ
 * <p>
 * volatile ʹ�����εı����ڷ����仯��ʱ��֪ͨ�����߳̽��и��£����ɼ��ԣ�
 */
public class MyVolatile {

    /*volatile*/ boolean flag = true;

    void test() {
        System.out.println("start");
        while (flag) {

        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        MyVolatile T = new MyVolatile();
        // �߳�Aִ��
        new Thread(T::test, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ��ͼ�����һ���߳�
        T.flag = false;
    }
}
