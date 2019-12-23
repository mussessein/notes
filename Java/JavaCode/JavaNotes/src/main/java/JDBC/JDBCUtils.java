package JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC的工具类
 * @author jt
 *
 */
public class JDBCUtils {
    private static final String driverClass;
    private static final String url;
    private static final String username;
    private static final String password;

    static{
        /*
        1.加载属性文件并解析：
        2.通常情况下使用类的加载器的方式进行获取
        3.类加载之后，所有static方法，都自动执行，驱动自动注册，
         */
        Properties props = new Properties();

        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("JDBC.properties");
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driverClass = props.getProperty("driverClass");
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
    }

    /**
     * 注册驱动的方法
     * @throws ClassNotFoundException
     */
    public static void loadDriver() throws ClassNotFoundException{
        Class.forName(driverClass);
    }

    /**
     * 获得连接的方法:
     * @throws SQLException
     */
    public static Connection getConnection() throws Exception{
        loadDriver();
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    /**
     * 资源释放
     */
    public static void release(Statement stmt,Connection conn){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    public static void release(ResultSet rs,Statement stmt,Connection conn){
        if(rs!= null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
}
