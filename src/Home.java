import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

public class Home extends JFrame {
    private JPanel home;
    private JMenuItem exit;
    private JMenuItem dangki;
    private JMenuBar menuBar;
    private JMenuItem update;
    private JButton go;
    private JTextField maCB;
    private JLabel idCB;
    private JLabel ns;
    private JLabel name;
    private JLabel addr;
    private JLabel luong;
    private JLabel thuong;
    private JLabel phat;
    private JLabel tong;

    public Home(){
        add(home);
        setTitle("Chương trình quản lí lương CBGV");
        setSize(400, 400);
        setVisible(true);
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection conn = new DBConnection();
                Locale localeEN = new Locale("en", "EN");
                NumberFormat en = NumberFormat.getInstance(localeEN);
                int id = Integer.parseInt(maCB.getText());
                try {
                    if(conn.isExist(id)){
                        idCB.setText("Mã cán bộ: "+id);
                        name.setText("Họ và tên: " + conn.info(id).getString("name"));
                        ns.setText("Năm sinh: "+conn.info(id).getString("dob"));
                        addr.setText("Quê quán: "+ conn.info(id).getString("address"));
                        luong.setText("Lương cứng: "+en.format(conn.info(id).getInt("luong")) + " VNĐ");
                        thuong.setText("Lương thưởng: "+en.format(conn.info(id).getInt("thuong")) +" VNĐ");
                        phat.setText("Phạt: "+en.format(conn.info(id).getInt("phat"))+" VNĐ");
                        tong.setText("Tổng thực lĩnh: "+en.format(+conn.info(id).getInt("tong"))+" VNĐ");
                    }else{
                        JOptionPane.showMessageDialog(null, "Cán bộ không tồn tại !");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        Home home = new Home();
        home.setVisible(true);
    }

}
