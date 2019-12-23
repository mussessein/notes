package notes;
/**
 ���ַ���ֹͣ�������е��߳�:
 1.Thread.interrupt():����һ��������ֹͣ�߳�
 2.ʹ���˳���־,ʹ�߳�����ִ����run����ֹ
 3.stop():����ֹͣ,�Ѿ����ڵķ���,������ʹ��

 ��ͣ�߳�(����):
 suspend():��ͣ
 resume():�ָ�
 ���ڷ���,ȱ��:
 1.��ռ:������ɹ�����ͬ������Ķ�ռ,ʹ�����߳��޷����ʹ�����ͬ������
 2.��ͬ��:��Ϊ�̵߳���ͣ���������ݵĲ�ͬ�����
 */
class StopRun1 implements Runnable{
    private boolean flag = true;
    public synchronized void run() {
        while(flag){
            System.out.println(Thread.currentThread().getName()+"....��������");
        }
    }   
    public void setFlag() {
        flag = false;
    }
}

//�ڶ��ַ���interrupt()
class StopRun2 implements Runnable{
    private boolean flag = true;
    public synchronized void run() {
        while(flag){
            try{
                wait();
            }
            catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"...."+e);
                //���̱߳�ǿ�ƻ���֮�󣬽���ѭ��
                flag = false;
            }
            System.out.println(Thread.currentThread().getName()+"....");
        }
    }
}



class stopdemo{
    public static void main(String[] args) {
        int num1 = 0;
        int num2 = 0;

//��һ�ַ���
        StopRun1 sr1 = new StopRun1(); 
        Thread s0 = new Thread(sr1);
        Thread s1 = new Thread(sr1);
        s0.start();
        s1.start();
        for(;;){
            if (++num1 == 5) {
                sr1.setFlag();
                break;
            }
            System.out.println("*****");
        }

//�ڶ��ַ���(interrupt)
        StopRun2 sr2 = new StopRun2(); 
        Thread s2 = new Thread(sr2);
        Thread s3 = new Thread(sr2);
        s2.start();
// s3.setDaemon(true);��̨�̣߳��趨֮����������̣߳�����ǰ̨�߳̽���֮�󣬺�̨�߳��Զ���ʧ
        s3.start();
        for(;;){
            if (++num2 == 200) {
                s2.interrupt();
                s3.interrupt();
                break;
            }
            System.out.println("@@@@@");
        }
//ȫ���߳̽���
        System.out.println("over");
    }
}



