package text;
/**
 * 
 * �������1.��������
 *          2.thisʹ�ã�˭�����˴˺������˺�����this��ָ��˭������Ա�����;ֲ�����ͬ��������ʹ�ã�
 *              2.1:this Ӧ��
 *          3.static����̬�������η���
    *              3.1 static���ι�������Ϊ���ж�������������ݡ�
    *              3.2 static �������γ�Ա�������ڶ�����ڣ�������ļ��أ����Ѿ�������
    *              3.3 static ���˱�������ã�p1.country)��������ֱ�ӱ��������ã�practice_2.country)
    *              3.4 ������Ϊ��̬������ֻ�ܷ��ʾ�̬̬����������ǰû��static������ֱ�ӷ��ʣ�����ͨ�������ڲ���(��demo_1)
    *              3.5 ��̬����� �� static{ } �����ڸ����ʼ���������ڹ��캯����ʼ������  
    *                              ������ص�ʱ�򣬵�һʱ�����о�̬�����
    *     *****    3.6 ��̬������û��this��������������
 *          4.����������飺ÿ�������ʼ����ʱ��ͻ����һ��
 *                           {  } û������                                            
 */         
class practice_2 {
    //���ֲ�������
        public static void main(String[] args) {
            //�����������Ͳ������ݣ�
            //��������x��ֵ��show��������show����������֮��,�뿪�ڴ棬��������������Ӱ��
            int x = 3;
            show_1(x);
            System.out.println(x);
    
            //�����������Ͳ������ݣ�
            //�������������ַ���ݸ�show_2������ֱ�Ӹ����ڴ��е�p1�����x��ֵ
            practice_2 p1=new practice_2();
            p1.x=9;
            show_2(p1);
            System.out.println(p1.x);
    
    //this
            practice_2 p2=new practice_2("Y'Shtola");
            System.out.println(p2.name);
    
    //thisӦ�ã�
            practice_2 p3=new practice_2();
            p3.age=11;
            practice_2 p4=new practice_2();
            p4.age=18;
            p3.Compare(p4);  //thisָp3
    
    //static���εı�����ֱ�ӱ���������
            System.out.println(practice_2.country);
        }
    
    //��Ա������
        int x=3;
        private String name;
        private int age;
    //static ���ε����ݣ����ж���Ĭ�Ϲ���
        static String country = "CN";
    
    //�����ʼ��ʱ �͵�һʱ��������
        static{
            System.out.println("Hahahaha");
        }
    //��������,ÿ�������ʼ��������һ��
        {
            System.out.println("***********");          
        }
    
    //��Ա������
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
    
    //���캯��
        practice_2(){}
        practice_2(String name)
        {
            this.name=name;
        }
        
    }