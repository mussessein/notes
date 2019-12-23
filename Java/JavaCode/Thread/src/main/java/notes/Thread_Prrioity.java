package notes;
/*
线程优先级:
1.优先级较高 则获得cpu资源较多
2.线程优先级具有继承特性,A线程启动B线程,那么B线程优先级与A相同
3.优先级具有随机性:优先级较高的线程,并不一定先执行完毕
setPriority()设置线程优先级
getPriority()返回线程优先级
 */
public class Thread_Prrioity extends Thread{

    public Thread_Prrioity(String name){
        super(name);
    }

    @Override
    public void run() {
        //后续创建的线程，每个输出50次
        for (int i=0;i<50;i++){
            System.out.println(getName()+i+",的优先级是"+getPriority());
        }
    }

    public static void main(String[] args){
        //改变主线程的优先级
        Thread.currentThread().setPriority(6);
        for (int i=0;i<30;i++){
            if (i==10){
                Thread_Prrioity low=new Thread_Prrioity("低级线程");
                //start方法，只是线程就绪状态，并不是运行
                low.start();
                System.out.println("low线程的初始优先级："+low.getPriority());
                //将次线程设置为最低优先级
                low.setPriority(MIN_PRIORITY);
            }

            if (i==20){
                Thread_Prrioity high=new Thread_Prrioity("高级线程");
                high.start();
                System.out.println("high线程的初始优先级："+high.getPriority());
                //将次线程设置为最低优先级
                high.setPriority(MAX_PRIORITY);

            }

        }




    }
}
