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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmplyeePage {

	JFrame frame;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtSalaire;
	private JTextField txtAdress;
	private JTextField txtPoste;
	private JTextField txtDateEmbauche;
	private JTextField txtId;
	private JTable tb_details_employe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmplyeePage window = new EmplyeePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	int idEmploye;
	String nomEmploye, premomEmploye, adressEmploye, dateEmbaucheEmploye,posteEmploye;
	float  salaireEmploye;
	
	DefaultTableModel model;

	/**
	 * Create the application.
	 */
	public EmplyeePage() {
		initialize();
		setmedicamntDetaiToTable();
	}

	
	
	
	//mthd set details  to table
			public void setmedicamntDetaiToTable() {
				try {
					Connection con = DBConnection.connect();
					PreparedStatement pst= con.prepareStatement("select * from employe");
					ResultSet rs =  pst.executeQuery();
					while (rs.next()) {
						int idEmploye = rs.getInt("id");
						String nomemploye = rs.getString("nom");
						String prenomemploye = rs.getString("prenom");
						String adressemploye = rs.getString("adresse");
						float salaireemploye = rs.getFloat("salaire");
						String posteemploye = rs.getString("poste");
						String dateEmbaucheemploye = rs.getString("date_embauche");


						
						Object []  obj = {idEmploye,nomemploye,prenomemploye,adressemploye,salaireemploye,posteemploye,dateEmbaucheemploye};
						model = (DefaultTableModel) tb_details_employe.getModel();
						model.addRow(obj);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	
	
			//add Medicament 
			public boolean add() {
				boolean isadd = false;
				
				nomEmploye = txtNom.getText();
				premomEmploye = txtPrenom.getText();
				adressEmploye = txtAdress.getText();
				salaireEmploye = Float.parseFloat(txtSalaire.getText()) ;
				dateEmbaucheEmploye = txtDateEmbauche.getText();
				posteEmploye = txtPoste.getText();
				
				try {
					Connection con = DBConnection.connect();
					PreparedStatement pst = con.prepareStatement("insert into employe (nom, prenom, adresse, salaire, poste, date_embauche) VALUES (?,?,?,?,?,?)");
					pst.setString(1, nomEmploye);
					pst.setString(2, premomEmploye);
					pst.setString(3, adressEmploye);
					pst.setFloat(4, salaireEmploye);
					pst.setString(5, posteEmploye);
					pst.setString(6, dateEmbaucheEmploye);
					
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

				idEmploye = Integer.parseInt(txtId.getText()) ;
				nomEmploye = txtNom.getText();
				premomEmploye = txtPrenom.getText();
				adressEmploye = txtAdress.getText();
				salaireEmploye = Float.parseFloat(txtSalaire.getText()) ;
				dateEmbaucheEmploye = txtDateEmbauche.getText();
				posteEmploye = txtPoste.getText();
				
				try {
					
					Connection con = DBConnection.connect();
					
					PreparedStatement pst = con.prepareStatement("update employe set nom = ?,prenom = ?,adresse = ?,salaire = ?,poste = ?,date_embauche = ? where id = ?");
					pst.setString(1, nomEmploye);
					pst.setString(2, premomEmploye);
					pst.setString(3, adressEmploye);
					pst.setFloat(4, salaireEmploye);
					pst.setString(5, posteEmploye);
					pst.setString(6, dateEmbaucheEmploye);
					pst.setInt(7, idEmploye);
					
					
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
				idEmploye = Integer.parseInt(txtId.getText());

				
				try {
					
					Connection con = DBConnection.connect();
					
					PreparedStatement pst = con.prepareStatement("delete from employe where id = ?");
					pst.setInt(1,idEmploye);
					
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
				DefaultTableModel model = (DefaultTableModel) tb_details_employe.getModel();
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
		panel_2_4_1.setBackground(Color.LIGHT_GRAY);
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
		panel_2_4_2.setBackground(Color.GRAY);
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
		panel_2_4_3.setBackground(Color.GRAY);
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
		panel_2_4_4.setBackground(Color.GRAY);
		panel_2_4_4.setBounds(161, 0, 167, 58);
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
		panel_2_4_5.setBackground(new Color(128, 128, 128));
		panel_2_4_5.setBounds(0, 0, 167, 58);
		panel_1.add(panel_2_4_5);
		
		JLabel lblNewLabel_4_5 = new JLabel("Accueil");
		lblNewLabel_4_5.setBounds(71, 22, 49, 14);
		panel_2_4_5.add(lblNewLabel_4_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(0, 134, 499, 58);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ajouter un emplyée");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(204, 22, 158, 14);
		panel_2.add(lblNewLabel);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			PrintCarteEmployePage printCarteEmployePage = new PrintCarteEmployePage();
			printCarteEmployePage.frame.setVisible(true);
			}
		});
		panel_2_1.setBackground(new Color(192, 192, 192));
		panel_2_1.setBounds(501, 134, 499, 58);
		frame.getContentPane().add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("print la carte d'employée");
		lblNewLabel_1.setBounds(231, 21, 139, 14);
		panel_2_1.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(105, 105, 105));
		panel_3.setBounds(0, 189, 499, 410);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("nom");
		lblNewLabel_2.setBounds(40, 34, 49, 14);
		panel_3.add(lblNewLabel_2);
		
		txtNom = new JTextField();
		txtNom.setBounds(50, 59, 96, 20);
		panel_3.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("prenom");
		lblNewLabel_2_1.setBounds(273, 34, 49, 14);
		panel_3.add(lblNewLabel_2_1);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(283, 59, 96, 20);
		panel_3.add(txtPrenom);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("salaire");
		lblNewLabel_2_1_1.setBounds(273, 127, 49, 14);
		panel_3.add(lblNewLabel_2_1_1);
		
		txtSalaire = new JTextField();
		txtSalaire.setColumns(10);
		txtSalaire.setBounds(283, 152, 96, 20);
		panel_3.add(txtSalaire);
		
		JLabel lblNewLabel_2_2 = new JLabel("adresse");
		lblNewLabel_2_2.setBounds(40, 127, 49, 14);
		panel_3.add(lblNewLabel_2_2);
		
		txtAdress = new JTextField();
		txtAdress.setColumns(10);
		txtAdress.setBounds(50, 152, 96, 20);
		panel_3.add(txtAdress);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Poste");
		lblNewLabel_2_1_2.setBounds(273, 205, 49, 14);
		panel_3.add(lblNewLabel_2_1_2);
		
		txtPoste = new JTextField();
		txtPoste.setColumns(10);
		txtPoste.setBounds(283, 230, 96, 20);
		panel_3.add(txtPoste);
		
		JLabel lblNewLabel_2_3 = new JLabel("dated'embauche");
		lblNewLabel_2_3.setBounds(40, 205, 89, 14);
		panel_3.add(lblNewLabel_2_3);
		
		txtDateEmbauche = new JTextField();
		txtDateEmbauche.setColumns(10);
		txtDateEmbauche.setBounds(50, 230, 96, 20);
		panel_3.add(txtDateEmbauche);
		
		JLabel lblNewLabel_3 = new JLabel("id");
		lblNewLabel_3.setBounds(188, 11, 49, 14);
		panel_3.add(lblNewLabel_3);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(198, 34, 30, 14);
		panel_3.add(txtId);
		txtId.setColumns(10);
		
		JButton btnNewButton = new JButton("add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(add() == true) {
					JOptionPane.showMessageDialog(null, "vous avez ajouté ");
					cleartable();
					setmedicamntDetaiToTable();
					}else {
						JOptionPane.showMessageDialog(null, "error d'ajout");
					}
			}
		});
		btnNewButton.setBounds(40, 303, 89, 23);
		panel_3.add(btnNewButton);
		
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
		btnUpdate.setBounds(172, 303, 89, 23);
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
		btnDelete.setBounds(308, 303, 89, 23);
		panel_3.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(521, 300, 469, 135);
		frame.getContentPane().add(scrollPane);
		
		tb_details_employe = new JTable();
		tb_details_employe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rownbr = tb_details_employe.getSelectedRow();
				TableModel model = tb_details_employe.getModel();
				txtId.setText(model.getValueAt(rownbr, 0).toString());
				txtNom.setText(model.getValueAt(rownbr, 1).toString());
				txtPrenom.setText(model.getValueAt(rownbr, 2).toString());
				txtSalaire.setText(model.getValueAt(rownbr, 3).toString());
				txtPoste.setText(model.getValueAt(rownbr, 4).toString());
				txtAdress.setText(model.getValueAt(rownbr, 5).toString());
				txtDateEmbauche.setText(model.getValueAt(rownbr, 6).toString());
			}
		});
		tb_details_employe.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "nom", "prenom", "salaire", "poste", "adress", "date embauche"
			}
		));
		scrollPane.setViewportView(tb_details_employe);
	}
}
