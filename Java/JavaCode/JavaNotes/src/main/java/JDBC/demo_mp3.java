package JDBC;
import org.apache.commons.io.IOUtils;
import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.*;
/*              谨慎运行
* */
public class demo_mp3 {

    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";

    Connection connection=null;
    public void fun_1(){


        try {
            Class.forName(Driver_Name);
            connection=DriverManager.getConnection(URL,User_Name,Password);
            String sql="insert into mp3 value (?,?,?)";
            PreparedStatement pstmt=connection.prepareStatement(sql);
            //设置含参的sql语句参数
            pstmt.setInt(1,1);
            pstmt.setString(2,"MUSIC.jpg");
            /*Blog是一个接口，实现类SerialBlob
            * 1.将文件转化为byte[]
            * 2.使用byte[]创建Blob
            * */
            byte[] bytes= IOUtils.toByteArray(new FileInputStream("C:\\Users\\whr\\Pictures\\Saved Pictures\\a.jpg"));
            Blob blob=new SerialBlob(bytes);
            pstmt.setBlob(3,blob);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (connection!=null)connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public void fun_2() throws Exception{
        Connection connection=DriverManager.getConnection(URL,User_Name,Password);
        String sql="select * from mp3";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        if (rs.next()){
            Blob blob=rs.getBlob("data");
            /*
            * 1.通过Blob得到输入流对象
            * 2.创建输出流对象
            * 3.把输入流的数据写入到输出流中
            * */
            InputStream is=blob.getBinaryStream();
            OutputStream os=new FileOutputStream("d:/a.jpg");
            IOUtils.copy(is,os);
        }
    }

    public static void main(String[] args) throws Exception{
        demo_mp3 dm=new demo_mp3();
        dm.fun_1();
        dm.fun_2();
    }
}
