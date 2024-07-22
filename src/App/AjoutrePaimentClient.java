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

public class AjoutrePaimentClient {

	JFrame frame;
	private JTextField txtTotale;
	private JTextField txtPaiement;
	JComboBox comboBox = new JComboBox();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutrePaimentClient window = new AjoutrePaimentClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 
	

	String nomClient;
	double payer;
	
	
	
	
	
	
	

	
	
	
	/**
	 * Create the application.
	 */
	public AjoutrePaimentClient() {
		initialize();
		 setClient();
	}

	
	
	
	public void setTotale() {
		try {
        	
			nomClient = (String) comboBox.getSelectedItem();
 
			
        	Connection con = DBConnection.connect();
        	String sql = "SELECT * FROM client_medicament WHERE nom_client=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nomClient);
            
            ResultSet resultSet = statement.executeQuery();

            double total = 0;
            
            while (resultSet.next()) {
                double prix = resultSet.getDouble("prix");
                total += prix;
            }

            txtTotale.setText(total+" dh");

            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des données !");
        }
	}
	
	
	
	
	
	
	
	public void setClient() {
		try {
			Connection con = DBConnection.connect();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nom_client FROM client");
            while (resultSet.next()) {
                String clientCombobox = resultSet.getString("nom_client");
                comboBox.addItem(clientCombobox);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	
	// Ajouter un paiement
				public boolean AjouterPaiement() {
					boolean isupdate = false;

					
					payer = Double.parseDouble(txtPaiement.getText()) ;
					nomClient = (String) comboBox.getSelectedItem();

					
					
					try {
						
						Connection con = DBConnection.connect();
						
						PreparedStatement pst1 = con.prepareStatement("select * from  client  where nom_client = ?");
						pst1.setString(1, nomClient);

			            ResultSet resultSet = pst1.executeQuery();
			            int  nbr = 0;
			            while (resultSet.next()) {
			                nbr +=1;
			            }
						
						
						PreparedStatement pst = con.prepareStatement("update client_medicament set payer = payer + ? where nom_client = ?");
						pst.setDouble(1, payer/nbr);
						pst.setString(2, nomClient);

						
						
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
		
		comboBox.setBounds(40, 334, 164, 45);
		frame.getContentPane().add(comboBox);
		
		txtTotale = new JTextField();
		txtTotale.setEditable(false);
		txtTotale.setBounds(287, 334, 147, 45);
		frame.getContentPane().add(txtTotale);
		txtTotale.setColumns(10);
		
		txtPaiement = new JTextField();
		txtPaiement.setColumns(10);
		txtPaiement.setBounds(540, 334, 147, 45);
		frame.getContentPane().add(txtPaiement);
		
		JButton btnNewButton = new JButton("Ajouter ke paiemet");
		btnNewButton.setBounds(799, 334, 158, 45);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("le client");
		lblNewLabel.setBounds(47, 279, 49, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTotale = new JLabel("totale");
		lblTotale.setBounds(273, 279, 49, 14);
		frame.getContentPane().add(lblTotale);
		
		JLabel lblNewLabel_1_1 = new JLabel("le paiement");
		lblNewLabel_1_1.setBounds(540, 279, 49, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
	}
}
