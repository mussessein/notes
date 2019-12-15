package text;

/**ö����
 * ��ʵ��һ����Ů�Ա� ö�ٵ�����
 */
enum Gender {
    //ֱ���г�����ö��ʵ��
    MALE, FEMALE;

    public String gender;

    public void setGender(String gender) {
        switch (this) {
        case MALE:
            if (gender.equals("��")) {
                this.gender = gender;
            } else {
                System.out.println("��������");
                return;
            }
        case FEMALE:
            if (gender.equals("Ů")) {
                this.gender = gender;
            } else {
                System.out.println("��������");
                return;
            }
        }
    }

    public String getGender() {
        return this.gender;
    }
}


enum Color_1 {
    //ʵ�ֳ��󷽷�
    RED("��") {
        public void print_1()
        {
            System.out.println("��");
        }
    },
    GREEN("��") {
        public void print_1()
        {
            System.out.println("��");
        }
    };
    //˽�л��������������޷�����ʵ��
    private Color_1(String name)
    {

    }
    //���Դ��ڳ��󷽷�����ÿһ��ʵ����ʵ�ֳ��󷽷�
    public abstract void print_1();
}


public class demo_Enum {

    public static void main(String[] args) {
        //ö����������һ������ValueOf()
//        Gender g = Gender.valueOf("FEMALE");
        Gender g = Enum.valueOf(Gender.class,"FEMALE");
        g.setGender("Ů");
        System.out.println(g + "�����ˣ�" + g.getGender());
        
        Gender b = Gender.valueOf("MALE");
        b.setGender("Ů");
        System.out.println(b + "�����ˣ�" + b.getGender());

        //ֱ�ӵ���ö��ʵ����
        Color_1.GREEN.print_1();
        //����ö���±�
        int Enum_num = Color_1.GREEN.ordinal();
        System.out.println(Enum_num);
    }
}
