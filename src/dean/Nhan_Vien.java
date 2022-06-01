package dean;


import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

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

public class Nhan_Vien extends JFrame implements ActionListener {
	
	Connection conn;
	Statement stm;
	ResultSet rst;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTextField timkiem;
	Vector vData=new Vector();
	Vector vTitle=new Vector();
	private JTextField jTextFieldTENNV;
	private JTextField jTextFieldLUONG;
	private JTextField jTextFieldMANV;
	private JTextField jTextFieldNGAYSINH;
	private JTextField jTextFieldGIOITINH;
	int selectedrow =0 ;
	JTable tb = new JTable();
	String tim;
	String[] timtheo = {"MANV","TENNV"};
	private JTextField jTextFieldCHUCVU;
	private JTextField jTextFieldCHUTHICH;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				 new Nhan_Vien("Nhân Viên");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Nhan_Vien(String s) {
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
		xoa.setBounds(10, 550, 89, 25);
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
		
		JLabel lblQunLSinh = DefaultComponentFactory.getInstance().createTitle("Quản lý thông tin nhân viên");
		lblQunLSinh.setForeground(Color.RED);
		lblQunLSinh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblQunLSinh.setBounds(533, 11, 326, 52);
		contentPane.add(lblQunLSinh);
		
		JLabel lblH = new JLabel("Tên Nhân Viên: ");
		lblH.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblH.setBounds(10, 121, 139, 25);
		contentPane.add(lblH);
		
		JLabel lblId = new JLabel("Mã Nhân Viên:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId.setBounds(10, 73, 129, 25);
		contentPane.add(lblId);
		
		JLabel lblNgySinh = new JLabel("Lương:");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgySinh.setBounds(10, 217, 98, 25);
		contentPane.add(lblNgySinh);
		
		JLabel lblinThoi = new JLabel("Ngày Sinh:");
		lblinThoi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblinThoi.setBounds(10, 265, 98, 25);
		contentPane.add(lblinThoi);
		
		JLabel lblTnKhoa = new JLabel("Giới Tính:");
		lblTnKhoa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTnKhoa.setBounds(10, 313, 76, 25);
		contentPane.add(lblTnKhoa);
		
		ButtonGroup nhomgioitinh = new ButtonGroup();
		
		jTextFieldTENNV = new JTextField();
		jTextFieldTENNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldTENNV.setBounds(149, 122, 182, 25);
		contentPane.add(jTextFieldTENNV);
		jTextFieldTENNV.setColumns(10);
		
		jTextFieldLUONG = new JTextField();
		jTextFieldLUONG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldLUONG.setBounds(149, 218, 182, 25);
		contentPane.add(jTextFieldLUONG);
		jTextFieldLUONG.setColumns(10);
		
		jTextFieldMANV = new JTextField();
		jTextFieldMANV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldMANV.setBounds(149, 74, 182, 25);
		contentPane.add(jTextFieldMANV);
		jTextFieldMANV.setColumns(10);
		
		jTextFieldNGAYSINH = new JTextField();
		jTextFieldNGAYSINH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldNGAYSINH.setBounds(149, 266, 182, 25);
		contentPane.add(jTextFieldNGAYSINH);
		jTextFieldNGAYSINH.setColumns(10);
		
		jTextFieldGIOITINH = new JTextField();
		jTextFieldGIOITINH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldGIOITINH.setBounds(149, 314, 182, 25);
		contentPane.add(jTextFieldGIOITINH);
		jTextFieldGIOITINH.setColumns(10);
		
		
		
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
		sua.setBounds(136, 550, 89, 25);
		contentPane.add(sua);
		them.setBounds(262, 550, 89, 25);
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
					String sql ="SELECT * FROM nhanvien where `MANV` = '"+click+"'";
					PreparedStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) 
					{
						jTextFieldMANV.setText(String.valueOf(rs.getString("MANV")));
						jTextFieldTENNV.setText(String.valueOf(rs.getString("TENNV")));
						jTextFieldNGAYSINH.setText(String.valueOf(rs.getString("NGAYSINH")));
						jTextFieldLUONG.setText(String.valueOf(rs.getString("LUONGNV")));
						jTextFieldGIOITINH.setText(String.valueOf(rs.getString("GIOITINH")));
						jTextFieldCHUTHICH.setText(String.valueOf(rs.getString("CHUTHICH")));
						jTextFieldCHUCVU.setText(String.valueOf(rs.getString("CHUCVU")));
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
		
		JLabel lblCmnd = new JLabel("Chức Vụ: ");
		lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCmnd.setBounds(10, 169, 98, 25);
		contentPane.add(lblCmnd);
		
		jTextFieldCHUCVU = new JTextField();
		jTextFieldCHUCVU.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldCHUCVU.setColumns(10);
		jTextFieldCHUCVU.setBounds(149, 170, 182, 25);
		contentPane.add(jTextFieldCHUCVU);
		
		jTextFieldCHUTHICH = new JTextField();
		jTextFieldCHUTHICH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldCHUTHICH.setColumns(10);
		jTextFieldCHUTHICH.setBounds(149, 362, 182, 25);
		contentPane.add(jTextFieldCHUTHICH);
		
		JLabel lblTui = new JLabel("Chú Thích:");
		lblTui.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTui.setBounds(10, 361, 115, 25);
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
			ResultSet rst= stm.executeQuery("SELECT * FROM nhanvien where `"+tim+"` like '%"+timkiem.getText().trim()+"%'");
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
			stm.executeUpdate("INSERT INTO NhanVien(MANV,TENNV,CHUCVU, LUONGNV, NGAYSINH, GIOITINH, CHUTHICH) VALUES('" + jTextFieldMANV.getText() + "','" + jTextFieldTENNV.getText() + "','" + jTextFieldCHUCVU.getText() + "','" + jTextFieldLUONG.getText() + "','" + jTextFieldNGAYSINH.getText() + "','" + jTextFieldGIOITINH.getText() + "','" + jTextFieldCHUTHICH.getText() + "')");
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
			stm.executeUpdate("UPDATE `ql_khachsan`.`nhanvien` SET `MANV` = '"+jTextFieldMANV.getText()+"', `TENNV` = '"+jTextFieldTENNV.getText()+"', `CHUCVU` = '"+jTextFieldCHUCVU.getText()+"', `LUONGNV` = '"+jTextFieldLUONG.getText()+"', `NGAYSINH` = '"+jTextFieldNGAYSINH.getText()+"', `GIOITINH` = '"+jTextFieldGIOITINH.getText()+"', `CHUTHICH` = '"+jTextFieldCHUTHICH.getText()+"' WHERE (`MANV` = '"+jTextFieldMANV.getText()+"')");
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
		stm.executeUpdate("DELETE FROM nhanvien WHERE `MANV` = '"+jTextFieldMANV.getText()+"'");
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
			ResultSet rst= stm.executeQuery("SELECT * FROM nhanvien ");
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
