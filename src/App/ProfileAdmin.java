package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfileAdmin {

	JFrame frame;
	private JTextField txtEmail;
	private JTextField txtMdp;
	 JTextField txtNom;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileAdmin window = new ProfileAdmin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	String pswdAdmin;
	int idAdmin;
	 JTextField txtwelcomProfile;

	/**
	 * Create the application.
	 */
	public ProfileAdmin() {
		initialize();
		setDetaiToTable();
	}
	
	
	
	//mthd set details  to table
			public void setDetaiToTable() {
				try {
					;
					
					Connection con = DBConnection.connect();
					PreparedStatement pst= con.prepareStatement("select * from admin where admin_name = ?");
					pst.setString(1, LoginAdmin.name);
					ResultSet rs =  pst.executeQuery();
					
					while (rs.next()) {
						int id_admin = rs.getInt("id_admin");
						String admin_password = rs.getString("admin_password");
						String admin_email = rs.getString("admin_email");
						String admin_name = rs.getString("admin_name");
						
						txtId.setText(Integer.toString(id_admin));
						txtMdp.setText(admin_password);
						txtEmail.setText(admin_email);
						txtNom.setText(admin_name);


						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// mthd update 
			public boolean update() {
				boolean isupdate = false;

				pswdAdmin = txtMdp.getText();
				idAdmin = Integer.parseInt(txtId.getText());
				
				try {
					
					Connection con = DBConnection.connect();
					
					PreparedStatement pst = con.prepareStatement("update admin set admin_password = ? where id_admin  = ?");
					pst.setString(1, pswdAdmin);
					pst.setInt(2, idAdmin);
					
					
					int nbrrow = pst.executeUpdate();
					if  (nbrrow > 0) {
						isupdate  = true;
					}else {
						isupdate = false;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				return isupdate;
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
		
		JLabel lblNewLabel_1 = new JLabel("Profile");
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
		
		txtwelcomProfile = new JTextField();
		txtwelcomProfile.setBorder(null);
		txtwelcomProfile.setBackground(new Color(64, 128, 128));
		txtwelcomProfile.setEditable(false);
		txtwelcomProfile.setBounds(836, 11, 96, 20);
		panel_1.add(txtwelcomProfile);
		txtwelcomProfile.setColumns(10);
		
		JLabel lblVotreEmai = new JLabel("Votre Email");
		lblVotreEmai.setBounds(126, 312, 73, 14);
		frame.getContentPane().add(lblVotreEmai);
		
		JLabel lblVotreMotDe = new JLabel("votre mot de pass");
		lblVotreMotDe.setBounds(554, 312, 89, 14);
		frame.getContentPane().add(lblVotreMotDe);
		
		JLabel lblNewLabel_3_1 = new JLabel("votre nom");
		lblNewLabel_3_1.setBounds(553, 226, 73, 14);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(229, 309, 173, 20);
		frame.getContentPane().add(txtEmail);
		
		txtMdp = new JTextField();
		txtMdp.setColumns(10);
		txtMdp.setBounds(694, 312, 173, 20);
		frame.getContentPane().add(txtMdp);
		
		txtNom = new JTextField();
		txtNom.setEditable(false);
		txtNom.setColumns(10);
		txtNom.setBounds(694, 223, 173, 20);
		frame.getContentPane().add(txtNom);
		
		JButton btnNewButton = new JButton("changer le mot de pass");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(update() == true) {
					JOptionPane.showMessageDialog(null, "vous avez modifié le mot de pass");
					}else {
						JOptionPane.showMessageDialog(null, "error de modification");
					}
			}
		});
		btnNewButton.setBounds(664, 406, 203, 45);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(126, 226, 73, 14);
		frame.getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(229, 223, 173, 20);
		frame.getContentPane().add(txtId);
	}
}
