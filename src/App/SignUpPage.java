package App;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SignUpPage {

	JFrame frame;
	private JTextField txtUserName;
	private JTextField txtPassWord;
	private JTextField txtEmail;
	private JTextField txtContact;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPage window = new SignUpPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	/**
	 * Create the application.
	 */
	public SignUpPage() {
		initialize();
	}

	
	
	
	
	
	
	//Validation methode 
	public boolean validiteSignUp() {
		String name = txtUserName.getText();
		String password = txtPassWord.getText();
		String email = txtEmail.getText();
		String contact = txtContact.getText();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "plz enter user name");
			return false;
		}if (password.equals("") || password.length()< 6) {
			JOptionPane.showMessageDialog(null, "plz enter password with 6 cara ");
			return false;
		}if (email.equals("")) {
			JOptionPane.showMessageDialog(null, "plz enter email ");
			return false;
		}if (contact.equals("")) {
			JOptionPane.showMessageDialog(null, "plz enter contact ");
			return false;
		}
		
		return true;
		
		
	}
	
	//check duplicate users 
	public boolean checkDuplicateUser() {
		String name = txtUserName.getText();
		boolean isexist = false;
		try {
			Connection con =  DBConnection.connect();
			PreparedStatement pst= con.prepareStatement("select * from users where user_name = ?");
			pst.setString(1, name);
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				isexist = true ;	
			}else {
				isexist = false;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isexist;
	}
	
	
	
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setBounds(0, 0, 1001, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 434, 600);
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
		panel_1.setBounds(433, 0, 568, 600);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(60, 113, 49, 14);
		panel_1.add(lblNewLabel);
		
		txtUserName = new JTextField();
		txtUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (checkDuplicateUser() == true) {
					JOptionPane.showMessageDialog(null, "ce nom est déja exist");
				}
				
			}
		});
		txtUserName.setBounds(134, 134, 272, 47);
		panel_1.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(60, 206, 49, 14);
		panel_1.add(lblPassword);
		
		txtPassWord = new JTextField();
		txtPassWord.setColumns(10);
		txtPassWord.setBounds(134, 225, 272, 47);
		panel_1.add(txtPassWord);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(60, 289, 49, 14);
		panel_1.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(134, 308, 272, 47);
		panel_1.add(txtEmail);
		
		JLabel lblNewLabel_1_1 = new JLabel("contact");
		lblNewLabel_1_1.setBounds(60, 382, 49, 14);
		panel_1.add(lblNewLabel_1_1);
		
		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(134, 405, 272, 47);
		panel_1.add(txtContact);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validiteSignUp() == true) {
					if (checkDuplicateUser() == false) {
						try {
							Connection con = DBConnection.connect();
							PreparedStatement pst= con.prepareStatement("insert into users (user_name, user_password, user_email, user_contact) values (?,?,?,?)");
							pst.setString(1, txtUserName.getText());
							pst.setString(2, txtPassWord.getText());
							pst.setString(3, txtEmail.getText());
							pst.setString(4, txtContact.getText());
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "succesfull");
							LoginPage loginPage = new LoginPage();
							loginPage.frame.setVisible(true);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null, "ce nom est déja exist  il faut le changer");
					}
					
				}
				
				
			}
		});
		btnNewButton.setBounds(228, 497, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				loginPage.frame.setVisible(true);

			}
		});
		btnNewButton_1.setBounds(228, 550, 89, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("SIGN UP");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(202, 11, 169, 77);
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
	}
}
