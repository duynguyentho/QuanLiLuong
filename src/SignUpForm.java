import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpForm extends JFrame{
    private JTextField username;
    private JTextField phoneNumber;
    private JButton signUpBtn;
    private JPasswordField password;
    private JPasswordField passwordConfirmed;
    private JPanel SignUpForm;
    private boolean a;

    public SignUpForm(){
        add(SignUpForm);
        setTitle("Đăng kí tài khoản");
        setSize(500,700);
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });
    }
    public void signUp(){
        String usr = username.getText();
        String pwd = new String(password.getPassword());
        String pwdConfirmed = new String(passwordConfirmed.getPassword());
        String phone = phoneNumber.getText();
        DBConnection conn = new DBConnection();
        if(validate(usr, pwd, pwdConfirmed, phone)){
            try{
                if(conn.checkSignup(usr, pwd, phone)){
                    if(conn.signup(usr, pwd, phone)){
                        System.out.println("Đăng kí thành công");
                        JOptionPane.showMessageDialog(null,"Đăng kí thành công");
                        this.dispose();
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    public boolean validate(String usr, String pwd, String pwdcf, String phone){
        if(usr.equals("") || pwd.equals("") || pwdcf.equals("") || phone.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }
        if(!pwdcf.equals(pwd)){
            JOptionPane.showMessageDialog(null, "Mật khẩu xác thực không khớp");
            return false;
        }
        if(isNumeric(phone)){
            if(phone.length() != 10){
                System.out.println(phone.length());
                JOptionPane.showMessageDialog(null, "Số điện thoại bao gồm 10 số !");
                return false;
            }
            else{
                String regex = "^09\\d{8}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(phone);
                if(!matcher.find()){
                    JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ !");
                }
            }
        }
        return true;
    }
    public boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
