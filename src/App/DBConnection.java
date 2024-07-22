package App;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	static Connection con = null;
	public static Connection connect() {
		String url = "jdbc:mysql://localhost:3306/app_gestion_pharmacie_v2";
		String username = "root"; 
		String pswd = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,pswd);
			System.out.println("connexion etablie");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return con;
	}

}
