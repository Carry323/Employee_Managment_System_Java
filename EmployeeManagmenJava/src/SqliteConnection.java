import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqliteConnection {
	
	
	public static Connection dbConnector() {
		
		try {
			
			//JDBC --> Java DataBase Connection
			Class.forName("org.sqlite.JDBC");
			Connection conn =  DriverManager.getConnection("jdbc:sqlite:EmployeeDB.db");
			//JOptionPane.showMessageDialog(null, "Connection Successfully Established ...");
			return conn;
			
		} catch (Exception e) {
			// TODO: handle exception
	
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
		
	}

}
