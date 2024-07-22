package pharma;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connections.Connecteur;

public class Requetes {

	Connecteur con=new Connecteur();
	Statement st;
	ResultSet rst;
	JButton bdepot,bretrait,bstock,bexpire;
	 JLabel lbmontant,lbmontant2;
	JComboBox jcbjour;
	JTable tb;
	JScrollPane scrl;
	public void requete(JPanel pn){
		
		bdepot=new JButton("Depot");
		bdepot.setBounds(210,10,90,25);
		bdepot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a=jcbjour.getSelectedItem().toString();
				String kk="select * from mouvement where nature='Depot' and jour='"+a+"'";
						
						
						 
				DefaultTableModel df=new DefaultTableModel();
				init();
				df.addColumn("jour_depot");
				df.addColumn("code");
				df.addColumn("nom");
				df.addColumn("prix_unitaire");
				df.addColumn("quantite");
				df.addColumn("date_expiration");
				df.addColumn("nature");
				tb.setModel(df);
				pn.add(scrl);
				
				try{
					st=con.connect().createStatement();
					rst=st.executeQuery(kk);
					while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("jour"),
						rst.getString("code"),
						rst.getString("nom"),
						rst.getString("prix"),
						rst.getString("quantite"),
						rst.getString("datexp"),
						rst.getString("nature")
				});
					}
				}
				catch(SQLException ex){
					
				}
						
			}
		});
		pn.add(bdepot);
		
		bretrait=new JButton("Retrait");
		bretrait.setBounds(110,10,90,25);
		bretrait.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a=jcbjour.getSelectedItem().toString();
				String kk="select * from mouvement where nature='Retrait' and jour='"+a+"'";
						
						
						 
				DefaultTableModel df=new DefaultTableModel();
				init();
				df.addColumn("jour_retrait");
				df.addColumn("code");
				df.addColumn("nom");
				df.addColumn("prix_unitaire");
				df.addColumn("quantite");
				df.addColumn("date_expiration");
				df.addColumn("nature");
				tb.setModel(df);
				pn.add(scrl);
				try{
					st=con.connect().createStatement();
					rst=st.executeQuery(kk);
					while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("jour"),
						rst.getString("code"),
						rst.getString("nom"),
						rst.getString("prix"),
						rst.getString("quantite"),
						rst.getString("datexp"),
						rst.getString("nature")
				});
					}
				}
				catch(SQLException ex){
					
				}
						
			}
		});//
		pn.add(bretrait);
		///////////////////////////////////////////
		
		bstock=new JButton("Stock");
		bstock.setBounds(500,10,90,25);
		bstock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 
			    
	String kk="select code,nom,prix ,prix*sum(quantite) as montant,datexp,sum(quantite) as stock"
						+ " from vue1 group by nom ";		 
				DefaultTableModel df=new DefaultTableModel();
				init();
				df.addColumn("code");
				df.addColumn("nom");
				df.addColumn("prix_unitaire");
				df.addColumn("quantite");
				df.addColumn("Montant");
				df.addColumn("date_expiration");
				
				
				tb.setModel(df);
				pn.add(scrl);
				pn.add(lbmontant);
				try{
					st=con.connect().createStatement();
					rst=st.executeQuery(kk);
					while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("code"),
						rst.getString("nom"),
						rst.getString("prix"),
						rst.getString("stock"),
						rst.getString("montant"),
						rst.getString("datexp")
						
						
				});
					}
				}
				catch(SQLException ex){
					
				}
				String kk2="select sum(montant) as total from vuemontant  ";
				try{
					st=con.connect().createStatement();
					rst=st.executeQuery(kk2);
					if(rst.next()){
				lbmontant2.setText(rst.getString("total"));
					}
				}
				catch(SQLException ex){
					
				}
						
			}
		});///
		pn.add(bstock);
		
		bexpire=new JButton("Périmés");
		bexpire.setBounds(310,10,90,25);
		bexpire.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a=jcbjour.getSelectedItem().toString();
          String kk="select code,nom,prix,stock,datexp,"
		+ "case when datexp<'"+a+"' then 'Périmé' else "
		+ "'Pas encore' end as verification  from vuedate where stock>0 ";
				/*String kk="select code,nom,prix,stock,datexp,"
						+ "case when datexp<'"+a+"' then 'Périmé'  "
						+ " end as verification  from vuedate where stock>0 ";*/
				
				 
				DefaultTableModel df=new DefaultTableModel();
				init();
				df.addColumn("code");
				df.addColumn("nom");
				df.addColumn("prix_unitaire");
				df.addColumn("quantite");
				df.addColumn("date_expiration");
				df.addColumn("verification");
				tb.setModel(df);
				pn.add(scrl);
				
				try{
					st=con.connect().createStatement();
					rst=st.executeQuery(kk);
					while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("code"),
						rst.getString("nom"),
						rst.getString("prix"),
						rst.getString("stock"),
						rst.getString("datexp"),
						rst.getString("verification")
				});
					}
				}
				catch(SQLException ex){
					
				}
						
			}
		});
		pn.add(bexpire);
		
		
		jcbjour=new JComboBox();
		jcbjour.setBounds(10,10,90,25);
		pn.add(jcbjour);

		lbmontant=new JLabel("Montant total des produits en stock :");
		lbmontant.setBounds(30,270,300,25);
		pn.add(lbmontant);
		
		lbmontant2=new JLabel();
		lbmontant2.setBounds(250,270,100,25);
		lbmontant2.setForeground(Color.blue);
		lbmontant2.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbmontant2);
		//comboAffichageJour
		String kk3="select * from combojour";
		try{
			st=con.connect().createStatement();
			rst=st.executeQuery(kk3);
			while(rst.next()){
				
				jcbjour.addItem(rst.getString("date"));
	
			}
		}
		catch(SQLException ex){
			
		}
		//
	}
	public void init(){
		tb=new JTable();
		
		
		scrl=new JScrollPane();
		scrl.setViewportView(tb);
		scrl.setBounds(10,60,900,200);
		;
	}
	
	
}
