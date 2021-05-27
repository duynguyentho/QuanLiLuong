import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class Login extends JFrame {
    private JTextField username;
    private JButton login;
    private JPanel LoginPanel;
    private JPasswordField password;
    private JButton register;
    private JCheckBox check;
    private QLGVForm qlgv;

    public Login() throws SQLException {
        add(LoginPanel);
        setSize(300,300);
        setTitle("Login Form");
        DataInput dip = new DataInput();
        String input = dip.Input().equals("-") ? "" : dip.Input();
        if(!input.equals("")){
            String [] arr = dip.Input().split("-");
            String usrRemember = arr[0];
            String pwdRemember = arr[1];
            System.out.println(pwdRemember);
            username.setText(usrRemember);
            password.setText(pwdRemember);
            check.setSelected(true);
        }
        
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpForm signUpForm = new SignUpForm();
                signUpForm.setVisible(true);
            }
        });
    }
    public  void doLogin(){
//        validator
        String usr = username.getText();
        String pwd = new String(password.getPassword());
        if(usr == "" || pwd == ""){
            JOptionPane.showMessageDialog(null, "Username or Password require !");
        }
        DBConnection conn = new DBConnection();
        try{
            if(conn.Login(usr, pwd)){
                System.out.println("Đăng nhập thành công !");
                conn.setTime(usr, new Date().getTime());
                if(check.isSelected()){
                    DataOutput dop = new DataOutput();
                    dop.Output(usr, pwd);
                }
                else{
                    DataOutput dop = new DataOutput();
                    dop.Output("", "");
                }
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                QLGVForm qlgv = new QLGVForm();
                qlgv.setVisible(true);
                this.dispose();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
