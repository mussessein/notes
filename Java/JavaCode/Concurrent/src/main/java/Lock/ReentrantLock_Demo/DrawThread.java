package Lock.ReentrantLock_Demo;
/*
����ȡǮ���߳�
 */
public class DrawThread extends Thread{
    //�����˻�
    private Account account;
    private double drawAmount;
    public DrawThread(String name,Account account,double drawAmount){
        super(name);
        this.account=account;
        this.drawAmount=drawAmount;
    }

    @Override
    //�ظ�ȡǮ100�ε��߳�
    public void run() {
        for (int i=0;i<50;i++){
            account.draw(drawAmount);
        }
    }
}
