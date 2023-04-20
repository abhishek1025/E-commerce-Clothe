package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import model.CartItem;

public class CartDAO {
	
	public static int addProductToCart(String query, CartItem cartitem) {
		
		Connection con = DbConnection.getDbConnection();
		
		String CARTITEM_UPDATE_QUERY = "UPDATE CartItems SET quantity = ? WHERE userID = ? AND productID = ?";
		
		
		
		if(con != null) {
						
			try {
				
				int cartItemQuantity = checkCartItemExits(cartitem.getUserID(), cartitem.getProductID());
				
				if( cartItemQuantity == 0 ) {
					
					PreparedStatement statement = con.prepareStatement(query);
					
					statement.setInt(1, cartitem.getUserID());
					statement.setInt(2, cartitem.getProductID());
					statement.setInt(3, cartitem.getQuantity());
					
					int queryResult = statement.executeUpdate();
					
					if(queryResult > 0) {
						return 1;
					} 
									
				} else {
				
					int queryResult = updateCartItem(CARTITEM_UPDATE_QUERY,cartItemQuantity+1 , 0,  cartitem.getUserID(), cartitem.getProductID());
					
					if(queryResult > 0) {
						return 2;
					} 
								
									
				}
				
				
				con.close();	
				
				
				
							
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return -1;
			}
				
			
		}
		
		return -1;
		
	}
	
	public static int checkCartItemExits(int userID, int productID) throws SQLException {
		
		Connection con = DbConnection.getDbConnection();
		
		String GET_CART_ITEM = "SELECT quantity FROM CARTITEMS WHERE userID = ? AND productID = ?";
		
		if(con != null) {
			
			PreparedStatement statement = con.prepareStatement(GET_CART_ITEM);
			
			statement.setInt(1, userID);
			statement.setInt(2, productID);
			
			ResultSet queryResult = statement.executeQuery();
			
			while(queryResult.next()) {
				
				return queryResult.getInt(1);
				
			}
			
							
		}
			
		return 0;
		
	}
	
	
	public static int updateCartItem(String query, int quantity, int cartItemID, int userID,  int productID) throws SQLException {
		
		Connection con = DbConnection.getDbConnection();
		
		if(con != null) {
			
			PreparedStatement statement = con.prepareStatement(query);
			
			if(productID != 0 && userID != 0) {
				
				statement.setInt(1, quantity);
				statement.setInt(2, userID);
				statement.setInt(3, productID);
				
			} else {
				statement.setInt(1, quantity);
				statement.setInt(2, cartItemID);
				
			}
			
			int queryResult = statement.executeUpdate();
			
			if(queryResult > 0) {
				return queryResult;
			}
						
		}
		
		return 0;
		
	}
	
	
	public static ResultSet getAllCartItems(HttpServletRequest request) {
		
		String[] userData = UserDAO.getCookiesData(request); 
		
		Connection con = DbConnection.getDbConnection();
		
		ResultSet queryResult = null;
		
		String SELECT_CART_ITEMS_QUERY = "SELECT CI.cartItemID, P.productName, P.productPrice, P.productImg, CI.quantity FROM CartItems as "
				+ "CI INNER JOIN Products as P ON CI.productID = P.productID WHERE userID = ?";
		
		if(con != null && userData.length != 0) {
			
			int userID = Integer.parseInt(userData[0]);
			
			try {
				
				PreparedStatement statement = con.prepareStatement(SELECT_CART_ITEMS_QUERY);
				
				statement.setInt(1, userID);
				
				queryResult = statement.executeQuery();
	
				return queryResult;	
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return queryResult;	
			}
			
			
			
		}
		
		return queryResult;		
	}
	

	public static int deletCartItem(HttpServletRequest request) {
		
		Connection con = DbConnection.getDbConnection();
		
		String DELETE_CART_ITEM_QUERY = "DELETE FROM CartItems WHERE cartItemID = ?";

		String operationType = request.getParameter("cartOperationType");
		
		if(operationType != null && con != null){
			
			int cartItemID = Integer.parseInt(request.getParameter("cartItemID"));
			
			try {
				PreparedStatement statement = con.prepareStatement(DELETE_CART_ITEM_QUERY);
				
				statement.setInt(1, cartItemID);
				
				int queryResult = statement.executeUpdate();
				
				if(queryResult > 0) {
					
					return 1;
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return -1;
			}
			
		}
		
		return -1;
		
	}
	
}
