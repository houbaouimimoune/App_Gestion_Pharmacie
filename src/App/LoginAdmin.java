package App;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginAdmin {

	JFrame frame;
	private JTextField txtUserName;
	private JTextField txtPassWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAdmin window = new LoginAdmin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	static String name;
	
	/**
	 * Create the application.
	 */
	public LoginAdmin() {
		initialize();
	}


	//validation
	
	public boolean validiteLogin() {
		
		 name =  txtUserName.getText();
		String password = txtPassWord.getText();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "plz enter the user name");
			return false;
		}if (password.equals("")) {
			JOptionPane.showMessageDialog(null, "plz enter the password ");
			return false;
		}
		
		return true;
	}
	
	
	
	
	//verify login
	public void login() {
		try {
			
			
			Connection con = DBConnection.connect();
			PreparedStatement pst= con.prepareStatement("select * from admin where admin_name = ? and admin_password = ?");
			pst.setString(1, txtUserName.getText());
			pst.setString(2, txtPassWord.getText());
			name = txtUserName.getText();
			
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {			
				JOptionPane.showMessageDialog(null, "login successful");
				HomePageAdmin homePageAdmin = new HomePageAdmin();
				homePageAdmin.frame.setVisible(true);
				homePageAdmin.txtWelcome.setText(txtUserName.getText());
			}else {
				JOptionPane.showMessageDialog(null, "Incorrect user name or password");
			}
	
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setBounds(0, 0, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 434, 601);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			FirstPage firstPage = new FirstPage();
			firstPage.frame.setVisible(true);
			}
		});
		panel_2.setLayout(null);
		panel_2.setBackground(Color.RED);
		panel_2.setBounds(0, 0, 68, 27);
		panel.add(panel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Back");
		lblNewLabel_4.setBounds(10, 11, 49, 14);
		panel_2.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(64, 128, 128));
		panel_1.setBounds(432, 0, 568, 601);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(63, 146, 49, 14);
		panel_1.add(lblNewLabel);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(133, 172, 272, 47);
		panel_1.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(63, 252, 49, 14);
		panel_1.add(lblPassword);
		
		txtPassWord = new JTextField();
		txtPassWord.setColumns(10);
		txtPassWord.setBounds(133, 280, 272, 47);
		panel_1.add(txtPassWord);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validiteLogin()) {
					login();
				}
				
			}
		});
		btnNewButton_1.setBounds(162, 417, 204, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN ADMIN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(127, 0, 239, 77);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("X");
		lblNewLabel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1_2.setBackground(new Color(255, 0, 0));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblNewLabel_1_2.setBounds(519, 0, 49, 47);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblLoginToYour = new JLabel("login to your account");
		lblLoginToYour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoginToYour.setBounds(182, 68, 184, 40);
		panel_1.add(lblLoginToYour);
	}
}
