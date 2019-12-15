package text;

/**枚举类
 * 简单实现一个男女性别 枚举的例子
 */
enum Gender {
    //直接列出所有枚举实例
    MALE, FEMALE;

    public String gender;

    public void setGender(String gender) {
        switch (this) {
        case MALE:
            if (gender.equals("男")) {
                this.gender = gender;
            } else {
                System.out.println("参数错误");
                return;
            }
        case FEMALE:
            if (gender.equals("女")) {
                this.gender = gender;
            } else {
                System.out.println("参数错误");
                return;
            }
        }
    }

    public String getGender() {
        return this.gender;
    }
}


enum Color_1 {
    //实现抽象方法
    RED("红") {
        public void print_1()
        {
            System.out.println("红");
        }
    },
    GREEN("绿") {
        public void print_1()
        {
            System.out.println("绿");
        }
    };
    //私有化构造器，类外无法创建实例
    private Color_1(String name)
    {

    }
    //可以存在抽象方法，在每一项实例中实现抽象方法
    public abstract void print_1();
}


public class demo_Enum {

    public static void main(String[] args) {
        //枚举类型生成一个对象，ValueOf()
//        Gender g = Gender.valueOf("FEMALE");
        Gender g = Enum.valueOf(Gender.class,"FEMALE");
        g.setGender("女");
        System.out.println(g + "代表了：" + g.getGender());
        
        Gender b = Gender.valueOf("MALE");
        b.setGender("女");
        System.out.println(b + "代表了：" + b.getGender());

        //直接调用枚举实例，
        Color_1.GREEN.print_1();
        //返回枚举下标
        int Enum_num = Color_1.GREEN.ordinal();
        System.out.println(Enum_num);
    }
}
