package Lock.ReentrantLock_Demo;
/*
�����Ǯ���߳�
 */
public class DepositThread extends Thread{

    private Account account;
    private double depositAmount;
    public DepositThread(String name,Account account,double depositAmount){
        super(name);
        this.account=account;
        this.depositAmount=depositAmount;
    }

    @Override
    //�ظ���Ǯ100�ε��߳�
    public void run() {
        for (int i=0;i<50;i++){
            account.deposit(depositAmount);
        }
    }
}
