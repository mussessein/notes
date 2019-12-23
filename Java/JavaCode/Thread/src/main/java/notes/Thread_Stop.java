package notes;
/**
 三种方法停止正在运行的线程:
 1.Thread.interrupt():并不一定能真正停止线程
 2.使用退出标志,使线程正常执行完run后终止
 3.stop():暴力停止,已经过期的方法,不建议使用

 暂停线程(挂起):
 suspend():暂停
 resume():恢复
 过期方法,缺点:
 1.独占:容易造成公共的同步对象的独占,使其他线程无法访问公共的同步对象
 2.不同步:因为线程的暂停而导致数据的不同步情况
 */
class StopRun1 implements Runnable{
    private boolean flag = true;
    public synchronized void run() {
        while(flag){
            System.out.println(Thread.currentThread().getName()+"....正在运行");
        }
    }   
    public void setFlag() {
        flag = false;
    }
}

//第二种方法interrupt()
class StopRun2 implements Runnable{
    private boolean flag = true;
    public synchronized void run() {
        while(flag){
            try{
                wait();
            }
            catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"...."+e);
                //当线程被强制唤醒之后，结束循环
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

//第一种方法
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

//第二种方法(interrupt)
        StopRun2 sr2 = new StopRun2(); 
        Thread s2 = new Thread(sr2);
        Thread s3 = new Thread(sr2);
        s2.start();
// s3.setDaemon(true);后台线程，设定之后，无需结束线程，其他前台线程结束之后，后台线程自动消失
        s3.start();
        for(;;){
            if (++num2 == 200) {
                s2.interrupt();
                s3.interrupt();
                break;
            }
            System.out.println("@@@@@");
        }
//全部线程结束
        System.out.println("over");
    }
}



