package JDBC;

import org.apache.commons.dbcp2.*;

import java.sql.*;

/*
����jar����commons-dbcp.jar/commons-pool.jar
    1.���ӳر���ʵ�֣�javax.sql.DataSource
    2.DBCP��Connection�����close������������ǿ�����ǹر����ӣ����ǹ黹����
 */
public class demo_DBCP {

    /*
    1.�������ӳض���
    2.�����Ĵ����
    3.���óز���
    4.�õ����Ӷ���
     */
    public void fun() throws SQLException {

        //�������ӳأ������Ĵ����
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/student?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        dataSource.setMaxTotal(20);//�������������
        dataSource.setMinIdle(3);  //��С��������
        dataSource.setMaxWaitMillis(1000);//���ȴ�����ʱ��

        //�õ����Ӷ���
        Connection connection=dataSource.getConnection();
        System.out.println(connection.getClass().getName());
        //�黹����
        connection.close();
    }
}
