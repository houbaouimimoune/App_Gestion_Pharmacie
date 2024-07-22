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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AchatsPage {

	JFrame frame;
	private JTextField txtNom;
	private JTable tableDetailFournisseur;
	private JTextField txtPrix;
	private JTextField txtTotale;
	private JTextField txtPaiment;
	JSpinner txtQuantite = new JSpinner();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AchatsPage window = new AchatsPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JComboBox<String> comboBox = new JComboBox();

	
	/**
	 * Create the application.
	 */
	public AchatsPage() {
		initialize();
		setMedicament();
		setFournisseurDetaiToTable();
	}
	
	DefaultTableModel model;
	
	//mthd set details  to table
	public void setFournisseurDetaiToTable() {
		try {
			Connection con = DBConnection.connect();
			PreparedStatement pst= con.prepareStatement("select * from fournisseur");
			ResultSet rs =  pst.executeQuery();
			while (rs.next()) {
				int numReference = rs.getInt("n_reference");
				String nomFournisseur = rs.getString("nom_fournisseur");

				
				Object []  obj = {numReference,nomFournisseur};
				model = (DefaultTableModel) tableDetailFournisseur.getModel();
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
                comboBox.addItem(categrCombo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	String nomFournisseur, medicamnt;
	int quantite;
	float prix,payer,totale;
	
	//add Achat
	public boolean addAchat() {
		boolean isadd = false;
		
		medicamnt = (String) comboBox.getSelectedItem();
		nomFournisseur = txtNom.getText();
		quantite = (int) txtQuantite.getValue();
		prix = java.lang.Float.parseFloat(txtPrix.getText()) ;
		totale = java.lang.Float.parseFloat(txtTotale.getText()) ;
		payer = txtPaiment.getText().isEmpty() ? 0 : java.lang.Float.parseFloat(txtPaiment.getText());
		
		
		try {
			if (quantite <= 0) {
				JOptionPane.showMessageDialog(null, "erreur Quantité");
			}else {
				for(int i = 1; i<=quantite;i++) {
					Connection con = DBConnection.connect();
					PreparedStatement pst = con.prepareStatement("insert into fournisseur_medicament (nom_fournisseur, nom_medicament, quantite_medicament,totale,payer) values (?,?,?,?,?)");
					pst.setString(1, nomFournisseur);
					pst.setString(2, medicamnt);
					pst.setInt(3, quantite);	
					pst.setFloat(4, totale);
					pst.setFloat(5, payer);

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
		panel_2_4_3.setBackground(Color.LIGHT_GRAY);
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
		panel_2_4_5.setBackground(Color.GRAY);
		panel_2_4_5.setBounds(0, 0, 167, 58);
		panel_1.add(panel_2_4_5);
		
		JLabel lblNewLabel_4_5 = new JLabel("Accueil");
		lblNewLabel_4_5.setBounds(71, 22, 49, 14);
		panel_2_4_5.add(lblNewLabel_4_5);
		
		JLabel lblNewLabel = new JLabel("Nom de fournisseur");
		lblNewLabel.setBounds(43, 216, 96, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtNom = new JTextField();
		txtNom.setBounds(109, 241, 106, 20);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajuter un founisseur");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFournisseur addFournisseur = new AddFournisseur();
				addFournisseur.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(109, 324, 143, 38);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 424, 257, 140);
		frame.getContentPane().add(scrollPane);
		
		tableDetailFournisseur = new JTable();
		tableDetailFournisseur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rownbr = tableDetailFournisseur.getSelectedRow();
				TableModel model = tableDetailFournisseur.getModel();
				txtNom.setText(model.getValueAt(rownbr, 1).toString());
			}
		});
		scrollPane.setViewportView(tableDetailFournisseur);
		tableDetailFournisseur.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"num de reference", "nom de fournisseur"
			}
		));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(325, 171, 10, 417);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.GRAY);
		panel_2_1.setBounds(671, 171, 10, 417);
		frame.getContentPane().add(panel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("liste des medicaments");
		lblNewLabel_1.setBounds(379, 216, 116, 14);
		frame.getContentPane().add(lblNewLabel_1);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					medicamnt = (String) comboBox.getSelectedItem();
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
		
		comboBox.setBounds(505, 212, 152, 31);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Quantite");
		lblNewLabel_2.setBounds(355, 301, 49, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("prix de medicament");
		lblNewLabel_3.setBounds(352, 388, 106, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtPrix = new JTextField();
		txtPrix.setBounds(521, 385, 106, 20);
		frame.getContentPane().add(txtPrix);
		txtPrix.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("totale");
		lblNewLabel_5.setBounds(355, 447, 49, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		txtTotale = new JTextField();
		txtTotale.setColumns(10);
		txtTotale.setBounds(521, 444, 106, 20);
		frame.getContentPane().add(txtTotale);
		
		txtPaiment = new JTextField();
		txtPaiment.setColumns(10);
		txtPaiment.setBounds(521, 525, 106, 20);
		frame.getContentPane().add(txtPaiment);
		
		JLabel lblNewLabel_5_1 = new JLabel("paiment");
		lblNewLabel_5_1.setBounds(355, 528, 49, 14);
		frame.getContentPane().add(lblNewLabel_5_1);
		
		JButton btnNewButton_1 = new JButton("Ajouter l'achat");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(addAchat() == true) {
					JOptionPane.showMessageDialog(null, "vous avez ajouté ");
					}else {
						JOptionPane.showMessageDialog(null, "error d'ajout");
					}
			}
		});
		btnNewButton_1.setBounds(739, 241, 190, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Ajouter un paiment");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			AjouterPaimentFournisseur ajouterPaimentFournisseur = new AjouterPaimentFournisseur();
			ajouterPaimentFournisseur.frame.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(739, 362, 190, 38);
		frame.getContentPane().add(btnNewButton_1_1);
		txtQuantite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantite = (int) txtQuantite.getValue();
				
				if (! txtPrix.getText().isEmpty()) {
					prix = Float.parseFloat(txtPrix.getText()) ;
				}else {
					prix = 0;
				}
				
				
				float totale = quantite * prix;
				txtTotale.setText(totale+" dh");


			}
		});
		
		txtQuantite.setBounds(522, 298, 105, 17);
		frame.getContentPane().add(txtQuantite);
	}
}
