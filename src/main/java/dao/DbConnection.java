package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	private static Connection con = null;
	
	//Method start
	public static  Connection getDbConnection() {
		/*
		 * This method establishes the connection with database.
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/TrendyAttire";
			String user = "root";
			String pass = "";
			
			con = DriverManager.getConnection(URL, user, pass);
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	//Method end
}