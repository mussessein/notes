package inherit;

/**
 * ��̬��ʼ�����ִ��˳��
 */
class Root {
    static {
        System.out.println("Root��̬��ʼ����");
    }
    {
        System.out.println("Root��ͨ��ʼ����");
    }

    public Root() {
        System.out.println("Root�޲ι�����");
    }
}

class Mid extends Root {
    static {
        System.out.println("Mid��̬��ʼ����");
    }
    {
        System.out.println("Mid��ͨ��ʼ����");
    }

    public Mid() {
        System.out.println("Mid�޲ι�����");
    }
}

class Leaf extends Mid {
    static {
        System.out.println("Leaf��̬��ʼ����");
    }
    {
        System.out.println("Leaf��ͨ��ʼ����");
    }

    public Leaf() {
        System.out.println("Leaf�޲ι�����");
    }
}
public class StaticTest {
    public static void main(String[] args) {
        //��һ�δ������࣬��ʼ������ִ�ж��㸸��ľ�̬��ʼ���飬�̶���ͨ��ʼ���飬���ǹ�������������
        new Leaf();
        System.out.println("---------------------");
        //�����ʼ���ɹ�֮����������о�һֱ���ڣ����Եڶ��δ������಻���ٽ��о�̬��ʼ��
        new Leaf();
    }
    
}