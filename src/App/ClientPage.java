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
import java.awt.geom.Arc2D.Float;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientPage {

	JFrame frame;
	private JTextField txtNomClient;
	private JTable table;
	private JTextField txtPrix;
	private JTextField txtPayer;
	JSpinner txtQuantite = new JSpinner();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientPage window = new ClientPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	JComboBox comboBoxListeMedic = new JComboBox();
	String nomClient, medicamnt;
	int quantite;
	float prix,payer;
	 
	
	/**
	 * Create the application.
	 */
	public ClientPage() {
		initialize();
		setMedicament();
		setClientDetaiToTable();
	}

	DefaultTableModel model;

	//mthd set details  to table
		public void setClientDetaiToTable() {
			try {
				Connection con = DBConnection.connect();
				PreparedStatement pst= con.prepareStatement("select * from client");
				ResultSet rs =  pst.executeQuery();
				while (rs.next()) {
					String nomClient = rs.getString("nom_client");
					String PrenomClient = rs.getString("prenom_client");

					
					Object []  obj = {nomClient,PrenomClient};
					model = (DefaultTableModel) table.getModel();
					model.addRow(obj);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	public void setMedicament() {
		try {
			Connection con = DBConnection.connect();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nom FROM medicament");
            while (resultSet.next()) {
                String categrCombo = resultSet.getString("nom");
                comboBoxListeMedic.addItem(categrCombo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	 
	//add Credit
			public boolean addCredit() {
				boolean isadd = false;
				

				nomClient = txtNomClient.getText();
				quantite = (int) txtQuantite.getValue();
				medicamnt = (String) comboBoxListeMedic.getSelectedItem();
				prix = java.lang.Float.parseFloat(txtPrix.getText()) ;
				payer = txtPayer.getText().isEmpty() ? 0 : java.lang.Float.parseFloat(txtPayer.getText());
				
				
				try {
					if (quantite <= 0) {
						JOptionPane.showMessageDialog(null, "erreur Quantité");
					}else {
						for(int i = 1; i<=quantite;i++) {
							Connection con = DBConnection.connect();
							PreparedStatement pst = con.prepareStatement("insert into client_medicament (nom_client, name_medicament, prix,payer) values (?,?,?,?)");
							pst.setString(1, nomClient);
							pst.setString(2, medicamnt);
							pst.setFloat(3, prix);	
							pst.setFloat(4, payer);	
							int rownbr = pst.executeUpdate();
							if (rownbr > 0) {
								isadd = true;
							}else {
								isadd = false;
							}
						}
						
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
		panel_2_4_2.setBackground(Color.LIGHT_GRAY);
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
		panel_2_4_3.setBounds(325, 0, 167, 58);
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
		panel_2_4_5.setBackground(Color.GRAY);
		panel_2_4_5.setBounds(0, 0, 167, 58);
		panel_1.add(panel_2_4_5);
		
		JLabel lblNewLabel_4_5 = new JLabel("Accueil");
		lblNewLabel_4_5.setBounds(71, 22, 49, 14);
		panel_2_4_5.add(lblNewLabel_4_5);
		
		JLabel lblNewLabel = new JLabel("NOM");
		lblNewLabel.setBounds(35, 225, 49, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtNomClient = new JTextField();
		txtNomClient.setBounds(120, 225, 96, 20);
		frame.getContentPane().add(txtNomClient);
		txtNomClient.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 428, 253, 145);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rownbr = table.getSelectedRow();
				TableModel model = table.getModel();
				txtNomClient.setText(model.getValueAt(rownbr, 1).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NOM", "PRENOM"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(329, 157, 10, 431);
		frame.getContentPane().add(panel_2);
		
		JLabel lblIdMedicament = new JLabel("liste des medicaments");
		lblIdMedicament.setBounds(374, 207, 119, 14);
		frame.getContentPane().add(lblIdMedicament);
		comboBoxListeMedic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					medicamnt = (String) comboBoxListeMedic.getSelectedItem();
				    Connection con = DBConnection.connect();
				    PreparedStatement pstmt = con.prepareStatement("SELECT prix FROM medicament WHERE nom = ?");
				    pstmt.setString(1, medicamnt);

				    ResultSet resultSet = pstmt.executeQuery();
				    if (resultSet.next()) {
				        float prix = resultSet.getFloat("prix");
				        String prixText = String.valueOf(prix);
				        txtPrix.setText(prixText);
				    } else {

				    	JOptionPane.showMessageDialog(null, "error");
				    }

				    
				} catch (SQLException e1) {
				    e1.printStackTrace();
				}
			}
		});
		
		
		comboBoxListeMedic.setBounds(503, 203, 128, 20);
		frame.getContentPane().add(comboBoxListeMedic);
		
		JLabel lblLaQuantit = new JLabel("la quantité");
		lblLaQuantit.setBounds(374, 282, 119, 14);
		frame.getContentPane().add(lblLaQuantit);
		
		JLabel lblLePrixDe = new JLabel("le prix de medicament ");
		lblLePrixDe.setBounds(374, 351, 119, 14);
		frame.getContentPane().add(lblLePrixDe);
		
		JLabel lblPayer = new JLabel("payer");
		lblPayer.setBounds(374, 457, 119, 14);
		frame.getContentPane().add(lblPayer);
		
		txtPrix = new JTextField();
		txtPrix.setColumns(10);
		txtPrix.setBounds(507, 348, 96, 20);
		frame.getContentPane().add(txtPrix);
		
		txtPayer = new JTextField();
		txtPayer.setColumns(10);
		txtPayer.setBounds(507, 454, 96, 20);
		frame.getContentPane().add(txtPayer);
		
		txtQuantite.setBounds(503, 278, 98, 23);
		frame.getContentPane().add(txtQuantite);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.GRAY);
		panel_2_1.setBounds(707, 157, 10, 431);
		frame.getContentPane().add(panel_2_1);
		
		JButton btnNewButton_1 = new JButton("ajouter le credit ");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(addCredit() == true) {
					JOptionPane.showMessageDialog(null, "vous avez ajouté ");
					}else {
						JOptionPane.showMessageDialog(null, "error d'ajout");
					}	
			}
		});
		btnNewButton_1.setBounds(759, 243, 183, 50);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("la situation des clients");
		btnNewButton_1_1.setBounds(759, 402, 183, 50);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 64, 128));
		panel_3.setBounds(747, 509, 216, 30);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("liste des medicamtn de chaque client");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			ListeMedicClient listeMedicClient = new ListeMedicClient();
			listeMedicClient.frame.setVisible(true);
			}
		});
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(31, 11, 213, 14);
		panel_3.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Ajouter un client");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			AddClient addClient = new AddClient();
			addClient.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(172, 334, 116, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Ajouter un paiement");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			AjoutrePaimentClient ajoutrePaimentClient = new AjoutrePaimentClient();
			ajoutrePaimentClient.frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(759, 320, 183, 50);
		frame.getContentPane().add(btnNewButton_2);
	}
}
