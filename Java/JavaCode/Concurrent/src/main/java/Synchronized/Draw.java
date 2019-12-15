package Synchronized;
/*
取钱的线程
 */

public class Draw extends Thread {
    //每次进行取钱，都需要一个账户
    private Account account;
    private double drawAmount;
    public Draw(String name,Account account,double drawAmount){
        //继承线程名字方法
        super(name);
        this.account=account;
        this.drawAmount=drawAmount;
    }
    //取钱方法：继承Thread类，必须重写run方法线程执行体
    //不能直接对run方法进行同步！！！！！！！！！无法实现同步！！！！！！
    //但可以实现同步代码块
    @Override
    public void run() {
        account.draw(drawAmount);
    }

    public static void main(String[] args){
     //设置一个账户
        Account account=new Account("88888888",100000.0);
        //创建两个线程，对同一个账户进行取钱
        new Draw("张三",account,90000.0).start();
        new Draw("李四",account,90000.0).start();
    }
}
