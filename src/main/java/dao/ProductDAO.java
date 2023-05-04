package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import appConstants.MyConstants;
import model.Product;

public class ProductDAO {
	
	public static Boolean addProduct(Product product) throws SQLException {
		
		Connection con = DbConnection.getDbConnection();
		
		if(con != null) {
					
			String insertQuery = MyConstants.PRODUCT_INSERT_QUERY;
			
			PreparedStatement statement = con.prepareStatement(insertQuery);
			
			statement.setString(1, product.getProductName());
			statement.setString(2,product.getBrandName());
			statement.setString(3,product.getProductCategory());
			statement.setString(4, product.getProductImgUrl());
			statement.setFloat(5,product.getProductPrice());
			statement.setFloat(6,product.getProductRating());
			statement.setInt(7,product.getProductStock());

			int result = statement.executeUpdate();		
			
			con.close();
			
			if(result > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Boolean deleteProduct(int productID) {
		
		Connection con = DbConnection.getDbConnection();
		
		if(con != null) {
			
			String deletQuery = MyConstants.PRODUCT_DELETE_QUERY;
			
			try {
				
				PreparedStatement statement = con.prepareStatement(deletQuery);
				
				statement.setInt(1, productID);
				
				int result = statement.executeUpdate();
				
				con.close();
				
				if(result > 0) {
					return true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return false;
			}

		}
		
		return false;
		
	}
	
	
	public static Boolean updateProductDetails(Product product) throws SQLException {
		
		Connection con = DbConnection.getDbConnection();
		
		String updateQuery = "UPDATE Products SET productName = ?, brandName = ?, productCategory = ? , productImg = ?,"
				+ "productPrice = ?, productRating = ?, productStock = ? WHERE productID = ?";
		
		if(con != null) {
			
			PreparedStatement statement = con.prepareStatement(updateQuery);
			
			statement.setString(1, product.getProductName());
			statement.setString(2,product.getBrandName());
			statement.setString(3,product.getProductCategory());
			statement.setString(4,product.getProductImgUrl());
			statement.setFloat(5,product.getProductPrice());
			statement.setFloat(6,product.getProductRating());
			statement.setInt(7,product.getProductStock());
			statement.setInt(8, product.getProductID());
			
			int result = statement.executeUpdate();
			
			con.close();
			
			if(result > 0) {
				return true;
			}
			
		}
		
		return false;
	}
	
	
	public static Product getProductById(int productID) throws SQLException, IOException {
		
		Connection con = DbConnection.getDbConnection();
		
		Product product = null;
		
		if(con != null) {
			
			String selectProduct = MyConstants.GET_PRODUCT_BY_ID_QUERY ;
			
			PreparedStatement statement = con.prepareStatement(selectProduct);
			
			statement.setInt(1, productID);
			
			ResultSet productFromDB = statement.executeQuery();
			
			while(productFromDB.next()) {
				
				product = new Product();
				
				product.setProductID(productFromDB.getInt(1));
				product.setProductName(productFromDB.getString(2));
				product.setBrandName(productFromDB.getString(3));
				product.setProductCategory(productFromDB.getString(4));
				product.setProductImgUrl(productFromDB.getString(5));
				product.setProductPrice(productFromDB.getFloat(6));
				product.setProductRating(productFromDB.getFloat(7));
				product.setProductStock(productFromDB.getInt(8));
				
				
			}

		}
		
		con.close();
		
		return product;	
		
	}
	
	
	public static List<Product> getAllProducts(boolean filterProductUsingStock){
		
		//createing an arrray list
		
		List<Product> allProducts = new ArrayList<Product>();
		
		Connection con = DbConnection.getDbConnection();
	
		if(con != null) {
			
			try {
				
				PreparedStatement statement = con.prepareStatement(MyConstants.GET_All_PRODUCTS_QUERY);
				
				ResultSet productsFromDB = statement.executeQuery();
				
				while(productsFromDB.next()) {
					
					Product product = new Product();
					
					product.setProductID(productsFromDB.getInt(1));
					product.setProductName(productsFromDB.getString(2));
					product.setBrandName(productsFromDB.getString(3));
					product.setProductCategory(productsFromDB.getString(4));
					product.setProductImgUrl(productsFromDB.getString(5));
					product.setProductPrice(productsFromDB.getFloat(6));
					product.setProductRating(productsFromDB.getFloat(7));
					product.setProductStock(productsFromDB.getInt(8));
		            
		            /*
		             * To display in products in product page, we need to display the products with stock.
		             * If the request for all products is made from product page, the filterProductUsingStock will be true.
		             * and if the stock of product is not zero, then the product is added to array list
		             * Simultaneously, if the request is made from dashboard page, we need to display all the product whether 
		             * its quantity is zero or not.
		             * 
		             */
					if(filterProductUsingStock && product.getProductStock() != 0) {
						
						allProducts.add(product);
						
					} else if(!filterProductUsingStock) {
						
						allProducts.add(product);
						
					}
					
					
				}
				
				con.close();	
				
				return allProducts;
						
			} catch (SQLException e) {
				e.printStackTrace();
				return allProducts;
			}	
		}
		
		return allProducts;
	}
	
	
	public static int getProductStockByProductID(int productID) throws SQLException {
		
		Connection con = DbConnection.getDbConnection();
		
		String query = "SELECT productStock FROM products WHERE productID = ?";
		
		if(con != null) {
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1, productID);
			
			ResultSet queryResult = statement.executeQuery();
			
			while(queryResult.next()) {
				return queryResult.getInt(1);
			}
			
		}
		
		con.close();
		
		return 0;
		
	}
	
	
	
	public static int manageProductStock(String stockOperation, int productID, int quantity) throws SQLException {
		
		Connection con = DbConnection.getDbConnection();
		
		String query = "UPDATE Products SET productStock = ? WHERE productID = ?";
		
		if(con != null) {
			
			int productStock = getProductStockByProductID(productID);
			
			PreparedStatement statement = con.prepareStatement(query);
			
			//setting product id in query
			statement.setInt(2, productID);
						
			// Stock will increase if the user delete the item from cart before purchasing it
			if(stockOperation.equals("INCREASE STOCK")) {
				
				// setting stock in query
				statement.setInt(1, productStock + quantity);
				
			}
			// Stock will decrease if the user add or update the quantity of cart items
			else if(stockOperation.equals("DECREASE STOCK")) {
				
				// setting stock in query
				statement.setInt(1, productStock - quantity);
				
			}

			int queryResult = statement.executeUpdate();
			
			if(queryResult > 0) {
				return queryResult;
			}
			
		}
		
		return 0;
		
	}
	
	

	
}
