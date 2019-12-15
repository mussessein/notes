package observer;
import java.util.*;

/**
 * ���ߣ�Observer��ģʽ�Ķ��壺
 * ָ�����������һ�Զ��������ϵ����һ�������״̬�����ı�ʱ���������������Ķ��󶼵õ�֪ͨ�����Զ����¡�
 * ����ģʽ��ʱ�ֳ�������-����ģʽ��ģ��-��ͼģʽ�����Ƕ�����Ϊ��ģʽ��
 */
public class BellEventTest
{
    public static void main(String[] args)
    {
        BellEventSource bell=new BellEventSource();    //�壨�¼�Դ��
        bell.addPersonListener(new TeachEventListener()); //ע�����������ʦ��
        bell.addPersonListener(new StuEventListener());    //ע���������ѧ����
        bell.ring(true);   //���Ͽ�����
        System.out.println("------------");
        bell.ring(false);  //���¿�����
    }
}
//�����¼��ࣺ���ڷ�װ�¼�Դ��һЩ���¼���صĲ���
class RingEvent extends EventObject
{
    private static final long serialVersionUID=1L;
    private boolean sound;    //true��ʾ�Ͽ�����,false��ʾ�¿�����
    public RingEvent(Object source,boolean sound)
    {
        super(source);
        this.sound=sound;
    }
    public void setSound(boolean sound)
    {
        this.sound=sound;
    }
    public boolean getSound()
    {
        return this.sound;
    }
}
//Ŀ���ࣺ�¼�Դ����
class BellEventSource
{
    private List<BellEventListener> listener; //����������
    public BellEventSource()
    {
        listener=new ArrayList<BellEventListener>();
    }
    //���¼�Դ�󶨼�����
    public void addPersonListener(BellEventListener person)
    {
        listener.add(person);
    }
    //�¼������������ӣ�������sound��ֵ�����仯ʱ�������¼���
    public void ring(boolean sound)
    {
        String type=sound?"�Ͽ���":"�¿���";
        System.out.println(type+"��!");
        RingEvent event=new RingEvent(this, sound);
        notifies(event);    //֪ͨע���ڸ��¼�Դ�ϵ����м�����
    }
    //���¼�����ʱ,֪ͨ���ڸ��¼�Դ�ϵ����м�����������Ӧ�������¼���������
    protected void notifies(RingEvent e)
    {
        BellEventListener person=null;
        Iterator<BellEventListener> iterator=listener.iterator();
        while(iterator.hasNext())
        {
            person=iterator.next();
            person.heardBell(e);
        }
    }
}


//����۲����ࣺ�����¼�������
interface  BellEventListener extends EventListener
{
    //�¼�����������������
    public void heardBell(RingEvent e);
}
//����۲����ࣺ��ʦ�¼�������
class TeachEventListener implements BellEventListener
{
    public void heardBell(RingEvent e)
    {
        if(e.getSound())
        {
            System.out.println("��ʦ�Ͽ���...");
        }
        else
        {
            System.out.println("��ʦ�¿���...");
        }
    }
}
//����۲����ࣺѧ���¼�������
class StuEventListener implements BellEventListener
{
    public void heardBell(RingEvent e)
    {
        if(e.getSound())
        {
            System.out.println("ͬѧ�ǣ��Ͽ���...");
        }
        else
        {
            System.out.println("ͬѧ�ǣ��¿���...");
        }
    }
}