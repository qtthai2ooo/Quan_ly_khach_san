package dean;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;

public class Dang_Nhap extends JFrame implements ActionListener 
{
	JLabel user = new JLabel("Username:");
	JTextField userlb = new JTextField();
	JLabel pass = new JLabel("Password:");
	JPasswordField passlb = new JPasswordField();
	Connection con;
	Statement stm;
	ResultSet rst;
	JButton ok = new JButton("Đăng nhập");
	JButton cancel = new JButton("Cancel");

	public Dang_Nhap(String title)
	{
		super(title);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/sightup","root","181035");
			stm = con.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Container c1 = this.getContentPane();
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		user.setBounds(68, 136, 112, 19);
		user.setFont(new Font("Tahoma", Font.BOLD, 17));
		p1.add(user);
		userlb.setBounds(202, 134, 165, 25);
		userlb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		p1.add(userlb);
		pass.setBounds(68, 180, 112, 19);
		pass.setFont(new Font("Tahoma", Font.BOLD, 17));
		p1.add(pass);
		passlb.setBounds(202, 178, 165, 25);
		passlb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		p1.add(passlb);
		c1.add(p1);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Quản lý khách sạn");
		lblNewJgoodiesTitle.setForeground(Color.CYAN);
		lblNewJgoodiesTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 45));
		lblNewJgoodiesTitle.setBounds(75, 11, 354, 53);
		p1.add(lblNewJgoodiesTitle);
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		ok.setFont(new Font("Tahoma", Font.PLAIN, 15));
		p2.add(ok);
		ok.addActionListener(this);
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		p2.add(cancel);
		cancel.addActionListener(this);
		c1.add(p2,"South");
		setSize(486,348);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("Đăng nhập")) 
		{ 
			try {
				rst = stm.executeQuery("select * from sightup.dangnhap where Username = n'"+userlb.getText()+"' and Password = n'"+new String(passlb.getPassword())+"';");
		
				 if (rst != null) {
                     if (rst.next()) {
                         if (rst.getString("Password").equals(new String(passlb.getPassword()))) {
                             JOptionPane.showMessageDialog(this, "You has been login successful.", "login successfuly", JOptionPane.INFORMATION_MESSAGE);
                             new Menu();
                             this.dispose();
                         } else {
                             JOptionPane.showMessageDialog(this, "Password is match case.", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                         }
                     } else {
                         JOptionPane.showMessageDialog(this, "Wrong username or password.", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                     }
                 }}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else 
		{
			this.dispose();
		}
	}



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				 new Dang_Nhap("Đăng nhập");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

	
