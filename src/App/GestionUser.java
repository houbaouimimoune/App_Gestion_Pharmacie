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
import java.security.DrbgParameters.NextBytes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionUser {

	JFrame frame;
	private JTable tb_detailsUser;
	private JTextField txtUsername;
	private JTextField txtPdw;
	private JTextField txtContact;
	private JTextField txtEmail;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUser window = new GestionUser();
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
	public GestionUser() {
		initialize();
		setmedicamntDetaiToTable();
	}

	
	int idUser;
	String username, userpswd, useremail, usercontact;

	DefaultTableModel model;

	
	//mthd set details  to table
			public void setmedicamntDetaiToTable() {
				try {
					Connection con = DBConnection.connect();
					PreparedStatement pst= con.prepareStatement("select * from users");
					ResultSet rs =  pst.executeQuery();
					while (rs.next()) {
						int id_user = rs.getInt("id_user");
						String user_name = rs.getString("user_name");
						String user_password = rs.getString("user_password");
						String user_email = rs.getString("user_email");
						String user_contact = rs.getString("user_contact");


						
						Object []  obj = {id_user,user_name,user_password,user_email,user_contact};
						model = (DefaultTableModel) tb_detailsUser.getModel();
						model.addRow(obj);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		
		//add Medicament 
			public boolean addMedc() {
				boolean isadd = false;
				
				username = txtUsername.getText();
				userpswd = txtPdw.getText();
				useremail = txtEmail.getText();
				usercontact = txtContact.getText();
				
				try {
					Connection con = DBConnection.connect();
					PreparedStatement pst = con.prepareStatement("insert into users (user_name, user_password, user_email,user_contact) values (?,?,?,?)");
					pst.setString(1, username);
					pst.setString(2, userpswd);
					pst.setString(3, useremail);
					pst.setString(4, usercontact);
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
		
			// mthd update 
			public boolean update() {
				boolean isupdate = false;
				
				idUser = Integer.parseInt(txtId.getText());
				username = txtUsername.getText();
				userpswd = txtPdw.getText();
				useremail = txtEmail.getText();
				usercontact = txtContact.getText();
				
				try {
					
					Connection con = DBConnection.connect();
					
					PreparedStatement pst = con.prepareStatement("update users set user_name = ?,user_password = ?,user_email = ?,user_contact = ? where id_user= ?");
					pst.setString(1, username);
					pst.setString(2, userpswd);
					pst.setString(3, useremail);
					pst.setString(4, usercontact);
					pst.setInt(5, idUser);
					
					
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
			
			// mthd delete 
			public boolean delete() {
				boolean isdeleted = false;		
				idUser = Integer.parseInt(txtId.getText());

				
				try {
					
					Connection con = DBConnection.connect();
					
					PreparedStatement pst = con.prepareStatement("delete from users where id_user = ?");
					pst.setInt(1,idUser);
					
					int nbrrow = pst.executeUpdate();
					if  (nbrrow > 0) {
						isdeleted  = true;
					}else {
						isdeleted = false;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				return isdeleted;
			}
			
			
			// mthd to cleat table 
			public void cleartable() {
				DefaultTableModel model = (DefaultTableModel) tb_detailsUser.getModel();
				model.setRowCount(0);
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
		
		JLabel lblNewLabel_1 = new JLabel("Gestion Utilisateurs");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(433, 27, 388, 41);
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(192, 192, 192));
		panel_3.setBounds(0, 165, 481, 435);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addMedc() == true) {
					JOptionPane.showMessageDialog(null, "vous avez ajouté ");
					cleartable();
					setmedicamntDetaiToTable();
					}else {
						JOptionPane.showMessageDialog(null, "error d'ajout");
					}
			}
		});
		btnAdd.setBounds(52, 355, 89, 23);
		panel_3.add(btnAdd);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(update() == true) {
					JOptionPane.showMessageDialog(null, "vous avez modifié ");
					cleartable();
					setmedicamntDetaiToTable();
					}else {
						JOptionPane.showMessageDialog(null, "error de modification");
					}
			}
		});
		btnUpdate.setBounds(186, 355, 89, 23);
		panel_3.add(btnUpdate);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(delete() == true) {
					delete();
					JOptionPane.showMessageDialog(null, "vous avez supprime  ");
					cleartable();
					setmedicamntDetaiToTable();
					}else {
						JOptionPane.showMessageDialog(null, "error de suppression");
					}
			}
		});
		btnDelete.setBounds(330, 355, 89, 23);
		panel_3.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(40, 85, 49, 14);
		panel_3.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(74, 111, 96, 20);
		panel_3.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPdw = new JTextField();
		txtPdw.setColumns(10);
		txtPdw.setBounds(299, 111, 96, 20);
		panel_3.add(txtPdw);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(265, 85, 49, 14);
		panel_3.add(lblPassword);
		
		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(299, 223, 96, 20);
		panel_3.add(txtContact);
		
		JLabel lblNewLabel_2_1 = new JLabel("contact");
		lblNewLabel_2_1.setBounds(265, 197, 49, 14);
		panel_3.add(lblNewLabel_2_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(74, 223, 96, 20);
		panel_3.add(txtEmail);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(40, 197, 49, 14);
		panel_3.add(lblEmail);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(201, 37, 34, 20);
		panel_3.add(txtId);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(200, 11, 49, 14);
		panel_3.add(lblId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(545, 280, 429, 178);
		frame.getContentPane().add(scrollPane);
		
		tb_detailsUser = new JTable();
		tb_detailsUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rownbr = tb_detailsUser.getSelectedRow();
				TableModel model = tb_detailsUser.getModel();
				txtId.setText(model.getValueAt(rownbr, 0).toString());
				txtUsername.setText(model.getValueAt(rownbr, 1).toString());
				txtPdw.setText(model.getValueAt(rownbr, 2).toString());
				txtEmail.setText(model.getValueAt(rownbr, 3).toString());
				txtContact.setText(model.getValueAt(rownbr, 4).toString());
			}
		});
		tb_detailsUser.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "username", "password", "email", "contact"
			}
		));
		scrollPane.setViewportView(tb_detailsUser);
	}
}
