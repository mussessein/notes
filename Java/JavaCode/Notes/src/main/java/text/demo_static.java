package text;
/**
 * 
 * 面向对象：1.参数传递
 *          2.this使用：谁调用了此函数，此函数的this就指向谁。（成员变量和局部变量同名，方便使用）
 *              2.1:this 应用
 *          3.static（静态）（修饰符）
    *              3.1 static修饰过的数据为所有对象所共享的数据。
    *              3.2 static 用于修饰成员，优先于对象存在，随着类的加载，就已经存在了
    *              3.3 static 除了被对象调用（p1.country)，还可以直接被类名调用（practice_2.country)
    *              3.4 主函数为静态方法，只能访问静态态方法，方法前没有static，不能直接访问，可以通过匿名内部类(见demo_1)
    *              3.5 静态代码块 ： static{ } ：用于给类初始化（类似于构造函数初始化对象）  
    *                              在类加载的时候，第一时间运行静态代码块
    *     *****    3.6 静态方法中没有this！！！！！！！
 *          4.构造对象代码块：每个对象初始化的时候就会调用一次
 *                           {  } 没有名字                                            
 */         
class practice_2 {
    //两种参数传递
        public static void main(String[] args) {
            //基本数据类型参数传递：
            //主函数的x赋值给show函数，在show函数内运算之后,离开内存，不会对主函数造成影响
            int x = 3;
            show_1(x);
            System.out.println(x);
    
            //引用数据类型参数传递：
            //主函数将对象地址传递给show_2函数，直接更改内存中的p1对象的x的值
            practice_2 p1=new practice_2();
            p1.x=9;
            show_2(p1);
            System.out.println(p1.x);
    
    //this
            practice_2 p2=new practice_2("Y'Shtola");
            System.out.println(p2.name);
    
    //this应用：
            practice_2 p3=new practice_2();
            p3.age=11;
            practice_2 p4=new practice_2();
            p4.age=18;
            p3.Compare(p4);  //this指p3
    
    //static修饰的变量，直接被类名调用
            System.out.println(practice_2.country);
        }
    
    //成员变量：
        int x=3;
        private String name;
        private int age;
    //static 修饰的数据，所有对象默认共享
        static String country = "CN";
    
    //在类初始化时 就第一时间运行了
        static{
            System.out.println("Hahahaha");
        }
    //构造代码块,每个对象初始化，调用一次
        {
            System.out.println("***********");          
        }
    
    //成员函数：
        public static void show_1(int x) {
            x=4;
            return;
        }
        public static void show_2(practice_2 p) {
            p.x=11;
            return;
        }
        public void Compare(practice_2 p) {
            if(this.age==p.age)
            {
                System.out.println("true");
            }
            else
            {
                System.out.println("false");
            }     
        }
    
    //构造函数
        practice_2(){}
        practice_2(String name)
        {
            this.name=name;
        }
        
    }