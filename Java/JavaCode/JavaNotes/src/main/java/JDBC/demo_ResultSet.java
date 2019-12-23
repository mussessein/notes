package JDBC;
import java.sql.*;
/* �ɹ������ɸ��µĽ����
 * �ڴ���Statement()�����ʱ����������Կ���ResultSet
 * 1.ResultSet.Type_FORWARD_ONLY            ���Ƽ�¼ָ��ֻ����ǰ�ƶ�
 * 2.ResultSet.Type_SCROLL_INSENSITIVE      ���Ƽ�¼ָ����������ƶ������ײ����ݸı䲻Ӱ��ResultSet���
 * 3.
 *
 * 1.ResultSet.CONCUR_READ_ONLY        ָ��ResultSetֻ���Ĳ���ģʽ
 * 2.ResultSet.CONCUR_UPDATABLE         ָ��ResultSet�ɸ��µĲ���ģʽ
 * */
public class demo_ResultSet {
    /*
    * ���ý�����ɹ���/�ɸ���
    * ͨ������ָ����ƶ�
    * 1.��ɱ���������
    * 2.���������˵ڶ��е�����
    *
    * */
    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";

    public void query(String sql) throws Exception{

        Class.forName(Driver_Name);
        try(
                Connection connection=DriverManager.getConnection(URL,User_Name,Password);
                //����PreparedStatement���󣬴��������ɹ���/�ɸ��µĲ���
                PreparedStatement pstmt=connection.prepareStatement(sql
                        ,ResultSet.TYPE_SCROLL_INSENSITIVE
                        ,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs=pstmt.executeQuery()
                ){
            //ʹָ�����򣬴Ӻ���ǰ��
            rs.last();
            //�õ���ֵ
            int rowCount =rs.getRow();
            //ѭ���������
            for (int i=rowCount;i>0;i--){
                /* i���Ϊ����ָ���ƶ�������ڽ��������Ķ�Ӧ�к�i
                *  i���Ϊ����ָ���ƶ�������ڽ��������Ķ�Ӧ�к�i
                *  �˴�iΪ��
                *  */
                rs.absolute(i);
                //������е�ÿһ��
                System.out.println(rs.getString(1)+"\t"
                    +rs.getString(2));
                //���õڶ��е����ݣ������У��������ݣ�
                rs.updateString(2,"ѧ����"+i);
                //���µ�ǰ������ĵ�ǰ�е�����
                rs.updateRow();
            }
        }
    }

    public static void main(String[] args) throws  Exception{

        demo_ResultSet dr=new demo_ResultSet();
        dr.query("select * from stu3");
    }
}
