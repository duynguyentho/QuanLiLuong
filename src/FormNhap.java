import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FormNhap extends JFrame {
    private JPanel FormNhap;
    private JButton add;
    private JTextField address;
    private JComboBox namsinh;
    private JTextField name;
    private JTextField luong;
    private JTextField thuong;
    private JTextField phat;

    public FormNhap(){
        setTitle("Nhập dữ liệu");
        setSize(500,500);
        add(FormNhap);
        for(int i=1950;i<1990;i++){
            namsinh.addItem(i);
        }
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(QLGVForm.action == "add"){
                    try {
                        Nhap();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }
    public void Nhap() throws SQLException {
        double luongCung = Double.parseDouble(luong.getText());
        double luongThuong = Double.parseDouble(thuong.getText());
        double luongPhat = Double.parseDouble(phat.getText());
        int dob = Integer.parseInt(namsinh.getSelectedItem().toString());
        CBGV cb = new CBGV(name.getText(),dob, address.getText(), luongCung, luongThuong, luongPhat);
        DBConnection conn = new DBConnection();
        Connection c = conn.getConnection();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO `QLGV`.`CBGV` (`name`, `dob`, `address`, `luong`, `thuong`, `phat`,`tong`) VALUES ( '"+
                    cb.name+"', '"+
                    cb.dob+"', '"+
                    cb.address+"', '"+
                    cb.luongCung+"', '"+
                    cb.thuong+"', '"+
                    cb.phat+"', '"+
                    cb.luongThucLinh()+"');";
            System.out.println(sql);
             stmt.executeUpdate(sql);
             QLGVForm qlgv = new QLGVForm();
             qlgv.Load();
             qlgv.setVisible(true);
             this.dispose();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
