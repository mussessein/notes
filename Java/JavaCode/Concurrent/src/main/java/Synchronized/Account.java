package Synchronized;
/*
传统线程通信的方式，synchronized修饰方法，会容易出现线程阻塞

实现对同一个账户进行 取钱操作

 */
public class Account {
    //封装账户和金额
    private String account_number;
    private double balance;
    //设置是否有存款的标志位
    private boolean flag=false;

    public Account(String account_number,double balance){
        this.account_number=account_number;
        this.balance=balance;
    }
    //设置GETTER,SETTER方法
    public String getAccount_number() {
        return account_number;
    }
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
    public double getBalance() {
        return balance;
    }

    //同步下的取钱方法
    public synchronized void draw(double drawAmount){

        //判断，如果余额大于取钱金额，则可以执行取钱
        if (balance>=drawAmount){
            System.out.println(Thread.currentThread().getName()+"取钱成功，取出："+drawAmount);

            try{
                Thread.sleep(1);
            }catch (InterruptedException e){e.printStackTrace();}
            //修改账户余额
            balance-=drawAmount;
            System.out.println("余额为："+balance);
        }
        else{
            System.out.println("余额不足，取钱失败");
        }
    }
}
