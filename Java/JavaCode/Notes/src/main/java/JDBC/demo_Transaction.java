package JDBC;
import java.sql.*;
/*   JDBC���񣺣�ֻ�ܴ���һ��Connection����
* 1.�������� start transaction
* 2.����sql����
* 3.commit�ύ/rollback�ع�
*
* */

public class demo_Transaction {

    public static final String Driver_Name="com.mysql.cj.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";
    Connection connection =null;

    //�����������ֻ����һ��Connection����
    public void transfer(String from,String to,double money){

        try {
            //������Ĳ�������ʹ��Connection����
            connection = DriverManager.getConnection(URL, User_Name, Password);
            //��������
            connection.setAutoCommit(false);
            demo_Transaction dt=new demo_Transaction();
            //����ת�˷�from����ȥ���
            dt.updateBalance(connection,from,-money);
            //�������˷�to�����Ͻ��
            dt.updateBalance(connection,to,money);
            //�ύ����
            connection.commit();
            connection.close();

        }catch (Exception e){
            try{      //����׳��쳣���Զ��ع�����
                connection.rollback();
                connection.close();
            }catch (SQLException e1){e1.printStackTrace();}
        }
    }
    //�޸�ĳ���˻��Ľ��
    //�����������ֻ����һ��Connection����ͨ������Ĳ������������
    public void updateBalance(Connection connection,String name,double money){

        try {
            //����ĳ�˵��˻����Ͻ��
            String sql="update stu3 set money=money+? where name=?";
            PreparedStatement prsmt=connection.prepareStatement(sql);
            prsmt.setDouble(1,money);
            prsmt.setString(2,name);
            prsmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        //zs��lsת��500
        new demo_Transaction().transfer("ls","zs",1000);
    }
}












