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
import javax.swing.JTextField;

public class HomePageAdmin {

	JFrame frame;
	JTextField txtWelcome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePageAdmin window = new HomePageAdmin();
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
	public HomePageAdmin() {
		initialize();
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
		
		JLabel lblNewLabel_1 = new JLabel("ESPACE ADMIN ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(433, 27, 260, 41);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(64, 128, 128));
		panel_1.setBounds(0, 108, 1000, 55);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2_1_2 = new JPanel();
		panel_2_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			ProfileAdmin profileAdmin = new ProfileAdmin();
			profileAdmin.frame.setVisible(true);
			profileAdmin.txtwelcomProfile.setText(txtWelcome.getText());
			
			}
		});
		
		panel_2_1_2.setBounds(778, 0, 222, 55);
		panel_1.add(panel_2_1_2);
		panel_2_1_2.setBackground(new Color(192, 192, 192));
		panel_2_1_2.setLayout(null);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Profile");
		lblNewLabel_2_2_1_1.setBounds(84, 11, 49, 14);
		panel_2_1_2.add(lblNewLabel_2_2_1_1);
		
		txtWelcome = new JTextField();
		txtWelcome.setBorder(null);
		txtWelcome.setBackground(new Color(192, 192, 192));
		txtWelcome.setBounds(126, 8, 96, 20);
		panel_2_1_2.add(txtWelcome);
		txtWelcome.setEditable(false);
		txtWelcome.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			GestionUser gestionUser = new GestionUser();
			gestionUser.frame.setVisible(true);
			}
		});
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(218, 214, 222, 55);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion User");
		lblNewLabel.setBounds(83, 11, 84, 14);
		panel_2.add(lblNewLabel);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			GestionAdmin gestionAdmin = new GestionAdmin();
			gestionAdmin.frame.setVisible(true);
			}
		});
		panel_2_1.setBackground(Color.GRAY);
		panel_2_1.setBounds(538, 214, 222, 55);
		frame.getContentPane().add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Ajouter un admin");
		lblNewLabel_2.setBounds(80, 11, 108, 14);
		panel_2_1.add(lblNewLabel_2);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			ViewAlAdmin viewAlAdmin = new ViewAlAdmin();
			viewAlAdmin.frame.setVisible(true);
			}
		});
		panel_2_1_1.setBackground(Color.GRAY);
		panel_2_1_1.setBounds(538, 327, 222, 55);
		frame.getContentPane().add(panel_2_1_1);
		panel_2_1_1.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("voire tous les admins");
		lblNewLabel_2_2.setBounds(73, 11, 139, 14);
		panel_2_1_1.add(lblNewLabel_2_2);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			ViewAllUser viewAllUser = new ViewAllUser();
			viewAllUser.frame.setVisible(true);
			}
		});
		panel_2_2.setBackground(Color.GRAY);
		panel_2_2.setBounds(218, 327, 222, 55);
		frame.getContentPane().add(panel_2_2);
		panel_2_2.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("voire tous les utilisateurs");
		lblNewLabel_2_1.setBounds(55, 11, 131, 14);
		panel_2_2.add(lblNewLabel_2_1);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(frame, "confirm if you want to exit","print", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION ) {
					FirstPage firstPage = new FirstPage();
					firstPage.frame.setVisible(true);
					}
			}
		});
		panel_2_3.setBackground(new Color(255, 0, 0));
		panel_2_3.setBounds(387, 440, 222, 55);
		frame.getContentPane().add(panel_2_3);
		panel_2_3.setLayout(null);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Logout");
		lblNewLabel_2_2_1.setBounds(82, 11, 49, 14);
		panel_2_3.add(lblNewLabel_2_2_1);
	}
}
