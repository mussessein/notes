package notes;
/*
�߳����ȼ�:
1.���ȼ��ϸ� ����cpu��Դ�϶�
2.�߳����ȼ����м̳�����,A�߳�����B�߳�,��ôB�߳����ȼ���A��ͬ
3.���ȼ����������:���ȼ��ϸߵ��߳�,����һ����ִ�����
setPriority()�����߳����ȼ�
getPriority()�����߳����ȼ�
 */
public class Thread_Prrioity extends Thread{

    public Thread_Prrioity(String name){
        super(name);
    }

    @Override
    public void run() {
        //�����������̣߳�ÿ�����50��
        for (int i=0;i<50;i++){
            System.out.println(getName()+i+",�����ȼ���"+getPriority());
        }
    }

    public static void main(String[] args){
        //�ı����̵߳����ȼ�
        Thread.currentThread().setPriority(6);
        for (int i=0;i<30;i++){
            if (i==10){
                Thread_Prrioity low=new Thread_Prrioity("�ͼ��߳�");
                //start������ֻ���߳̾���״̬������������
                low.start();
                System.out.println("low�̵߳ĳ�ʼ���ȼ���"+low.getPriority());
                //�����߳�����Ϊ������ȼ�
                low.setPriority(MIN_PRIORITY);
            }

            if (i==20){
                Thread_Prrioity high=new Thread_Prrioity("�߼��߳�");
                high.start();
                System.out.println("high�̵߳ĳ�ʼ���ȼ���"+high.getPriority());
                //�����߳�����Ϊ������ȼ�
                high.setPriority(MAX_PRIORITY);

            }

        }




    }
}
