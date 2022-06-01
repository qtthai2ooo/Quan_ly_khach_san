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

public class Phong extends JFrame implements ActionListener {
	
	Connection conn;
	Statement stm;
	ResultSet rst;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTextField timkiem;
	Vector vData=new Vector();
	Vector vTitle=new Vector();
	private JTextField jTextFieldTENPHONG;
	private JTextField jTextFieldLOAIPHONG;
	private JTextField jTextFieldMAPHONG;
	private JTextField jTextFieldGIAPHONG;
	private JTextField jTextFieldCHUTHICHP;
	private JTextField jTextFieldMANVP;
	private JTextField jTextFieldMADVP;
	int selectedrow =0 ;
	JTable tb = new JTable();
	String gioitinh;
	String item;
	String tim;
	String[] timtheo = {"MAPHONG","TENPHONG","GIAPHONG"};
	private JTextField jTextFieldTINHTRANG;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				 new Phong("Phòng");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Phong(String s) {
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
		
		JLabel lblQunLSinh = DefaultComponentFactory.getInstance().createTitle("Quản lý phòng khách sạn");
		lblQunLSinh.setForeground(Color.RED);
		lblQunLSinh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblQunLSinh.setBounds(539, 11, 326, 52);
		contentPane.add(lblQunLSinh);
		
		JLabel lblH = new JLabel("Tên Phòng: ");
		lblH.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblH.setBounds(10, 123, 98, 25);
		contentPane.add(lblH);
		
		JLabel lblId = new JLabel("Mã Phòng:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId.setBounds(10, 66, 111, 25);
		contentPane.add(lblId);
		
		JLabel lblNgySinh = new JLabel("Giá Phòng:");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgySinh.setBounds(10, 237, 98, 25);
		contentPane.add(lblNgySinh);
		
		JLabel lblaCh = new JLabel("Mã Nhân Viên:");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblaCh.setBounds(10, 408, 121, 25);
		contentPane.add(lblaCh);
		
		JLabel lblinThoi = new JLabel("Chú Thích:");
		lblinThoi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblinThoi.setBounds(10, 294, 98, 25);
		contentPane.add(lblinThoi);
		
		JLabel lblTnKhoa = new JLabel("Tình Trạng:");
		lblTnKhoa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTnKhoa.setBounds(10, 351, 98, 25);
		contentPane.add(lblTnKhoa);
		
		JLabel lblEmail = new JLabel("Mã Dịch Vụ:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(10, 464, 121, 25);
		contentPane.add(lblEmail);
		
		ButtonGroup nhomgioitinh = new ButtonGroup();
		
		jTextFieldTENPHONG = new JTextField();
		jTextFieldTENPHONG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldTENPHONG.setBounds(131, 124, 182, 25);
		contentPane.add(jTextFieldTENPHONG);
		jTextFieldTENPHONG.setColumns(10);
		
		jTextFieldLOAIPHONG = new JTextField();
		jTextFieldLOAIPHONG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldLOAIPHONG.setBounds(131, 181, 182, 25);
		contentPane.add(jTextFieldLOAIPHONG);
		jTextFieldLOAIPHONG.setColumns(10);
		
		jTextFieldMAPHONG = new JTextField();
		jTextFieldMAPHONG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldMAPHONG.setBounds(131, 66, 182, 25);
		contentPane.add(jTextFieldMAPHONG);
		jTextFieldMAPHONG.setColumns(10);
		
		jTextFieldGIAPHONG = new JTextField();
		jTextFieldGIAPHONG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldGIAPHONG.setBounds(131, 238, 182, 25);
		contentPane.add(jTextFieldGIAPHONG);
		jTextFieldGIAPHONG.setColumns(10);
		
		jTextFieldCHUTHICHP = new JTextField();
		jTextFieldCHUTHICHP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldCHUTHICHP.setBounds(131, 295, 182, 25);
		contentPane.add(jTextFieldCHUTHICHP);
		jTextFieldCHUTHICHP.setColumns(10);
		
		jTextFieldMANVP = new JTextField();
		jTextFieldMANVP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldMANVP.setBounds(131, 411, 182, 25);
		contentPane.add(jTextFieldMANVP);
		jTextFieldMANVP.setColumns(10);
		
		jTextFieldMADVP = new JTextField();
		jTextFieldMADVP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldMADVP.setBounds(131, 464, 182, 25);
		contentPane.add(jTextFieldMADVP);
		jTextFieldMADVP.setColumns(10);
		
		
		
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
					String sql ="SELECT * FROM phong where `MAPHONG` = '"+click+"'";
					PreparedStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) 
					{
						jTextFieldMAPHONG.setText(String.valueOf(rs.getString("MAPHONG")));
						jTextFieldTENPHONG.setText(String.valueOf(rs.getString("TENPHONG")));
						jTextFieldGIAPHONG.setText(String.valueOf(rs.getString("GIAPHONG")));
						jTextFieldMANVP.setText(String.valueOf(rs.getString("MANV")));
						jTextFieldMADVP.setText(String.valueOf(rs.getString("MADV")));
						jTextFieldLOAIPHONG.setText(String.valueOf(rs.getString("LOAIPHONG")));
						jTextFieldCHUTHICHP.setText(String.valueOf(rs.getString("CHUTHICH")));
						jTextFieldTINHTRANG.setText(String.valueOf(rs.getString("TINHTRANG")));
					
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
		
		JLabel lblLoiPhng = new JLabel("Loại Phòng: ");
		lblLoiPhng.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLoiPhng.setBounds(10, 180, 98, 25);
		contentPane.add(lblLoiPhng);
		
		jTextFieldTINHTRANG = new JTextField();
		jTextFieldTINHTRANG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jTextFieldTINHTRANG.setColumns(10);
		jTextFieldTINHTRANG.setBounds(131, 356, 182, 25);
		contentPane.add(jTextFieldTINHTRANG);
		
	

		setBounds(100, 100, 1130, 632);

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
			ResultSet rst= stm.executeQuery("SELECT * FROM qlsv where `"+tim+"` like '%"+timkiem.getText().trim()+"%'");
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
			stm.executeUpdate("INSERT INTO phong(MAPHONG,TENPHONG, LOAIPHONG, GIAPHONG, CHUTHICH, TINHTRANG, MANV, MADV) VALUES('" + jTextFieldMAPHONG.getText() + "','" + jTextFieldTENPHONG.getText() + "','" + jTextFieldLOAIPHONG.getText() + "', '" + jTextFieldGIAPHONG.getText() + "', '" + jTextFieldCHUTHICHP.getText() + "', '" + jTextFieldTINHTRANG.getText() + "', '" + jTextFieldMANVP.getText() + "', '" + jTextFieldMADVP.getText() + "')");
			JOptionPane.showMessageDialog(this, "Thêm bảng thành công.", "thông báo", JOptionPane.INFORMATION_MESSAGE);
			bang();
			model.fireTableDataChanged();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Không thể thêm bảng.", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	public void sua() 
	{
		try {
			stm.executeUpdate("UPDATE phong SET `MAPHONG` = '"+jTextFieldMAPHONG.getText()+"', `TENPHONG` = '"+jTextFieldTENPHONG.getText()+"', `LOAIPHONG` = '"+jTextFieldLOAIPHONG.getText()+"', `GIAPHONG` = '"+jTextFieldGIAPHONG.getText()+"', `CHUTHICH` = '"+jTextFieldCHUTHICHP.getText()+"', `TINHTRANG` = '"+jTextFieldTINHTRANG.getText()+"', `MANV` = '"+jTextFieldMANVP.getText()+"', `MADV` = '"+jTextFieldMADVP.getText()+"' WHERE (`MAPHONG` = '"+jTextFieldMAPHONG.getText()+"')");
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
		stm.executeUpdate("DELETE FROM phong WHERE MAPHONG = '" + jTextFieldMAPHONG.getText() + "'");
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
			ResultSet rst= stm.executeQuery("SELECT * FROM phong ");
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
