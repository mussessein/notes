package JDBC;
import java.sql.*;
/*
 *  execute()��
 *  ͨ���˷���������һ������ͬʱִ�в�ͬ���͵�sql���ķ�������������
 * */
public class demo_Execute {
    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";

    public void executeSQL(String sql) throws Exception{

        Class.forName(Driver_Name);
        try(    //��ȡ���ݿ�����
                Connection connection=DriverManager.getConnection(URL,User_Name,Password);
                Statement stmt=connection.createStatement()
                )
        {
            /*ִ�в�������͵�sql��䣬execute����ִ�е�sql��䲻ͬ�����ص����Ͳ�ͬ��
            �������booleanֵ����˵��������һ��ResultSet���󣬼����Ա��������
            ���������0����������һ��sql�������Ĵ�����
             */
            boolean hasResultSet=stmt.execute(sql);
            //����ִ���˲�ѯ��䣬��᷵��һ����������󣬴洢��ѯ���Ľ��
            if (hasResultSet) {
                try (
                        //��ȡ�����
                        ResultSet rs = stmt.getResultSet()
                ) {
                    int count = rs.getMetaData().getColumnCount();
                    while (rs.next()) {
                        for (int i = 1; i <= count; i++) {
                            System.out.print(rs.getString(i) + "\t");
                        }
                        System.out.print("\n");
                    }
                }
            }else{ //û�з���ResultSet,��ִ�������sql��������
                System.out.println("��sql���Ӱ��ļ�¼��"+stmt.getUpdateCount()+"��");
            }
        }
    }


    public static void main(String[] args) throws Exception{
        demo_Execute demo=new demo_Execute();
        System.out.println("------ִ��ɾ�����DDL���------");
        demo.executeSQL("drop table if exists stu3");

        System.out.println("------ִ�н������DDL���------");
        demo.executeSQL("create table stu3(num int,name varchar(200))");

        System.out.println("------ִ�в������ݵ�DML���------");
        demo.executeSQL("insert into stu3 values(1,'zhao')");

        System.out.println("------ִ�в�ѯ���ݵ�DDL���------");
        demo.executeSQL("select * from stu3");
    }
}
