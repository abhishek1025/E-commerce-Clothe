package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OrderDAO {

	
	public int storeOrderDetails(int userID, double orderTotal, String customerName) {
		
		Connection con = DbConnection.getDbConnection();
		String query = "INSERT INTO ORDERS(userID, customerName, orderTotal, orderDate) VALUES(?,?,?,?);";
		
		if(con != null) {
			
			try {
				
				LocalDateTime orderDate = LocalDateTime.now();
				
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
				statement.setInt(1, userID);
				statement.setString(2, customerName);
				statement.setDouble(3, orderTotal);
				statement.setTimestamp(4, Timestamp.valueOf(orderDate));
				
				int queryResult = statement.executeUpdate();
				
				ResultSet rs = statement.getGeneratedKeys();
				
			    rs.next();
			    
			    int orderID = rs.getInt(1);
				
				if(queryResult > 0) {
					return orderID;
				}
				
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return 0;
			}
			
		}
		
		return 0;
			
	}
	
	
	
	public void storeOrderItems(int cartItemID, int orderID) {
		
		Connection con = DbConnection.getDbConnection();
		String query = "INSERT INTO ORDERITEMS(orderId, cartItemId) VALUES(?,?);";
		
		System.out.print(orderID);
		System.out.print(cartItemID);
		System.out.println();
		
		if(con != null) {
			
			try {
				
				PreparedStatement statement = con.prepareStatement(query);
				
				statement.setInt(1, orderID);
				statement.setInt(2, cartItemID);
				
				int queryResult = statement.executeUpdate();
				
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}

		
	}
	
	
	public void updateStatusOfCartItems(int cartItemID) { 
		
		Connection con = DbConnection.getDbConnection();
		String query = "UPDATE CartItems SET status = ? WHERE cartItemID = ?";
		
		if(con != null) {
			
			try {
				
				PreparedStatement statement = con.prepareStatement(query);
				
				statement.setString(1, "PURCHASED");
				statement.setInt(2, cartItemID);
				
				statement.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
		
	}
	
	
	
	

	
}
