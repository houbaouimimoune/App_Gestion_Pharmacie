package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddFournisseur {

	JFrame frame;
	private JTable tabledetail;
	private JTextField txtNom;
	private JTextField txtNReference;
	private JTextField txtTele;
	private JTextField txtAdresse;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFournisseur window = new AddFournisseur();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	String nom, numRefeence, tele, email,adresse;
	int idFournisseur;
	
	 
	
	 
	 
	 
	
	DefaultTableModel model;
	private JTextField txtEmail;
	/**
	 * Create the application.
	 */
	public AddFournisseur() {
		initialize();
		setFournisseurDetaiToTable();
	}

	
	
	//mthd set details  to table
		public void setFournisseurDetaiToTable() {
			try {
				Connection con = DBConnection.connect();
				PreparedStatement pst= con.prepareStatement("select * from fournisseur");
				ResultSet rs =  pst.executeQuery();
				while (rs.next()) {
					int id_fournisseur = rs.getInt("id_fournisseur");
					String nom_fournisseur = rs.getString("nom_fournisseur");
					int n_reference = rs.getInt("n_reference");
					String email_fournisseur = rs.getString("email_fournisseur");
					String adresse_fournisseur = rs.getString("adresse_fournisseur");
					String n_tele_fournisseur = rs.getString("n_tele_fournisseur");


					
					Object []  obj = {id_fournisseur,nom_fournisseur,n_reference,email_fournisseur,adresse_fournisseur,n_tele_fournisseur};
					model = (DefaultTableModel) tabledetail.getModel();
					model.addRow(obj);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	//add Fournisseur 
		public boolean addFournisseur() {
			boolean isadd = false;
			
			nom = txtNom.getText();
			numRefeence = txtNReference.getText();
			tele = txtTele.getText();	
			adresse  = txtAdresse.getText();
			email= txtEmail.getText();


			
			
			try {
				Connection con = DBConnection.connect();
				PreparedStatement pst = con.prepareStatement("insert into fournisseur (nom_fournisseur, n_reference, email_fournisseur,adresse_fournisseur,n_tele_fournisseur) values (?,?,?,?,?)");
				pst.setString(1, nom);
				pst.setString(2, numRefeence);
				pst.setString(3, email);
				pst.setString(4, adresse);
				pst.setString(5, tele);
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

			nom = txtNom.getText();
			numRefeence = txtNReference.getText();
			tele = txtTele.getText();	
			adresse  = txtAdresse.getText();
			email= txtEmail.getText();
			idFournisseur = Integer.parseInt(txtId.getText()) ;
			
			try {
				
				Connection con = DBConnection.connect();
				
				PreparedStatement pst = con.prepareStatement("update fournisseur set nom_fournisseur = ?,n_reference = ?,email_fournisseur = ?,adresse_fournisseur = ?, n_tele_fournisseur=? where id_fournisseur  = ?");
				pst.setString(1, nom);
				pst.setString(2, numRefeence);
				pst.setString(3, email);
				pst.setString(4, adresse);
				pst.setString(5, tele);
				pst.setInt(6, idFournisseur);
				
				
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
			idFournisseur= Integer.parseInt(txtId.getText());

			
			try {
				
				Connection con = DBConnection.connect();
				
				PreparedStatement pst = con.prepareStatement("delete from fournisseur where id_fournisseur = ?");
				pst.setInt(1,idFournisseur);
				
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
			DefaultTableModel model = (DefaultTableModel) tabledetail.getModel();
			model.setRowCount(0);
		}
		
		
		
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setBounds(0, 0, 1000, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 128, 128));
		panel.setBounds(0, 0, 1000, 80);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(0, 78, 1000, 58);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2_4 = new JPanel();
		panel_2_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(frame, "confirm if you want to exit","print", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION ) {
					FirstPage firstPage = new FirstPage();
					firstPage.frame.setVisible(true);
					}
			}
		});
		panel_2_4.setBackground(new Color(255, 0, 0));
		panel_2_4.setBounds(833, 0, 167, 58);
		panel_1.add(panel_2_4);
		panel_2_4.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Logout");
		lblNewLabel_4.setBounds(71, 22, 49, 14);
		panel_2_4.add(lblNewLabel_4);
		
		JPanel panel_2_4_1 = new JPanel();
		panel_2_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			EmplyeePage emplyeePage = new EmplyeePage();
			emplyeePage.frame.setVisible(true);
			}
		});
		panel_2_4_1.setLayout(null);
		panel_2_4_1.setBackground(Color.GRAY);
		panel_2_4_1.setBounds(664, 0, 167, 58);
		panel_1.add(panel_2_4_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Employé");
		lblNewLabel_4_1.setBounds(71, 22, 49, 14);
		panel_2_4_1.add(lblNewLabel_4_1);
		
		JPanel panel_2_4_2 = new JPanel();
		panel_2_4_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			ClientPage clientPage = new ClientPage();
			clientPage.frame.setVisible(true);
			}
		});
		panel_2_4_2.setLayout(null);
		panel_2_4_2.setBackground(new Color(128, 128, 128));
		panel_2_4_2.setBounds(495, 0, 167, 58);
		panel_1.add(panel_2_4_2);
		
		JLabel lblNewLabel_4_2 = new JLabel("Client");
		lblNewLabel_4_2.setBounds(71, 22, 49, 14);
		panel_2_4_2.add(lblNewLabel_4_2);
		
		JPanel panel_2_4_3 = new JPanel();
		panel_2_4_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			AchatsPage achatsPage = new AchatsPage();
			achatsPage.frame.setVisible(true);
			}
		});
		panel_2_4_3.setLayout(null);
		panel_2_4_3.setBackground(new Color(192, 192, 192));
		panel_2_4_3.setBounds(326, 0, 167, 58);
		panel_1.add(panel_2_4_3);
		
		JLabel lblNewLabel_4_3 = new JLabel("Achats");
		lblNewLabel_4_3.setBounds(71, 22, 49, 14);
		panel_2_4_3.add(lblNewLabel_4_3);
		
		JPanel panel_2_4_4 = new JPanel();
		panel_2_4_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			Medicament medicament = new Medicament();
			medicament.frame.setVisible(true);
			}
		});
		panel_2_4_4.setLayout(null);
		panel_2_4_4.setBackground(new Color(128, 128, 128));
		panel_2_4_4.setBounds(166, 0, 162, 58);
		panel_1.add(panel_2_4_4);
		
		JLabel lblNewLabel_4_4 = new JLabel("Medicament");
		lblNewLabel_4_4.setBounds(71, 22, 60, 14);
		panel_2_4_4.add(lblNewLabel_4_4);
		
		JPanel panel_2_4_5 = new JPanel();
		panel_2_4_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			HomePage homePage = new HomePage();
			homePage.frame.setVisible(true);
			}
		});
		panel_2_4_5.setLayout(null);
		panel_2_4_5.setBackground(Color.GRAY);
		panel_2_4_5.setBounds(0, 0, 167, 58);
		panel_1.add(panel_2_4_5);
		
		JLabel lblNewLabel_4_5 = new JLabel("Accueil");
		lblNewLabel_4_5.setBounds(71, 22, 49, 14);
		panel_2_4_5.add(lblNewLabel_4_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 136, 1000, 463);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaptionBorder);
		panel_3.setBounds(0, 0, 451, 463);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOM de founisseur");
		lblNewLabel.setBounds(30, 45, 96, 14);
		panel_3.add(lblNewLabel);
		
		txtNom = new JTextField();
		txtNom.setBounds(30, 75, 96, 20);
		panel_3.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("adresse ");
		lblNewLabel_1.setBounds(286, 148, 85, 14);
		panel_3.add(lblNewLabel_1);
		
		txtNReference = new JTextField();
		txtNReference.setColumns(10);
		txtNReference.setBounds(30, 178, 96, 20);
		panel_3.add(txtNReference);
		
		JLabel lblNewLabel_2 = new JLabel("n de referance ");
		lblNewLabel_2.setBounds(30, 148, 49, 14);
		panel_3.add(lblNewLabel_2);
		
		txtTele = new JTextField();
		txtTele.setColumns(10);
		txtTele.setBounds(30, 276, 96, 20);
		panel_3.add(txtTele);
		
		JLabel lblNewLabel_5 = new JLabel("N Tele");
		lblNewLabel_5.setBounds(30, 246, 49, 14);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(322, 45, 49, 14);
		panel_3.add(lblId);
		
		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(286, 178, 96, 20);
		panel_3.add(txtAdresse);
		
		txtId = new JTextField();
		txtId.setBounds(316, 76, 39, 17);
		panel_3.add(txtId);
		txtId.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(286, 276, 96, 20);
		panel_3.add(txtEmail);
		
		JLabel lblNewLabel_5_1 = new JLabel("email");
		lblNewLabel_5_1.setBounds(286, 246, 49, 14);
		panel_3.add(lblNewLabel_5_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(483, 92, 491, 203);
		panel_2.add(scrollPane);
		
		tabledetail = new JTable();
		tabledetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rownbr = tabledetail.getSelectedRow();
				TableModel model = tabledetail.getModel();
				txtId.setText(model.getValueAt(rownbr, 0).toString());
				txtNom.setText(model.getValueAt(rownbr, 1).toString());
				txtNReference.setText(model.getValueAt(rownbr, 2).toString());
				txtEmail.setText(model.getValueAt(rownbr, 3).toString());
				txtAdresse.setText(model.getValueAt(rownbr, 4).toString());
				txtTele.setText(model.getValueAt(rownbr, 5).toString());


			}
		});
		tabledetail.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nom Fournisseur", "N\u00B0 Reference", "email", "Adresse", "Tele"
			}
		));
		scrollPane.setViewportView(tabledetail);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addFournisseur() == true) {
					JOptionPane.showMessageDialog(null, "vous avez ajouté ");
					cleartable();
					setFournisseurDetaiToTable();
					}else {
						JOptionPane.showMessageDialog(null, "error d'ajout");
					}
			}
		});
		btnAdd.setBounds(522, 380, 89, 23);
		panel_2.add(btnAdd);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(delete() == true) {
					delete();
					JOptionPane.showMessageDialog(null, "vous avez supprime  ");
					cleartable();
					setFournisseurDetaiToTable();
					}else {
						JOptionPane.showMessageDialog(null, "error de suppression");
					}
			}
		});
		btnDelete.setBounds(821, 380, 89, 23);
		panel_2.add(btnDelete);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(update() == true) {
					JOptionPane.showMessageDialog(null, "vous avez modifié ");
					cleartable();
					setFournisseurDetaiToTable();
					}else {
						JOptionPane.showMessageDialog(null, "error de modification");
					}
			}
		});
		btnUpdate.setBounds(684, 380, 89, 23);
		panel_2.add(btnUpdate);
	}
}
