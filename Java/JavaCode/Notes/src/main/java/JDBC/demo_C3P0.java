package JDBC;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;
/*
��Ҫc3p0-0.9.1.2.jar��
C3P0����Դ���ܸ��ã�
1.���������Զ�������ʹ�õ�Connection
2.�������Զ�����Statement��ResultSet
 */
public class demo_C3P0 {

    public void fun() throws PropertyVetoException, SQLException {
         //�������ӳ�ʵ���������Ĵ����
        ComboPooledDataSource cpds=new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/student?serverTimezone=UTC");
        cpds.setUser("root");
        cpds.setPassword("123456");

        cpds.setMaxPoolSize(40);   //���ӳ����������
        cpds.setMinPoolSize(2);    //������С������
        cpds.setInitialPoolSize(10);  //�������ӳس�ʼ������
        cpds.setMaxStatements(180);  //�������ӳصĻ���Statements�������

        //��ȡ���Ӷ���Connetction
        Connection connection=cpds.getConnection();


    }
}

