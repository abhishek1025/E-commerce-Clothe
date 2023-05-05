package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.OrderDetails;

public class OrderDAO {

	
	public int storeOrderDetails(OrderDetails orderdetails) {
		
		Connection con = DbConnection.getDbConnection();
		String query = "INSERT INTO ORDERS(userID, customerName, orderTotal, orderDate) VALUES(?,?,?,?);";
		
		if(con != null) {
			
			try {
				
				
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
				statement.setInt(1, orderdetails.getUserID());
				statement.setString(2, orderdetails.getCustomerName());
				statement.setDouble(3, orderdetails.getOrderTotal());
				statement.setTimestamp(4, Timestamp.valueOf(orderdetails.getOrderDate()));
				
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
	
	
	public List<OrderDetails> getAllOrderDetails() {
		
		List<OrderDetails> allOrders = new ArrayList<OrderDetails>();
		
		Connection con = DbConnection.getDbConnection();
		String query = "SELECT * FROM ORDERS;";
		
		if(con != null) {
			
			try {
				
				PreparedStatement statement = con.prepareStatement(query);
				
				ResultSet queryResult = statement.executeQuery();
				
				while(queryResult.next()) {
					
					int orderID = queryResult.getInt(1);	
					int userID = queryResult.getInt(2);
					String customerName = queryResult.getString(3);
					Double orderTotal = queryResult.getDouble(4);
					LocalDateTime orderDate =  queryResult.getTimestamp(5).toLocalDateTime();
					
					OrderDetails order = new OrderDetails(customerName, orderDate, orderTotal);
					
					order.setOrderID(orderID);
					order.setUserID(userID);
					
					allOrders.add(order);
					
				}
				
				return allOrders;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return allOrders;
		
	}
	
	public ResultSet getOrderDetailsByOrderID(int orderID) {
		
		Connection con = DbConnection.getDbConnection();
		
		String query = "SELECT P.productName, P.productImg, P.productPrice, CI.quantity FROM OrderItems OI INNER JOIN "
				+ " CartItems CI ON OI.cartItemID = CI.cartItemID INNER JOIN Products P on P.productID = CI.productID"
				+ " WHERE OI.orderID = ?";
		
		if(con != null) {
			
			try {
				
				PreparedStatement statement = con.prepareStatement(query);
				
				statement.setInt(1, orderID);
				
				ResultSet queryResult = statement.executeQuery();
				
				return queryResult;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return null;
		
	}
	
	
	public double[] getTotalSalesAndTotalOrders() {
		
		Connection con = DbConnection.getDbConnection();
		
		String query1 = "SELECT COUNT(orderID) FROM orders";
		String query2 = "SELECT SUM(orderTotal) FROM orders";
		
		if(con != null) {
				
			try {
				
				PreparedStatement statement1 = con.prepareStatement(query1);
				PreparedStatement statement2 = con.prepareStatement(query2);
				
				double[] salesAndOrdersNumber = new double[2];
				
				ResultSet rs1 = statement1.executeQuery();
				
				ResultSet rs2 = statement2.executeQuery();
				
				while(rs1.next()) {
					salesAndOrdersNumber[0] = rs1.getDouble(1);
				}
				
				while(rs2.next()) {
					salesAndOrdersNumber[1] = rs2.getDouble(1);
				}
				
				return salesAndOrdersNumber;			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return null;
		
	}

	
}
