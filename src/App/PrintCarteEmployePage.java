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

import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrintCarteEmployePage {

	JFrame frame;
	private JTable tb_details_employe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintCarteEmployePage window = new PrintCarteEmployePage();
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
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtPoste;
	private JTextField txtDateEmbauche;
	private JTextField txtId;

	/**
	 * Create the application.
	 */
	public PrintCarteEmployePage() {
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
	
	
	
			
			//mthd to print a panel
			public void printRecord(JPanel panel) {
				PrinterJob printJob = PrinterJob.getPrinterJob();
		        printJob.setPrintable(new Printable() {
		            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		                if (pageIndex > 0) {
		                    return Printable.NO_SUCH_PAGE;
		                }

		                Graphics2D g2d = (Graphics2D) graphics;
		                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		                panel.printAll(g2d);

		                return Printable.PAGE_EXISTS;
		            }
		        });

		        if (printJob.printDialog()) {
		            try {
		                printJob.print();
		            } catch (PrinterException ex) {
		                ex.printStackTrace();
		            }
		        }
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
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmplyeePage emplyeePage = new EmplyeePage();
				emplyeePage.frame.setVisible(true);
			}
		});
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(0, 134, 499, 58);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ajouter un emplyée");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(204, 22, 158, 14);
		panel_2.add(lblNewLabel);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(0, 0, 0));
		panel_2_1.setBounds(501, 134, 499, 58);
		frame.getContentPane().add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("print la carte d'employée");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(231, 21, 139, 14);
		panel_2_1.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(105, 105, 105));
		panel_3.setBounds(0, 189, 499, 410);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 109, 469, 135);
		panel_3.add(scrollPane);
		
		tb_details_employe = new JTable();
		tb_details_employe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rownbr = tb_details_employe.getSelectedRow();
				TableModel model = tb_details_employe.getModel();
				
				txtId.setText(model.getValueAt(rownbr, 0).toString());
				txtNom.setText(model.getValueAt(rownbr, 1).toString());
				txtPrenom.setText(model.getValueAt(rownbr, 2).toString());
				
				txtPoste.setText(model.getValueAt(rownbr, 4).toString());
				
				txtDateEmbauche.setText(model.getValueAt(rownbr, 6).toString());

			
			}
		});
		tb_details_employe.setBackground(new Color(128, 128, 128));
		tb_details_employe.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "nom", "prenom", "salaire", "poste", "adress", "date embauche"
			}
		));
		scrollPane.setViewportView(tb_details_employe);
		
		JPanel cartePanel = new JPanel();
		cartePanel.setBackground(new Color(128, 128, 128));
		cartePanel.setBounds(511, 203, 489, 215);
		frame.getContentPane().add(cartePanel);
		cartePanel.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 11, 102, 97);
		cartePanel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel labelImage = new JLabel("");
		labelImage.setBounds(0, 0, 102, 97);
		panel_5.add(labelImage);
		
		JLabel lblNewLabel_3 = new JLabel("nom :");
		lblNewLabel_3.setBounds(144, 11, 49, 14);
		cartePanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("prenom :");
		lblNewLabel_3_1.setBounds(144, 36, 49, 14);
		cartePanel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("poste :");
		lblNewLabel_3_2.setBounds(144, 57, 49, 14);
		cartePanel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Date d'embauche :");
		lblNewLabel_3_3.setBounds(144, 82, 93, 14);
		cartePanel.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("ID :");
		lblNewLabel_3_3_1.setBounds(10, 119, 26, 14);
		cartePanel.add(lblNewLabel_3_3_1);
		
		txtNom = new JTextField();
		txtNom.setForeground(new Color(0, 0, 0));
		txtNom.setBackground(new Color(128, 128, 128));
		txtNom.setBorder(null);
		txtNom.setBounds(256, 11, 102, 14);
		cartePanel.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setForeground(new Color(0, 0, 0));
		txtPrenom.setColumns(10);
		txtPrenom.setBorder(null);
		txtPrenom.setBackground(new Color(128, 128, 128));
		txtPrenom.setBounds(256, 36, 102, 14);
		cartePanel.add(txtPrenom);
		
		txtPoste = new JTextField();
		txtPoste.setForeground(new Color(0, 0, 0));
		txtPoste.setColumns(10);
		txtPoste.setBorder(null);
		txtPoste.setBackground(new Color(128, 128, 128));
		txtPoste.setBounds(256, 57, 102, 14);
		cartePanel.add(txtPoste);
		
		txtDateEmbauche = new JTextField();
		txtDateEmbauche.setForeground(new Color(0, 0, 0));
		txtDateEmbauche.setColumns(10);
		txtDateEmbauche.setBorder(null);
		txtDateEmbauche.setBackground(new Color(128, 128, 128));
		txtDateEmbauche.setBounds(256, 79, 102, 14);
		cartePanel.add(txtDateEmbauche);
		
		txtId = new JTextField();
		txtId.setForeground(new Color(0, 0, 0));
		txtId.setColumns(10);
		txtId.setBorder(null);
		txtId.setBackground(new Color(128, 128, 128));
		txtId.setBounds(42, 119, 102, 14);
		cartePanel.add(txtId);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			printRecord(cartePanel);
			}
		});
		btnPrint.setBounds(731, 476, 219, 58);
		frame.getContentPane().add(btnPrint);
		
		JButton btnNewButton = new JButton("import image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select an Image File");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif"));

                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        Image image = ImageIO.read(selectedFile);
                        labelImage.setIcon(new ImageIcon(image));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
			}
		});
		btnNewButton.setBounds(520, 476, 143, 58);
		frame.getContentPane().add(btnNewButton);
	}
}
