package dean;


import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;

public class Khach_Hang extends JFrame implements ActionListener {
	
	Connection conn;
	Statement stm;
	ResultSet rst;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTextField timkiem;
	Vector vData=new Vector();
	Vector vTitle=new Vector();
	private JTextField jTextFieldTENKH;
	private JTextField jTextFieldQUOCTICH;
	private JTextField jTextFieldMAKH;
	private JTextField jTextFieldGIOITINHKH;
	private JTextField jTextFieldSDT;
	private JTextField jTextFieldMAPHONGKH;
	int selectedrow =0 ;
	JTable tb = new JTable();
	String gioitinh;
	String item;
	String tim;
	String[] timtheo = {"MAKH","TENKH"};
	private JTextField jTextFieldCMND;
	private JTextField jTextFieldTUOI;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				 new Khach_Hang("Khách Hàng");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Khach_Hang(String s) {
		super(s);
		try {
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QL_KHACHSAN", "root", "181035");
		stm = conn.createStatement();

		bang();
		model = new DefaultTableModel(vData, vTitle);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		
		JButton xoa = new JButton("Xo\u00E1");
		xoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		xoa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
			   xoa();
			}
		});
		xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		xoa.setBounds(10, 543, 89, 25);
		contentPane.add(xoa);
		
		timkiem = new JTextField();
		timkiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		timkiem.setBounds(361, 550, 455, 25);
		contentPane.add(timkiem);
		timkiem.setColumns(10);
		
		JComboBox cbtimkiem = new JComboBox(timtheo);
		cbtimkiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbtimkiem.setBounds(826, 550, 133, 25);
		contentPane.add(cbtimkiem);
		
		JLabel lblQunLSinh = DefaultComponentFactory.getInstance().createTitle("Quản lý thông tin khách hàng");
		lblQunLSinh.setForeground(Color.RED);
		lblQunLSinh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblQunLSinh.setBounds(520, 11, 326, 52);
		contentPane.add(lblQunLSinh);
		
		JLabel lblH = new JLabel("Tên Khách Hàng: ");
		lblH.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblH.setBounds(10, 115, 139, 25);
		contentPane.add(lblH);
		
		JLabel lblId = new JLabel("Mã Khách Hàng:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId.setBounds(10, 65, 129, 25);
		contentPane.add(lblId);
		
		JLabel lblNgySinh = new JLabel("Quốc Tịch:");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgySinh.setBounds(10, 215, 98, 25);
		contentPane.add(lblNgySinh);
		
		JLabel lblaCh = new JLabel("Số Điện Thoại:");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblaCh.setBounds(10, 365, 129, 25);
		contentPane.add(lblaCh);
		
		JLabel lblTnKhoa = new JLabel("Giới Tính:");
		lblTnKhoa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTnKhoa.setBounds(10, 265, 76, 25);
		contentPane.add(lblTnKhoa);
		
		JLabel lblEmail = new JLabel("Mã Phòng:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(10, 415, 129, 25);
		contentPane.add(lblEmail);
		
		ButtonGroup nhomgioitinh = new ButtonGroup();
		
		jTextFieldTENKH = new JTextField();
		jTextFieldTENKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldTENKH.setBounds(149, 116, 182, 25);
		contentPane.add(jTextFieldTENKH);
		jTextFieldTENKH.setColumns(10);
		
		jTextFieldQUOCTICH = new JTextField();
		jTextFieldQUOCTICH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldQUOCTICH.setBounds(149, 216, 182, 25);
		contentPane.add(jTextFieldQUOCTICH);
		jTextFieldQUOCTICH.setColumns(10);
		
		jTextFieldMAKH = new JTextField();
		jTextFieldMAKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldMAKH.setBounds(149, 67, 182, 25);
		contentPane.add(jTextFieldMAKH);
		jTextFieldMAKH.setColumns(10);
		
		jTextFieldGIOITINHKH = new JTextField();
		jTextFieldGIOITINHKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldGIOITINHKH.setBounds(149, 266, 182, 25);
		contentPane.add(jTextFieldGIOITINHKH);
		jTextFieldGIOITINHKH.setColumns(10);
		
		jTextFieldSDT = new JTextField();
		jTextFieldSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldSDT.setBounds(149, 366, 182, 25);
		contentPane.add(jTextFieldSDT);
		jTextFieldSDT.setColumns(10);
		
		jTextFieldMAPHONGKH = new JTextField();
		jTextFieldMAPHONGKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldMAPHONGKH.setBounds(149, 416, 182, 25);
		contentPane.add(jTextFieldMAPHONGKH);
		jTextFieldMAPHONGKH.setColumns(10);
		
		
		
		JButton them = new JButton("Th\u00EAm");
		them.setFont(new Font("Tahoma", Font.PLAIN, 15));
		them.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				them();
			}
		});
		them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		JButton sua = new JButton("S\u1EEDa");
		sua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sua.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sua();
			}
		});
		
		JButton timkiem_1 = new JButton("T\u00ECm ki\u1EBFm");
		timkiem_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		timkiem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tim = cbtimkiem.getSelectedItem().toString();
				timkiem();
			}
		});
		timkiem_1.setBounds(975, 550, 107, 25);
		contentPane.add(timkiem_1);
		sua.setBounds(131, 545, 89, 25);
		contentPane.add(sua);
		them.setBounds(254, 545, 89, 25);
		contentPane.add(them);
		
		JPanel panel = new JPanel();
		panel.setBounds(361, 74, 721, 460);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		tb = new JTable(model);
		tb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QL_KHACHSAN", "root", "181035");
					stm = conn.createStatement();
					int row = tb.getSelectedRow();
					String click = (tb.getModel().getValueAt(row, 0).toString());
					String sql ="SELECT * FROM khachhang where `MAKH` = '"+click+"'";
					PreparedStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) 
					{
						jTextFieldMAKH.setText(String.valueOf(rs.getString("MAKH")));
						jTextFieldTENKH.setText(String.valueOf(rs.getString("TENKH")));
						jTextFieldSDT.setText(String.valueOf(rs.getString("SDT")));
						jTextFieldTUOI.setText(String.valueOf(rs.getString("TUOI")));
						jTextFieldMAPHONGKH.setText(String.valueOf(rs.getString("MAPHONG")));
						jTextFieldQUOCTICH.setText(String.valueOf(rs.getString("QUOCTICH")));
						jTextFieldGIOITINHKH.setText(String.valueOf(rs.getString("GIOITINH")));
						jTextFieldCMND.setText(String.valueOf(rs.getString("CMND")));
					
					
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		panel.add(tb, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(tb);
		panel.add(scrollPane, BorderLayout.NORTH);
		
		JButton btnReset = new JButton("reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bang();
				model.fireTableDataChanged();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset.setBounds(993, 28, 89, 23);
		contentPane.add(btnReset);
		
		JLabel lblCmnd = new JLabel("CMND: ");
		lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCmnd.setBounds(10, 165, 98, 25);
		contentPane.add(lblCmnd);
		
		jTextFieldCMND = new JTextField();
		jTextFieldCMND.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldCMND.setColumns(10);
		jTextFieldCMND.setBounds(149, 166, 182, 25);
		contentPane.add(jTextFieldCMND);
		
		jTextFieldTUOI = new JTextField();
		jTextFieldTUOI.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldTUOI.setColumns(10);
		jTextFieldTUOI.setBounds(149, 316, 182, 25);
		contentPane.add(jTextFieldTUOI);
		
		JLabel lblTui = new JLabel("Tuổi:");
		lblTui.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTui.setBounds(10, 315, 76, 25);
		contentPane.add(lblTui);
		
		setBounds(100, 100, 1131, 644);

		} catch (Exception e) {
			// TODO: handle exception
		}
		this.setVisible(true);
	}
	public void timkiem() {
		try 
		{
			vTitle.clear();
			vData.clear();
			ResultSet rst= stm.executeQuery("SELECT * FROM khachhang where `"+tim+"` like '%"+timkiem.getText().trim()+"%'");
			ResultSetMetaData rstmeta = rst.getMetaData();
			int num_column = rstmeta.getColumnCount();
			for(int i=1;i<=num_column;i++) 
			{
				vTitle.add(rstmeta.getColumnLabel(i));
			}
			while(rst.next()) 
			{
				Vector row = new Vector(num_column);
				for(int i=1;i<=num_column;i++) 
				{
					row.add(rst.getString(i));
				}
				vData.add(row);
			}
			rst.close();
			model.fireTableDataChanged();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void them() 
	{
		try {
			stm.executeUpdate("INSERT INTO khachhang(MAKH,TENKH, CMND, QUOCTICH, GIOITINH, TUOI, SDT, MAPHONG) VALUES('" + jTextFieldMAKH.getText() + "','" + jTextFieldTENKH.getText() + "','" + jTextFieldCMND.getText() + "', '" + jTextFieldQUOCTICH.getText() + "', '" + jTextFieldGIOITINHKH.getText() + "', '" + jTextFieldTUOI.getText() +"', '" + jTextFieldSDT.getText() + "', '" + jTextFieldMAPHONGKH.getText() + "')");
			JOptionPane.showMessageDialog(this, "thêm bảng thành công.", "thông báo", JOptionPane.INFORMATION_MESSAGE);
			bang();
			model.fireTableDataChanged();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Không thể thêm bảng.", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	public void sua() 
	{
		try {
			stm.executeUpdate("UPDATE khachhang SET `MAKH` = '" + jTextFieldMAKH.getText() + "', `TENKH` = '"+jTextFieldTENKH.getText()+"', `CMND` = '"+jTextFieldCMND.getText()+"', `QUOCTICH` = '"+jTextFieldQUOCTICH.getText()+"', `GIOITINH` = '"+jTextFieldGIOITINHKH.getText()+"', `TUOI` = '"+jTextFieldTUOI.getText()+"', `SDT` = '"+jTextFieldSDT.getText()+"', `MAPHONG` = '"+ jTextFieldMAPHONGKH.getText()+"' WHERE (`MAKH` = '"+jTextFieldMAKH.getText()+"')");
			JOptionPane.showMessageDialog(this, "Sửa bảng thành công.", "thông báo", JOptionPane.INFORMATION_MESSAGE);
			bang();
			model.fireTableDataChanged();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Không thể sửa bảng.", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	public void xoa() 
	{
	  try {
		stm.executeUpdate("DELETE FROM khachhang WHERE MAKH = '" + jTextFieldMAKH.getText() + "'");
		JOptionPane.showMessageDialog(this, "Xoá thành công.", "thông báo", JOptionPane.INFORMATION_MESSAGE);
		bang();
		model.fireTableDataChanged();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		JOptionPane.showMessageDialog(this, "Không thể xoá.", "Lỗi", JOptionPane.INFORMATION_MESSAGE);

	}
	}
	
	public void bang() {

		try 
		{
			vTitle.clear();
			vData.clear();
			ResultSet rst= stm.executeQuery("SELECT * FROM khachhang ");
			ResultSetMetaData rstmeta = rst.getMetaData();
			int num_column = rstmeta.getColumnCount();
			for(int i=1;i<=num_column;i++) 
			{
				vTitle.add(rstmeta.getColumnLabel(i));
			}
			while(rst.next()) 
			{
				Vector row = new Vector(num_column);
				for(int i=1;i<=num_column;i++) 
				{
					row.add(rst.getString(i));
				}
			vData.add(row);
			}
			rst.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
