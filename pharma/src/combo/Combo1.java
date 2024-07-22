package combo;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connections.Connecteur;
import pharma.Adminis;
import pharma.AffichagePanel1;

public class Combo1 extends JFrame implements ActionListener  {
	//Adminis ad=new Adminis();
	JTextField tf;
	JButton bajout,bsupp,bajout2,bsupp2,bajout3,bsupp3,bajout_jour,bsupp_jour;
	JComboBox jcb;
	Statement st;
	ResultSet rst;
	Connecteur cn=new Connecteur();
	JPanel pn;
	JLabel lbcode,lbnom,lbdate,lbdatej;
	public Combo1(){
		this.setTitle("Reglage listes déroulantes");
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		 pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		
		tf=new JTextField();
		tf.setBounds(50,30,90,25);
		pn.add(tf);
		//bouton code
		bajout=new JButton("Ajouter");
		bajout.setBounds(60,70,80,25);
		bajout.addActionListener(this);
		pn.add(bajout);
		
		bsupp=new JButton("Suppression");
		bsupp.setBounds(160,70,110,25);
		bsupp.addActionListener(this);
		pn.add(bsupp);
		//label code
		lbcode=new JLabel("Code");
		lbcode.setBounds(10,70,100,25);
		pn.add(lbcode);
		//bouton nom
		bajout2=new JButton("Ajouter");
		bajout2.setBounds(60,110,80,25);
		bajout2.addActionListener(this);
		pn.add(bajout2);
		
		bsupp2=new JButton("Suppression");
		bsupp2.setBounds(160,110,110,25);
		bsupp2.addActionListener(this);
		pn.add(bsupp2);
		
		//label nom
				lbnom=new JLabel("Nom");
				lbnom.setBounds(10,110,100,25);
				pn.add(lbnom);
	  //bouton datexp
				bajout3=new JButton("Ajouter");
				bajout3.setBounds(80,150,80,25);
				bajout3.addActionListener(this);
				pn.add(bajout3);
				
				bsupp3=new JButton("Suppression");
				bsupp3.setBounds(180,150,110,25);
				bsupp3.addActionListener(this);
				pn.add(bsupp3);
				//label date
				lbdate=new JLabel("Date_exp");
				lbdate.setBounds(10,150,100,25);
				pn.add(lbdate);
	//bouton datejour
				bajout_jour=new JButton("Ajouter");
				bajout_jour.setBounds(80,190,80,25);
				bajout_jour.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String a=tf.getText();
						String qq="insert into combojour values(NULL,'"+a+"')";
						try{
							st=cn.connect().createStatement();
							st.executeUpdate(qq);
							JOptionPane.showMessageDialog(null,"Insertion reussi !");
						}
						catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Echec insertion !",null,JOptionPane.ERROR_MESSAGE);
						}
						dispose();
						Combo1 cb=new Combo1();
						cb.setVisible(true);
						
					}
				});
				pn.add(bajout_jour);
				
				bsupp_jour=new JButton("Suppression");
				bsupp_jour.setBounds(180,190,110,25);
				bsupp_jour.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String a=tf.getText();
						String qq="delete from combojour where date='"+a+"'";
						try{
							st=cn.connect().createStatement();
							if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
								st.executeUpdate(qq);
								JOptionPane.showMessageDialog(null,"suppression reussi !");
							}
							
						}
						catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Echec suppression !",null,JOptionPane.ERROR_MESSAGE);
						}
						dispose();
						Combo1 cb=new Combo1();
						cb.setVisible(true);
						
					}
				});
				pn.add(bsupp_jour);
				//label date
				lbdatej=new JLabel("Date_jour");
				lbdatej.setBounds(10,190,100,25);
				pn.add(lbdatej);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  Combo1 combo=new Combo1();
   combo.setVisible(true);
	}

	@Override
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//ajout_code
		if(e.getSource()==bajout){
			String a=tf.getText();
			String qq="insert into comboCode values(NULL,'"+a+"')";
			try{
				st=cn.connect().createStatement();
				st.executeUpdate(qq);
				JOptionPane.showMessageDialog(this,"Insertion reussi !");
			}
			catch(SQLException ex){
				JOptionPane.showMessageDialog(this,"Echec insertion !",null,JOptionPane.ERROR_MESSAGE);
			}
			dispose();
			Combo1 cb=new Combo1();
			cb.setVisible(true);
		}
	//suppression_code
		if(e.getSource()==bsupp){
			String a=tf.getText();
			String qq="delete from comboCode where code='"+a+"'";
			try{
				st=cn.connect().createStatement();
				if(JOptionPane.showConfirmDialog(this,"Voulez-vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					st.executeUpdate(qq);
					JOptionPane.showMessageDialog(this,"suppression reussi !");
				}
				
			}
			catch(SQLException ex){
				JOptionPane.showMessageDialog(this,"Echec suppression !",null,JOptionPane.ERROR_MESSAGE);
			}
			dispose();
			Combo1 cb=new Combo1();
			cb.setVisible(true);
			//	
		}
		//ajout_nom
				if(e.getSource()==bajout2){
					String a=tf.getText();
					String qq="insert into combonom values(NULL,'"+a+"')";
					try{
						st=cn.connect().createStatement();
						st.executeUpdate(qq);
						JOptionPane.showMessageDialog(this,"Insertion reussi !");
					}
					catch(SQLException ex){
						JOptionPane.showMessageDialog(this,"Echec insertion !",null,JOptionPane.ERROR_MESSAGE);
					}
					dispose();
					Combo1 cb=new Combo1();
					cb.setVisible(true);
				}
				//suppression_nom
				if(e.getSource()==bsupp2){
					String a=tf.getText();
					String qq="delete from combonom where nom='"+a+"'";
					try{
						st=cn.connect().createStatement();
						if(JOptionPane.showConfirmDialog(this,"Voulez-vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							st.executeUpdate(qq);
							JOptionPane.showMessageDialog(this,"suppression reussi !");
						}
						
					}
					catch(SQLException ex){
						JOptionPane.showMessageDialog(this,"Echec suppression !",null,JOptionPane.ERROR_MESSAGE);
					}
					dispose();
					Combo1 cb=new Combo1();
					cb.setVisible(true);
						
				}
		//ajout_date
				//ajout_nom
				if(e.getSource()==bajout3){
					String a=tf.getText();
					String qq="insert into combodate values(NULL,'"+a+"')";
					try{
						st=cn.connect().createStatement();
						st.executeUpdate(qq);
						JOptionPane.showMessageDialog(this,"Insertion reussi !");
					}
					catch(SQLException ex){
						JOptionPane.showMessageDialog(this,"Echec insertion !",null,JOptionPane.ERROR_MESSAGE);
					}
					dispose();
					Combo1 cb=new Combo1();
					cb.setVisible(true);
				}
				//suppression_date
				if(e.getSource()==bsupp3){
					String a=tf.getText();
					String qq="delete from combodate where date='"+a+"'";
					try{
						st=cn.connect().createStatement();
						if(JOptionPane.showConfirmDialog(this,"Voulez-vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							st.executeUpdate(qq);
							JOptionPane.showMessageDialog(this,"suppression reussi !");
						}
						
					}
					catch(SQLException ex){
						JOptionPane.showMessageDialog(this,"Echec suppression !",null,JOptionPane.ERROR_MESSAGE);
					}
					dispose();
					Combo1 cb=new Combo1();
					cb.setVisible(true);
					
					
					
				}
				
		
	
	}
	
	

}
