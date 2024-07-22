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

public class Medicament {

	JFrame frame;
	static JComboBox  <String> comboCategorie = new JComboBox<String>();
	private JTable tabledetailMedic;
	private JTextField txtNom;
	private JTextField txtPrix;
	private JTextField txtEmplcmnt;
	private JTextField txtQuantite;
	private JTextField txtQuantiteAlert;
	private JTextField txtDateExper;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Medicament window = new Medicament();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	int idMedic ,quantiteMedic,quantiteAlertMedic;
	String namemedic, emplacmntMedic, categorieMedic, dateExpirMedic;
	float  prixMedic;
	 
	 
	
	 
	 
	 
	
	DefaultTableModel model;
	/**
	 * Create the application.
	 */
	public Medicament() {
		initialize();
		setCategorie();
		setmedicamntDetaiToTable();
	}

	
	
	//mthd set details  to table
		public void setmedicamntDetaiToTable() {
			try {
				Connection con = DBConnection.connect();
				PreparedStatement pst= con.prepareStatement("select * from medicament");
				ResultSet rs =  pst.executeQuery();
				while (rs.next()) {
					int idMedic = rs.getInt("id_medicament");
					String nomMedic = rs.getString("nom");  
					int quantiteMedic = rs.getInt("quantite");
					Float prixMedic = rs.getFloat("prix");
					String dateExpirMedic = rs.getString("date_expiration");
					String emplcmMedic = rs.getString("emplacement_etager");
					String categorieMedic = rs.getString("categorie");
					int quantiteAlertMedic = rs.getInt("quantite");


					
					Object []  obj = {idMedic,nomMedic,quantiteMedic,prixMedic,dateExpirMedic,emplcmMedic,categorieMedic,quantiteAlertMedic};
					model = (DefaultTableModel) tabledetailMedic.getModel();
					model.addRow(obj);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	// set categorie combo box
		public void setCategorie() {
			try {
				Connection con = DBConnection.connect();
	            Statement statement = con.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT name_categorie FROM categorie");
	            while (resultSet.next()) {
	                String categrCombo = resultSet.getString("name_categorie");
	                comboCategorie.addItem(categrCombo);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	
	//add Medicament 
		public boolean addMedc() {
			boolean isadd = false;
			
			namemedic = txtNom.getText();
			emplacmntMedic = txtEmplcmnt.getText();
            categorieMedic = comboCategorie.getSelectedItem().toString();
			dateExpirMedic = txtDateExper.getText();
			prixMedic = Float.parseFloat(txtPrix.getText()) ;
			quantiteMedic = Integer.parseInt(txtQuantite.getText()) ;
			quantiteAlertMedic = Integer.parseInt(txtQuantiteAlert.getText()) ;
			
			try {
				Connection con = DBConnection.connect();
				PreparedStatement pst = con.prepareStatement("insert into medicament (nom, quantite, quantite_alert,prix,date_expiration,emplacement_etager,categorie) values (?,?,?,?,?,?,?)");
				pst.setString(1, namemedic);
				pst.setInt(2, quantiteMedic);
				pst.setInt(3, quantiteAlertMedic);
				pst.setFloat(4, prixMedic);
				pst.setString(5, dateExpirMedic);
				pst.setString(6, emplacmntMedic);
				pst.setString(7, categorieMedic);
				
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

			namemedic = txtNom.getText();
			emplacmntMedic = txtEmplcmnt.getText();
			categorieMedic = comboCategorie.getSelectedItem().toString();
			dateExpirMedic = txtDateExper.getText();
			prixMedic = Float.parseFloat(txtPrix.getText()) ;
			quantiteMedic = Integer.parseInt(txtQuantite.getText()) ;
			quantiteAlertMedic = Integer.parseInt(txtQuantiteAlert.getText());
			idMedic = Integer.parseInt(txtId.getText());
			
			try {
				
				Connection con = DBConnection.connect();
				
				PreparedStatement pst = con.prepareStatement("update medicament set nom = ?,quantite = ?,quantite_alert = ?,prix = ?,date_expiration = ?,emplacement_etager = ?,categorie = ? where id_medicament = ?");
				pst.setString(1, namemedic);
				pst.setInt(2, quantiteMedic);
				pst.setInt(3, quantiteAlertMedic);
				pst.setFloat(4, prixMedic);
				pst.setString(5, dateExpirMedic);
				pst.setString(6, emplacmntMedic);
				pst.setString(7, categorieMedic);
				pst.setInt(8,idMedic);
				
				
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
			idMedic = Integer.parseInt(txtId.getText());

			
			try {
				
				Connection con = DBConnection.connect();
				
				PreparedStatement pst = con.prepareStatement("delete from medicament where id_medicament = ?");
				pst.setInt(1,idMedic);
				
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
			DefaultTableModel model = (DefaultTableModel) tabledetailMedic.getModel();
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
		panel_2_4_4.setBackground(Color.LIGHT_GRAY);
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
		
		JLabel lblNewLabel = new JLabel("NOM");
		lblNewLabel.setBounds(30, 45, 49, 14);
		panel_3.add(lblNewLabel);
		
		txtNom = new JTextField();
		txtNom.setBounds(30, 75, 96, 20);
		panel_3.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("date d'expiration");
		lblNewLabel_1.setBounds(286, 148, 85, 14);
		panel_3.add(lblNewLabel_1);
		
		txtPrix = new JTextField();
		txtPrix.setColumns(10);
		txtPrix.setBounds(30, 178, 96, 20);
		panel_3.add(txtPrix);
		
		JLabel lblNewLabel_2 = new JLabel("prix");
		lblNewLabel_2.setBounds(30, 148, 49, 14);
		panel_3.add(lblNewLabel_2);
		
		txtEmplcmnt = new JTextField();
		txtEmplcmnt.setColumns(10);
		txtEmplcmnt.setBounds(30, 365, 96, 20);
		panel_3.add(txtEmplcmnt);
		
		JLabel lblNewLabel_3 = new JLabel("emplacement etager");
		lblNewLabel_3.setBounds(42, 327, 125, 14);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("categorie");
		lblNewLabel_3_1.setBounds(275, 340, 49, 14);
		panel_3.add(lblNewLabel_3_1);
		
		txtQuantite = new JTextField();
		txtQuantite.setColumns(10);
		txtQuantite.setBounds(30, 249, 96, 20);
		panel_3.add(txtQuantite);
		
		JLabel lblNewLabel_5 = new JLabel("Quantite");
		lblNewLabel_5.setBounds(30, 219, 49, 14);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quantite d'alert");
		lblNewLabel_1_1.setBounds(286, 219, 96, 14);
		panel_3.add(lblNewLabel_1_1);
		
		txtQuantiteAlert = new JTextField();
		txtQuantiteAlert.setColumns(10);
		txtQuantiteAlert.setBounds(286, 249, 96, 20);
		panel_3.add(txtQuantiteAlert);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(322, 45, 49, 14);
		panel_3.add(lblId);
		
		txtDateExper = new JTextField();
		txtDateExper.setColumns(10);
		txtDateExper.setBounds(286, 178, 96, 20);
		panel_3.add(txtDateExper);
		
		txtId = new JTextField();
		txtId.setBounds(316, 76, 39, 17);
		panel_3.add(txtId);
		txtId.setColumns(10);
		
		
		comboCategorie.setBounds(261, 364, 110, 20);
		panel_3.add(comboCategorie);
		
		JButton btnNewButton = new JButton("add categorie");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			AddCategorie addCategorie = new AddCategorie();
			addCategorie.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(311, 432, 119, 20);
		panel_3.add(btnNewButton);
		
		JLabel lblNewLabel_6 = new JLabel("Cliquer pour ajouter une categorie");
		lblNewLabel_6.setBounds(261, 404, 180, 14);
		panel_3.add(lblNewLabel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(483, 92, 491, 203);
		panel_2.add(scrollPane);
		
		tabledetailMedic = new JTable();
		tabledetailMedic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rownbr = tabledetailMedic.getSelectedRow();
				TableModel model = tabledetailMedic.getModel();
				txtId.setText(model.getValueAt(rownbr, 0).toString());
				txtNom.setText(model.getValueAt(rownbr, 1).toString());
				txtQuantite.setText(model.getValueAt(rownbr, 2).toString());
				txtPrix.setText(model.getValueAt(rownbr, 3).toString());
				txtDateExper.setText(model.getValueAt(rownbr, 4).toString());
				txtEmplcmnt.setText(model.getValueAt(rownbr, 5).toString());
				comboCategorie.addItem(model.getValueAt(rownbr, 6).toString());
				comboCategorie.setSelectedItem(model.getValueAt(rownbr, 6).toString());
				txtQuantiteAlert.setText(model.getValueAt(rownbr, 7).toString());
			}
		});
		tabledetailMedic.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "nom", "quantitre", "prix", "date exper", "emplacment", "categorie", "quantite d'alert"
			}
		));
		scrollPane.setViewportView(tabledetailMedic);
		
		JButton btnAdd = new JButton("add");
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
		btnAdd.setBounds(522, 380, 89, 23);
		panel_2.add(btnAdd);
		
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
		btnDelete.setBounds(821, 380, 89, 23);
		panel_2.add(btnDelete);
		
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
		btnUpdate.setBounds(684, 380, 89, 23);
		panel_2.add(btnUpdate);
	}
}
