package pharma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import combo.Combo1;
import connections.Connecteur;





public class AffichagePanel1  {
	Statement st;
	ResultSet rst;
	Connecteur cn=new Connecteur();
	JLabel titre1,lab1,lab2,lab3,lab4,lab5,labnature,labjour;
	JTextField tfcode,tfnom,tfprix,tfquantite,tfdate,tfrech; 
	JRadioButton jr1,jr2;
	JButton bajout,bsupp,bmodif,brech,bnew,bliste;
	JButton bdebut,bfin,bsuiv,bprec;
	int pos=0;
	JTable table;
	JComboBox jcbcode,jcbnom,jcbdate,jcbjour;
	JScrollPane scroll;
	Combo1 cb=new Combo1();

	//getConnexion()
	public static Connection getConnection(){
		Connection cn;
		try{
			cn=DriverManager.getConnection("jdbc:mysql://localhost/pharmabase","root","");
		 return cn;
		}
		catch(Exception ex){
			
		}
		return null;
	}
	//bindlist()
		public static List<Mouvement> bindlist(){
			
			try{
				Connection con=getConnection();
				Statement st=con.createStatement();
				ResultSet rst=st.executeQuery("select * from mouvement");
				List<Mouvement> liste=new ArrayList<Mouvement>();
				while(rst.next()){
				Mouvement mv=new Mouvement(
						Integer.parseInt(rst.getString("id")),
						Integer.parseInt(rst.getString("prix")),
						Integer.parseInt(rst.getString("quantite")),
						rst.getString("code"),
						rst.getString("nom"),
						rst.getString("datexp"),
						rst.getString("nature"),
						rst.getString("jour")
						);
				liste.add(mv);
				}
				return liste;
			}
			catch(SQLException  ex){
				
			}
			return null;
		}
		//showposinfo()
		public void showposinfo(int index){	
			tfrech.setText(Integer.toString(bindlist().get(index).getId()));
			jcbcode.setSelectedItem(bindlist().get(index).getCode());
			jcbnom.setSelectedItem(bindlist().get(index).getNom());
			tfprix.setText(Integer.toString(bindlist().get(index).getPrix()));
			tfquantite.setText(Integer.toString(bindlist().get(index).getQuantite()));
			jcbdate.setSelectedItem(bindlist().get(index).getDate());
			jcbjour.setSelectedItem(bindlist().get(index).getJour());
			if(bindlist().get(index).getNature().equals("Depot"))
				jr1.setSelected(true);
			else jr2.setSelected(true);
			
		}
	public void Panel1(JPanel pn){
	
/*		this.setSize(400,667);
		this.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setLayout(null);
*/		
		//boutons defilement
		bdebut=new JButton("Debut");
		bdebut.setBounds(20,70,70,20);
		bdebut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pos=0;
				showposinfo(pos);
			}
		});
		pn.add(bdebut);
		
		bsuiv=new JButton("Suiv>");
		bsuiv.setBounds(100,70,70,20);
		bsuiv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pos++;
				if(pos<bindlist().size()){
					showposinfo(pos);
					
				}
				else
					pos=bindlist().size()-1;
				showposinfo(pos);
			}
		});
		pn.add(bsuiv);
		
		bprec=new JButton("<Prec");
		bprec.setBounds(180,70,70,20);
		bprec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pos--;
				if(pos>0){
					showposinfo(pos);
					
				}
				else
					pos=0;
				showposinfo(pos);
			}
		});
		pn.add(bprec);
		
		bfin=new JButton("Fin");
		bfin.setBounds(260,70,70,20);
		bfin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pos=bindlist().size()-1;
				showposinfo(pos);
			}
		});
		pn.add(bfin);
		///////////////////
		//titre1
		titre1=new JLabel("Dépot médicaments / Retrait  médicaments");
		titre1.setBounds(30,5,300,30);
		pn.add(titre1);
		bliste=new JButton("Configurer les listes déroulantes");
		bliste.setBounds(30,35,300,25);
		bliste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Combo1 cb=new Combo1();
				cb.setVisible(true);
			}
		});
		pn.add(bliste);
		//code
		lab1=new JLabel("Code");
		lab1.setBounds(90,100, 110, 25);
		pn.add(lab1);

		jcbcode=new JComboBox();
		jcbcode.setBounds(150,100,110,25);
		pn.add(jcbcode);
		
		
		//nom
		lab2=new JLabel("Nom");
		lab2.setBounds(90,140, 110, 25);
		pn.add(lab2);
		//
		
		jcbnom=new JComboBox();
		jcbnom.setBounds(150,140,150,25);
		pn.add(jcbnom);
		//prix
		lab3=new JLabel("Prix");
		lab3.setBounds(90,180, 110, 25);
		pn.add(lab3);
		//
		tfprix=new JTextField();
		tfprix.setBounds(150,180, 110, 25);
		pn.add(tfprix);
		//quantite
		lab4=new JLabel("Quantité");
		lab4.setBounds(65,220, 110, 25);
		pn.add(lab4);
		//
		tfquantite=new JTextField();
		tfquantite.setBounds(150,220, 110, 25);
		pn.add(tfquantite);
		//date
		lab5=new JLabel("Date_péremtion");
		lab5.setBounds(20,260, 110, 25);
		pn.add(lab5);
		//
		jcbdate=new JComboBox();
		jcbdate.setBounds(150,260,110,25);
		pn.add(jcbdate);
		//nature
		labnature=new JLabel("Nature");
		labnature.setBounds(65,300, 110, 25);
		pn.add(labnature);
		//checkBox
		jr1=new JRadioButton("Depot");
		jr1.setBounds(150,300, 80, 25);
		pn.add(jr1);
		
		jr2=new JRadioButton("Retrait");
		jr2.setBounds(240,300, 80, 25);
		pn.add(jr2);
		ButtonGroup bg=new ButtonGroup();
		bg.add(jr1);
		bg.add(jr2);
		
		
		labjour=new JLabel("Date_jour");
		labjour.setBounds(55,340, 110, 25);
		pn.add(labjour);
		
		//
		jcbjour=new JComboBox();
		jcbjour.setBounds(150,340,110,25);
		pn.add(jcbjour);
		
		//bouton
		bajout=new JButton("Ajouter");
		bajout.setBounds(20,400,90,25);
		bajout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a,b,c,d,f,g,h;
				a=jcbcode.getSelectedItem().toString();
				b=jcbnom.getSelectedItem().toString();
				c=tfprix.getText();
				d=tfquantite.getText();
				f=jcbdate.getSelectedItem().toString();
				
				if(jr1.isSelected()) g=jr1.getText();else g=jr2.getText();
				h=jcbjour.getSelectedItem().toString();
		//String kk="insert into mouvement values('NULL','"+a+"','"+b+"','"+c+"','"+d+"','"+f+"','"+g+"')";
		String qr="insert into mouvement values(NULL,'"+a+"','"+b+"','"+c+"','"+d+"','"+f+"','"+g+"','"+h+"')";
				try{
					st=cn.connect().createStatement();
					st.executeUpdate(qr);
					JOptionPane.showMessageDialog(null,"Insertion reussie !");
					
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Echec de l'insertion !",null,JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		pn.add(bajout);
		
		bsupp=new JButton("Supprimer");
		bsupp.setBounds(130,400,100,25);
		bsupp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a=tfrech.getText();
				String qr="delete from mouvement where id='"+a+"'";
				try{
					st=cn.connect().createStatement();
					
					if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						st.executeUpdate(qr);
						JOptionPane.showMessageDialog(null,"Suppression reussie !");
					}
				}
				
				
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Echec de la suppréssion !",null,JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pn.add(bsupp);
		
		bmodif=new JButton("Modifier");
		bmodif.setBounds(250,400,100,25);
		
		bmodif.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a,b,c,d,f,g,h,i;
				a=jcbcode.getSelectedItem().toString();
				b=jcbnom.getSelectedItem().toString();
				c=tfprix.getText();
				d=tfquantite.getText();
				f=jcbdate.getSelectedItem().toString();
				if(jr1.isSelected())g=jr1.getText();else{g=jr2.getText();}
				
				h=tfrech.getText();
				i=jcbjour.getSelectedItem().toString();
	String kk="update mouvement set code='"+a+"',nom='"+b+"',prix='"+c+"',quantite='"+d+"',datexp='"+f+"',nature='"+g+"',jour='"+i+"' where id='"+h+"'";
				try{
					st=cn.connect().createStatement();
					st.executeUpdate(kk);
		JOptionPane.showMessageDialog(null,"Modification reussie !");
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Erreur modification !",null,JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		pn.add(bmodif);
		
		/*bnew=new JButton("Nouveau");
		bnew.setBounds(250,440,100,25);
		bnew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				AffichagePanel1 aff=new AffichagePanel1();
				aff.setVisible(true);
				
			}
		});
		pn.add(bnew);*/
		
		brech=new JButton("Chercher");
		brech.setBounds(20,450,90,25);
		brech.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				String a=tfrech.getText();
				String rq="select * from mouvement where id='"+a+"'";
				try{
					st=cn.connect().createStatement();
					rst=st.executeQuery(rq);
					if(rst.next()){
						//aff1.getTfcode().setText(rst.getString("code"));
						jcbcode.setSelectedItem(rst.getString("code"));
						//aff1.getTfnom().setText(rst.getString("nom"));
						jcbnom.setSelectedItem(rst.getString("nom"));
						tfprix.setText(rst.getString("prix"));
						tfquantite.setText(rst.getString("quantite"));
						jcbdate.setSelectedItem(rst.getString("datexp"));
						if(rst.getString("nature").equals("Depot")){
							jr1.setSelected(true);
						}else{
							jr2.setSelected(true);
						}
						jcbjour.setSelectedItem(rst.getString("jour"));
					}
					else{
						JOptionPane.showMessageDialog(null,"Données introuvables !",null,JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(SQLException ex){
					
				}
			}
		});
		
		pn.add(brech);
		
		tfrech=new JTextField("  id");
		tfrech.setBounds(120,450,50,25);
		pn.add(tfrech);
		//
		//affichage CRUD
				DefaultTableModel df=new DefaultTableModel();
				init();
				df.addColumn("id");
				df.addColumn("Code");
				df.addColumn("Quantité");
				df.addColumn("Nature");
				df.addColumn("Jour");
				table.setModel(df);
				pn.add(scroll);
				String qq="select * from mouvement";
				try{
					st=cn.connect().createStatement();
					rst=st.executeQuery(qq);
					while(rst.next()){
						df.addRow(new Object[]{
			rst.getString("id"),rst.getString("code"),rst.getString("quantite"),rst.getString("nature"),rst.getString("jour")
						});
					}
					
				}
				catch(Exception ex){
					
				}
				//comboAffichageCode
				String kk="select * from comboCode";
				try{
					st=cn.connect().createStatement();
					rst=st.executeQuery(kk);
					while(rst.next()){
						jcbcode.addItem(rst.getString("code"));
			
					}
				}
				catch(SQLException ex){
					
				}
				//comboAffichageNom
				String kk2="select * from combonom";
				try{
					st=cn.connect().createStatement();
					rst=st.executeQuery(kk2);
					while(rst.next()){
						jcbnom.addItem(rst.getString("nom"));
			
					}
				}
				catch(SQLException ex){
					
				}
				//comboAffichageDate
				String kk3="select * from combodate";
				try{
					st=cn.connect().createStatement();
					rst=st.executeQuery(kk3);
					while(rst.next()){
						jcbdate.addItem(rst.getString("date"));
			
					}
				}
				catch(SQLException ex){
					
				}
				//comboAffichageJour
				String kk4="select * from combojour";
				try{
					st=cn.connect().createStatement();
					rst=st.executeQuery(kk4);
					while(rst.next()){
						jcbjour.addItem(rst.getString("date"));
			
					}
				}
				catch(SQLException ex){
					
				}
	}
	///////////////////////
		 private void  init(){
			   table=new JTable();
			   scroll=new JScrollPane();
			   scroll.setViewportView(table);
			   scroll.setBounds(10,500,370,150);
		   }

}
