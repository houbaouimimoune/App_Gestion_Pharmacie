package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionAdmin {

	JFrame frame;
	private JTextField txtNom;
	private JTextField txtpdw;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAdmin window = new GestionAdmin();
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
	public GestionAdmin() {
		initialize();
	}

	
	String adminName,adminEmail,adminPassword;
	
	//add admin 
			public boolean add() {
				boolean isadd = false;
				
				adminName = txtNom.getText();
				adminPassword = txtpdw.getText();
				adminEmail = txtEmail.getText();
				
				try {
					Connection con = DBConnection.connect();
					PreparedStatement pst = con.prepareStatement("insert into admin (admin_name, admin_password,admin_email) values (?,?,?)");
					pst.setString(1, adminName);
					pst.setString(2, adminPassword);
					pst.setString(3, adminEmail);
					
					int rownbr = pst.executeUpdate();
					if (rownbr > 0) {
						isadd = true;
					}else {
						isadd = false;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return isadd;
			}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setBounds(0, 0, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 1000, 107);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ajouter un admin ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(433, 27, 329, 41);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("X");
		lblNewLabel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblNewLabel_1_2.setBackground(Color.RED);
		lblNewLabel_1_2.setBounds(951, 0, 49, 47);
		panel.add(lblNewLabel_1_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				HomePageAdmin homePageAdmin = new HomePageAdmin();
				homePageAdmin.frame.setVisible(true);
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
		panel_1.setBounds(0, 108, 1000, 55);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("le nom de l'admin ");
		lblNewLabel.setBounds(81, 263, 96, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPasswrd = new JLabel("password");
		lblPasswrd.setBounds(365, 263, 83, 14);
		frame.getContentPane().add(lblPasswrd);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(709, 263, 83, 14);
		frame.getContentPane().add(lblEmail);
		
		txtNom = new JTextField();
		txtNom.setBounds(208, 260, 96, 20);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtpdw = new JTextField();
		txtpdw.setColumns(10);
		txtpdw.setBounds(477, 260, 96, 20);
		frame.getContentPane().add(txtpdw);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(802, 260, 154, 20);
		frame.getContentPane().add(txtEmail);
		
		JButton btnAjouterAdmin = new JButton("Ajouter l'admin");
		btnAjouterAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(add() == true) {
					JOptionPane.showMessageDialog(null, "vous avez ajouté ");
					}else {
						JOptionPane.showMessageDialog(null, "error d'ajout");
					}
			}
		});
		btnAjouterAdmin.setBounds(365, 402, 206, 41);
		frame.getContentPane().add(btnAjouterAdmin);
	}
}
