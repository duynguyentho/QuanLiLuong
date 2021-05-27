import javax.swing.*;
import java.awt.event.*;
import javax.swing.JMenuBar;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Vector;
import java.text.NumberFormat;

public class QLGVForm extends JFrame {
    private JPanel QLGVForm;
    private JMenuItem exit;
    private JMenuItem ngv;
    private JMenuItem nluong;
    private JPanel tablePanel;
    private JScrollPane abc;
    JTable table1 ;
    private JMenuBar menuBar;
    private JButton delete;
    private JButton update;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtLuong;
    private JTextField txtThuong;
    private JTextField txtPhat;
    private JComboBox cbNam;
    private JLabel id;
    private JMenuItem logout;
    public static String action;
    Vector Col;
    public QLGVForm() throws SQLException {
        add(QLGVForm);
        setSize(1000, 1000);
        setTitle("Quản lí lương");
        for(int i=1950;i<2001;i++){
            cbNam.addItem(i);
        }
        createTable();
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitWindow();
            }
        });
        ngv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                action = "add";
                FormNhap nhap = new FormNhap();
                nhap.setVisible(true);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = table1.getSelectedRow();
                int id = Integer.parseInt(table1.getValueAt(row,0).toString());
                txtName.setText(table1.getValueAt(row,1).toString());
                txtAddress.setText(table1.getValueAt(row,3).toString());
                int Nam = Integer.parseInt(table1.getValueAt(row,2).toString());
                cbNam.setSelectedIndex(Nam - 1950);
                txtLuong.setText(table1.getValueAt(row,4).toString());
                txtThuong.setText(table1.getValueAt(row,5).toString());
                txtPhat.setText(table1.getValueAt(row,6).toString());
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = "update";
                store(action);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                int id = Integer.parseInt(table1.getValueAt(row,0).toString());
                DBConnection conn = new DBConnection();
                Connection c = conn.getConnection();
                Statement stmt = null;
                try {
                    stmt = c.createStatement();
                    String sql = "DELETE FROM `QLGV`.`CBGV` WHERE (`idCBGV` = '"+id+"');";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    Load();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Login login = new Login();
                    login.setVisible(true);
                    exitWindow();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }
    void store(String action){
        int row = table1.getSelectedRow();
        int id = Integer.parseInt(table1.getValueAt(row,0).toString());
        int nam = Integer.parseInt(cbNam.getSelectedItem().toString());
        double luongCung = Double.parseDouble(txtLuong.getText().replace(",",""));
        double luongThuong = Double.parseDouble(txtThuong.getText().replace(",",""));
        double luongPhat = Double.parseDouble(txtPhat.getText().replace(",",""));
        CBGV cb = new CBGV(txtName.getText(), nam, txtAddress.getText(), luongCung, luongThuong, luongPhat);
        DBConnection conn = new DBConnection();
        Connection c = conn.getConnection();
        Statement stmt = null;
        if(action == "update"){
            try {
                stmt = c.createStatement();
                String sql = "UPDATE `QLGV`.`CBGV` SET `name` = '"+
                        cb.name+"', `dob` = '"+
                        cb.dob+"', `address` = '"+
                        cb.address+"', `luong` = '"+
                        cb.luongCung+"', `thuong` = '"+
                        cb.thuong+"', `phat` = '"+
                        cb.phat+"', `tong` = '"+
                        cb.luongThucLinh()+"' WHERE (`idCBGV` = '"+
                        id+"');";
                System.out.println(sql);
                stmt.executeUpdate(sql);
                Load();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void exitWindow(){
        int result = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn thoát ?", "Swing Tester",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            this.dispose();
        }
    }
    public void createTable() throws SQLException {
        Col = new Vector();
        Col.addElement("Mã CB");
        Col.addElement("Họ tên");
        Col.addElement("Năm sinh");
        Col.addElement("Địa chỉ");
        Col.addElement("Lương");
        Col.addElement("Thưởng");
        Col.addElement("Phạt");
        Col.addElement("Thực lĩnh");
        Load();
    }
    public void exit(){
        this.dispose();
    }
    void Load() throws SQLException {
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        DBConnection conn = new DBConnection();
        String sql = "Select * from QLGV.CBGV";
        Connection c = conn.getConnection();
        Statement stmt = c.createStatement();
        try{
            ResultSet rs = stmt.executeQuery(sql);
            Vector Data = new Vector();
            while(rs.next()){
                Vector canbo = new Vector();
                canbo.addElement(rs.getInt("idCBGV"));
                canbo.addElement(rs.getString("name"));
                canbo.addElement(rs.getString("dob"));
                canbo.addElement(rs.getString("address"));
                canbo.addElement(en.format(rs.getInt("luong")));
                canbo.addElement(en.format(rs.getInt("thuong")));
                canbo.addElement(en.format(rs.getInt("phat")));
                canbo.addElement(en.format(rs.getInt("tong")));
                Data.addElement(canbo);
            }
            table1.setModel(new DefaultTableModel(Data,Col));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
