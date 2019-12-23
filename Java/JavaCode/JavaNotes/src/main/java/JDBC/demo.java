package JDBC;
import java.sql.*;
/* ִ��sql�������ַ�����
*  1.execute()ִ���κ�sql��䣬����booleanֵ��������ΪResultSet������true��
*  2.executeQuery()ִ��select��䣬���ز�ѯ���Ľ������
*  3.executeUpdate()��ִ��DML��䣬����һ����������ʾ����sql��Ӱ��ļ�¼������
*                   ��ִ��DDL������0��
*  DML�������������ݣ�insert��updata��delete....��
*  DDL(�������ݿ����create��alter��drop....)
* */
public class demo {
    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";
    public static void main(String[] args){
        /*
         * 1. ��������connection
         * 2. ����statement����
         * 3. sql���
         * 4. �����
         * */
        Connection connection=null;
        String sql=null;
        ResultSet rs=null;

        try {
            //�������������䣩
            Class.forName(Driver_Name);
            //ʹ��DriverManager��ȡ���ݿ����ӣ������ظ�connection����ʾjava�����ݿ������
            connection=DriverManager.getConnection(URL,User_Name,Password);
            //ʹ��connection������һ��Statement����
            Statement stmt=connection.createStatement();
            //sql���
            sql="select * from stu3";
            //ִ��sql��䣬
            rs=stmt.executeQuery(sql);
            //ResultSet��һ��ָ�룬ÿ��nextִ�У������ƶ�һ��
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+
                rs.getString(2));
            }
            /*ѭ������ResultSet��
             * int count =rs.getMetaData().getColumnCount();
             * for(int i=0;i<=count;i++){
             * 	System.out.print(rs.getString(i)+"\t");
             * }
             * */
            rs.close();
            stmt.close();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(connection!=null)connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
