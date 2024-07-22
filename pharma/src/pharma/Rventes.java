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
import javax.swing.table.DefaultTableModel;

import connections.Connecteur;

public class Rventes  {
	Connecteur con=new Connecteur();
	Statement st;
	ResultSet rst;
	JButton bvente,bvente2;
	JComboBox jcbjour,jcbcode;
	JLabel lbvente1,lbvente2,lbvente3,lbvente4;
	JTable tb;
	JScrollPane sp;
	public void rventes(JPanel pn){
		lbvente1=new JLabel("Le montant des ventes éffectuées à la date du ");
		lbvente1.setBounds(30,310,280,25);
		pn.add(lbvente1);
		
		lbvente2=new JLabel("2018-08-09");
		lbvente2.setBounds(310,310,90,25);
		lbvente2.setForeground(Color.blue);
		pn.add(lbvente2);
		
		lbvente3=new JLabel("est de ");
		lbvente3.setBounds(395,310,70,25);
		pn.add(lbvente3);
		
		lbvente4=new JLabel();
		lbvente4.setBounds(450,310,70,25);
		lbvente4.setForeground(Color.blue);
		lbvente4.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbvente4);
		
		bvente=new JButton("Ventes");
		bvente.setBounds(10,10,90,25);
		bvente.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a=jcbjour.getSelectedItem().toString();
	String kk="select * from vuevente where jour='"+a+"'  order by quantite desc";
						
						
						 
				DefaultTableModel df1=new DefaultTableModel();
				init();
				
				df1.addColumn("code");
				df1.addColumn("nom");
				df1.addColumn("prix_unitaire");
				df1.addColumn("quantite");
				df1.addColumn("montant");
				df1.addColumn("date_jour");
				
				tb.setModel(df1);
				pn.add(sp);
				
				try{
					st=con.connect().createStatement();
					rst=st.executeQuery(kk);
					while(rst.next()){
				df1.addRow(new Object[]{
						
						rst.getString("code"),
						rst.getString("nom"),
						rst.getString("prix"),
						rst.getString("quantite"),
						rst.getString("montant"),
						rst.getString("jour")
				});
					}
					lbvente2.setText(jcbjour.getSelectedItem().toString());
				}
				catch(SQLException ex){
					
				}
				///
		String qq="select sum(montant) as montant from vuevente where jour='"+a+"'";
				try{
					st=con.connect().createStatement();
					rst=st.executeQuery(qq);
					if(rst.next()){
				lbvente4.setText(rst.getString("montant"));
					}
					lbvente2.setText(jcbjour.getSelectedItem().toString());
				}
				catch(SQLException ex){
					
				}
				
				///
						
			}
		});
		pn.add(bvente);
		
		//////
		bvente2=new JButton("Ventes");
		bvente2.setBounds(310,10,90,25);
		bvente2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a=jcbcode.getSelectedItem().toString();
	String kk="select * from vuevente where code='"+a+"'  order by quantite desc";
						
						
						 
				DefaultTableModel df1=new DefaultTableModel();
				init();
				
				df1.addColumn("code");
				df1.addColumn("nom");
				df1.addColumn("prix_unitaire");
				df1.addColumn("quantite");
				df1.addColumn("montant");
				df1.addColumn("date_jour");
				
				tb.setModel(df1);
				pn.add(sp);
				
				try{
					st=con.connect().createStatement();
					rst=st.executeQuery(kk);
					while(rst.next()){
				df1.addRow(new Object[]{
						
						rst.getString("code"),
						rst.getString("nom"),
						rst.getString("prix"),
						rst.getString("quantite"),
						rst.getString("montant"),
						rst.getString("jour")
				});
					}
				}
				catch(SQLException ex){
					
				}
						
			}
		});
		pn.add(bvente2);
		
		//jcb
		jcbjour=new JComboBox();
		jcbjour.setBounds(110,10,100,25);
		pn.add(jcbjour);
		
		jcbcode=new JComboBox();
		jcbcode.setBounds(410,10,100,25);
		pn.add(jcbcode);
		
		
		
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
		//comboAffichageCode
				String qr="select * from combocode";
				try{
					st=con.connect().createStatement();
					rst=st.executeQuery(qr);
					while(rst.next()){
						
						jcbcode.addItem(rst.getString("code"));
			
					}
				}
				catch(SQLException ex){
					
				}
				
		
	}
	private void init(){
		tb=new JTable();
		sp=new JScrollPane();
		sp.setViewportView(tb);
		sp.setBounds(10,50,900,250);
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
    Rventes rv=new Rventes();
    rv.setVisible(true);
	}*/

}
