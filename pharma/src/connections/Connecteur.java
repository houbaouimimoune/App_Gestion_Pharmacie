package connections;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connecteur {
	Connection con;
	public Connecteur(){
		try{
		Class.forName("com.mysql.jdbc.Driver")	;
		con=DriverManager.getConnection("jdbc:mysql://localhost/pharmabase","root","");
		System.out.println("connection établie!");
		}
		catch(Exception e){
			System.out.println("erreur de connexion!!");
		}
		
		
	}
	public Connection connect(){
		return con;
	}

}
