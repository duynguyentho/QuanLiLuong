import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Arrays;

public class DBConnection {
    private static String url = "jdbc:mysql://localhost:3306/QLGV";
    private static String username = "root";
    private static String password = "admin";
    private static Connection conn;

    public static Connection getConnection() {
        conn = null;
        Properties properties = new Properties();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối thành công");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public static boolean Login(String username, String password) throws SQLException {
        Connection c = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        rs = stmt.executeQuery("SELECT password FROM QLGV.users WHERE username = '" + username + "';");
        while (rs.next()){
            if (rs.getString("password").equals(password))
                return true;
        }
        conn.close();
        return false;
    }
    public static boolean checkSignup(String username, String password, String phoneNumber) throws SQLException {
        Connection c = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        rs = stmt.executeQuery("SELECT username FROM QLGV.users WHERE username = '" + username + "';");
        while(rs.next()){
            if(rs.getString("username").equals(username)){
                JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
                return false;
            }
        }
        return true;
        }
        public static boolean signup(String username, String password, String phone){
            Connection c = getConnection();
            Statement stmt = null;
            try {
                stmt = c.createStatement();
                String sql = "INSERT INTO QLGV.users SET username = '"+
                        username+"', password = '"+
                        password+"', phone = '"+
                        phone+"'";
                System.out.println(sql);
                stmt.executeUpdate(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
            return true;
        }
    public static boolean checkAccount(String username, String phone) throws SQLException {
        Connection c = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        Long date = new Date().getTime();
        rs = stmt.executeQuery("SELECT password FROM QLGV.users WHERE username = '" + username + "';");
        while (rs.next()) {
            if (rs.getString("password").equals(password)) {
                return true;
            }
        }
        return false;
    }
    public static void  setTime(String username, long time) throws SQLException {
        Connection c = getConnection();
        Statement stmt = conn.createStatement();
         stmt.executeUpdate("UPDATE QLGV.users SET logintime = '"+
                time+"' WHERE username = '"+username+"'");
    }
    public static boolean isExist(int id) throws SQLException {
        Connection c = getConnection();
        ResultSet rs = null;
        try{
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM QLGV.CBGV WHERE idCBGV = '" + id + "'";
            rs =stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getRow() > 0){
                    return true;
                }
                return false;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public static ResultSet info(int id){
        Connection c = getConnection();
        ResultSet rs = null;
        try{
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM QLGV.CBGV WHERE idCBGV = '" + id + "'";
            rs =stmt.executeQuery(sql);
            rs.next();
        }catch(Exception e){
            System.out.println(e);
        }
        return rs;
    }
}
