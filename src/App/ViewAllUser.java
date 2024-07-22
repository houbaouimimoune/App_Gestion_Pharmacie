package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class ViewAllUser {

	JFrame frame;
	private JTable tb_detailUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllUser window = new ViewAllUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	DefaultTableModel model;
	private JTextField txtDernierUser;

	
	/**
	 * Create the application.
	 */
	public ViewAllUser() {
		initialize();
		setmedicamntDetaiToTable();

	}

	
	//mthd set details  to table
			public void setmedicamntDetaiToTable() {
				try {
					
					txtDernierUser.setText(LoginPage.dernierConnectName);
					
					
					
					Connection con = DBConnection.connect();
					PreparedStatement pst= con.prepareStatement("select * from users");
					ResultSet rs =  pst.executeQuery();
					while (rs.next()) {
						int iduser = rs.getInt("id_user");
						String username = rs.getString("user_name");
						String user_password = rs.getString("user_password");
						String user_email = rs.getString("user_email");
						String user_contact = rs.getString("user_contact");


						
						Object []  obj = {iduser,username,user_password,user_email,user_contact};
						model = (DefaultTableModel) tb_detailUser.getModel();
						model.addRow(obj);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		
		JLabel lblNewLabel_1 = new JLabel("Liste des Utilisateurs");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(433, 27, 260, 41);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(236, 334, 637, 222);
		frame.getContentPane().add(scrollPane);
		
		tb_detailUser = new JTable();
		tb_detailUser.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "name", "password", "email", "contact"
			}
		));
		scrollPane.setViewportView(tb_detailUser);
		
		JLabel lblNewLabel = new JLabel("  est le dernier à s'être connecté à l'application");
		lblNewLabel.setBounds(388, 209, 275, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtDernierUser = new JTextField();
		txtDernierUser.setEditable(false);
		txtDernierUser.setBackground(SystemColor.menu);
		txtDernierUser.setBorder(null);
		txtDernierUser.setBounds(272, 206, 96, 20);
		frame.getContentPane().add(txtDernierUser);
		txtDernierUser.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("L'utilisateur");
		lblNewLabel_2.setBounds(197, 209, 65, 14);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
