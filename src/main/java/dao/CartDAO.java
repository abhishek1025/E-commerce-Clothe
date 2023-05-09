package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import appConstants.MyConstants;
import model.CartItem;
import utils.ManageCookie;

public class CartDAO {
	
	//Method start
	public int addProductToCart(CartItem cartitem) {
		
		/*
		 * Here, first, we checked that whether the cart item exists or not in database.
		 * if it exists, we fetched the quantity and cart item id and update it using updateCartItem function
		 * If it does not exists, we add new cart item in the database.
		 */
		
		Connection con = DbConnection.getDbConnection();
		
		if(con != null) {
						
			try {
				
				// fetching the quantity and cartItemID of cart item if it exists
				CartItem updatedCartItemDetails = checkCartItemExits(cartitem);
				
				// the quantity of product is passed 1 because every time we add or re-add a product to a cart, the quantity will always be one
				ProductDAO productdao = new ProductDAO();
				int queryResultFromUpdateProductStock = productdao.manageProductStock("DECREASE STOCK", cartitem.getProductID(), 1);
				
				//if cartItemID is zero, that means the cart item does not exist. So, we are inserting new item.
				if(updatedCartItemDetails.getCartItemID() == 0) {
					
					PreparedStatement statement = con.prepareStatement(MyConstants.CART_ITEM_INSERT_QUERY);
					
					statement.setInt(1, cartitem.getUserID());
					statement.setInt(2, cartitem.getProductID());
					statement.setInt(3, cartitem.getQuantity());
					statement.setString(4,"IN CART");
					
					int queryResult = statement.executeUpdate();
					
					if(queryResult > 0 && queryResultFromUpdateProductStock > 0) {
						return 1;
					} 
									
				} else {
				
					// if cartItemID is not zero, the cart item exists and we are increasing the quantity by one. 
					int queryResult = updateCartItem(updatedCartItemDetails.getCartItemID(), updatedCartItemDetails.getQuantity() + 1);
					
					if(queryResult > 0 && queryResultFromUpdateProductStock > 0) {
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
	//Method end
	
	//Method start
	public CartItem checkCartItemExits(CartItem cartitem) throws SQLException {
		
		/*
		 * This method takes cartitem object which contains all details of cart item
		 * for a cart item, user id and product id are always unique. 
		 * so, using both, we are fetching cartItemId and quantity if exits and updating it in the cartitem object
		 * The updated cartitem object is return at the end if the cart item exists in the database.  
		 * 
		 */
		
		Connection con = DbConnection.getDbConnection();
		
		if(con != null) {
			
			PreparedStatement statement = con.prepareStatement(MyConstants.GET_CART_ITEM_QUERY);
			
			statement.setInt(1, cartitem.getUserID());
			statement.setInt(2, cartitem.getProductID());
			statement.setString(3, "IN CART");
			
			ResultSet queryResult = statement.executeQuery();
			
			while(queryResult.next()) {
				
				cartitem.setCartItemID(queryResult.getInt(1));
				cartitem.setQuantity(queryResult.getInt(2));
				
				return cartitem;
				
			}
							
		}
			
		return cartitem;
		
	}
	//Method end
	
	//Method start	
	public int updateCartItem(int cartItemID, int quantity){
	
		/*
		 * This function  updates the quantity of cart
		 */
		
		Connection con = DbConnection.getDbConnection();
		
		
		if(con != null) {
			
			try {
				
				PreparedStatement  statement = con.prepareStatement(MyConstants.UPDATE_CART_ITEM_QUERY);

				statement.setInt(1, quantity);
				statement.setInt(2, cartItemID);
					
				
				int queryResult = statement.executeUpdate();
				
				if(queryResult > 0) {
					return queryResult;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return 0;
			}
			
			
						
		}
		
		return 0;
		
	}
	//Method end
	
	//Method start
	public ResultSet getAllCartItems(HttpServletRequest request) {
		
		/*
		 * This method is used to fetch all the cart items where the status of cart is "IN CART".
		 * To fetch the cart items, we need the user id. 
		 * We are using the user id, stored in the cookie. 
		 */
		
		String[] userData = ManageCookie.getCookiesData(request, "userData"); 
		
		Connection con = DbConnection.getDbConnection();
		
		ResultSet queryResult = null;
		
		if(con != null && userData.length != 0) {
			
			int userID = Integer.parseInt(userData[0]);
			
			try {
				
				PreparedStatement statement = con.prepareStatement(MyConstants.GET_CART_ITEMS_DETAILS_QUERY);
				
				statement.setInt(1, userID);
				statement.setString(2, "IN CART");
				
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
	//Method end

	//Method start
	public  int deletCartItem(int cartItemID) {
		
		/*
		 * This method is used to delete the cart item from the cart item table. 
		 */
		
		Connection con = DbConnection.getDbConnection();
		
		String DELETE_CART_ITEM_QUERY = MyConstants.DELETE_CART_ITEM_QUERY;
		
		if(con != null){
			
			try {
				
				PreparedStatement statement = con.prepareStatement(DELETE_CART_ITEM_QUERY);
				
				statement.setInt(1, cartItemID);
				statement.setString(2, "IN CART");
				
				int queryResult = statement.executeUpdate();
				
				if(queryResult > 0) {
					
					return 1;
					
				}
				
			} catch (SQLException e) {
				
				return -1;
			}
			
		}
		
		return -1;
		
	}
	//Method end
}
