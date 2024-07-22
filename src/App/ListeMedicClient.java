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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

public class ListeMedicClient {

	JFrame frame;
	private JTextField txtNom;
	private JTextField txtTotale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeMedicClient window = new ListeMedicClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    public  JTextArea textAreaListeMedic = new JTextArea();

	
	public void afficherListeMedicaments(String client) {
        try {
        	
        	Connection con = DBConnection.connect();
        	String sql = "SELECT * FROM client_medicament WHERE nom_client=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, client);
            
            ResultSet resultSet = statement.executeQuery();

            double total = 0;
            StringBuilder sb = new StringBuilder();
            sb.append("le client :").append(client).append("\n\n");
            
            while (resultSet.next()) {
                String medication = resultSet.getString("name_medicament");
                double prix = resultSet.getDouble("prix");
                total += prix;
                sb.append(medication).append(": \t\t\t ").append(prix).append("\n");
            }
			textAreaListeMedic.setText(sb.toString());

            // Afficher le total des prix
            txtTotale.setText(total+" dh");

            // Fermer les connexions et les ressources
            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des médicaments !");
        }
    }

	/**
	 * Create the application.
	 */
	public ListeMedicClient() {
		initialize();
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(0, 175, 310, 424);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("entrez ke nom de client ");
		lblNewLabel.setBounds(122, 27, 148, 14);
		panel_2.add(lblNewLabel);
		
		txtNom = new JTextField();
		txtNom.setBounds(97, 163, 96, 20);
		panel_2.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("nom de client");
		lblNewLabel_1.setBounds(114, 119, 89, 14);
		panel_2.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("afficher la liste des medicaments");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  String client = txtNom.getText();
	                if (!client.isEmpty()) {
	                    afficherListeMedicaments(client);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Veuillez saisir le nom du client !");
	                }
			}
		});
		btnNewButton.setBounds(42, 288, 201, 60);
		panel_2.add(btnNewButton);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.GRAY);
		panel_2_1.setBounds(346, 175, 310, 424);
		frame.getContentPane().add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblLaListeDe = new JLabel("la liste de medicaments de ce client");
		lblLaListeDe.setBounds(88, 27, 195, 14);
		panel_2_1.add(lblLaListeDe);
		
		textAreaListeMedic.setBounds(40, 72, 244, 313);
		panel_2_1.add(textAreaListeMedic);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.GRAY);
		panel_2_1_1.setBounds(690, 175, 310, 424);
		frame.getContentPane().add(panel_2_1_1);
		panel_2_1_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("status de paiement");
		lblNewLabel_1_1.setBounds(131, 26, 151, 14);
		panel_2_1_1.add(lblNewLabel_1_1);
		
		txtTotale = new JTextField();
		txtTotale.setBounds(116, 92, 96, 20);
		panel_2_1_1.add(txtTotale);
		txtTotale.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("totale");
		lblNewLabel_2.setBounds(25, 95, 49, 14);
		panel_2_1_1.add(lblNewLabel_2);
	}
}
