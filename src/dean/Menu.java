package dean;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 new Menu();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDchV = new JButton("D\u1ECBch v\u1EE5");
		btnDchV.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDchV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDchV.setIcon(new ImageIcon("C:\\Users\\Min\\Downloads\\hinh\\sssssasasas.png"));
		btnDchV.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent arg0) {
			 new Dich_vu("Dịch Vụ");
			}
		});
		btnDchV.setBounds(34, 79, 195, 63);
		contentPane.add(btnDchV);
		
		JButton btnHon = new JButton("Ho\u00E1 \u0110\u01A1n");
		btnHon.setIcon(new ImageIcon("C:\\Users\\Min\\Downloads\\hinh\\âsasasasasa.png"));
		btnHon.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				new Hoa_Don("Hoá Đơn");
			}
		});
		btnHon.setBounds(255, 79, 182, 63);
		contentPane.add(btnHon);
		
		JButton btnNewButton = new JButton("Kh\u00E1ch H\u00E0ng");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Min\\Downloads\\hinh\\if_profile_3018530 (1).png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				new Khach_Hang("Khách Hàng");
			}
		});
		btnNewButton.setBounds(34, 165, 195, 63);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Nh\u00E2n Vi\u00EAn");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Min\\Downloads\\hinh\\enploy.png"));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				new Nhan_Vien("Nhân Viên");
			}
		});
		btnNewButton_1.setBounds(255, 165, 182, 63);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Phòng");
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\Min\\Downloads\\hinh\\qqqqq.png"));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				new Phong("Phòng");
			}
		});
		btnNewButton_2.setBounds(152, 239, 182, 63);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Menu");
		lblNewJgoodiesTitle.setFont(new Font("Script MT Bold", Font.BOLD | Font.ITALIC, 44));
		lblNewJgoodiesTitle.setBounds(162, 11, 143, 45);
		contentPane.add(lblNewJgoodiesTitle);
		
		this.setVisible(true);
	}
}
