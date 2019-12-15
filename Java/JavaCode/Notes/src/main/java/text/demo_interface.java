package text;

/**                            �����������ص㣡������������
 *                      ��������������ӿڱ�̣�������������
 *     �ӿڵ���ƣ��Զ�����Ϊ�βΣ��������ͬ��ʵ�ֵķ������á�
       ʵ���˷����ͷ�����ķ��룬�������Ĳ�ͬ��ʵ�ֵķ����岻ͬ�� *
 */
/*
�ӿڵĳ�Ա���ֶ� + ������Ĭ�϶��� public �ģ����Ҳ�������Ϊ private ���� protected��
�ӿڵ��ֶ�Ĭ�϶��� static �� final �ġ�
 */

interface Command {
    //������������У�������Ҫ�õ�����һ�ַ���
    //���Ǿ���ķ���ʵ�֣��ڽӿ��в����ṩ���������У�����ʵ��
    void process(int[] target);
}

class ProcessArray {
    //�˷����ǣ���Ҫ����һ��Command�µ��������������ʵ������process������������
    //���벻ͬ��Command���࣬Ч����ͬ
    public void process(int[] target, Command cmd) {
        cmd.process(target);
    }
}

class PrintCommand implements Command {
    //��д���෽��

    public void process(int[] target) {
        for (int tmp : target) {
            System.out.println("�������Ŀ�������Ԫ�أ�"+tmp);
        }
    }
}

class AddCommand implements Command {

    public void process(int[] target) {
        int sum = 0;
        for (int tmp : target) {
            sum += tmp;
        }
        System.out.println("Ԫ���ܺ�Ϊ��"+sum);
    }
}

public class demo_interface {

    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] target = { 3, 1, -4, 8, 9 };
        //��һ�δ������飬��δ���ȡ���ڴ�������һ�����󣬴˴�ΪPrintCommand����
        pa.process(target, new PrintCommand());
        //ʵ���˷����ͷ�����ķ��룬�������Ĳ�ͬ��ʵ�ֵķ����岻ͬ��
        System.out.println("--------------------------");
        //��һ�δ������飬��δ���ȡ���ڴ�������һ�����󣬴˴�ΪAddCommand����
        pa.process(target, new AddCommand());
    }
}