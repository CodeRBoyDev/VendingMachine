import java.sql.*;

import javax.swing.*;

public class DbCon {

	public static Connection dbconnector() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vmdb","root","09207888638");
			return con;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
		
		
	}
	
	
}
