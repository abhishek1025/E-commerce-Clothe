package Controller.product;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import Controller.dbconnection.DbConnection;
import Model.Product;
import Resources.MyConstants;

public class ProductOperationsHandeler {
	
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
	
	public static Boolean deleteProduct(int productID) throws SQLException {
		
		Connection con = DbConnection.getDbConnection();
		
		if(con != null) {
			String deletQuery = MyConstants.PRODUCT_DELETE_QUERY;
			
			PreparedStatement statement = con.prepareStatement(deletQuery);
			
			statement.setInt(1, productID);
			
			int result = statement.executeUpdate();
			
			con.close();
			
			if(result > 0) {
				return true;
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
		
		return product;	
		
	}
		
	
	public static List<Product> getAllProducts() throws SQLException, IOException{
		
		//createing an arrray list
		
		List<Product> allProducts = new ArrayList<Product>();
		
		Connection con = DbConnection.getDbConnection();
		
		String selectQuery = "select * from Products";
		
		PreparedStatement statement = con.prepareStatement(selectQuery);
		
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
            
            allProducts.add(product);
		}
		
		con.close();
		
		return allProducts;
		
	}
	

	public static String getImageUrl(Part imgPart) {
		
		String savePath = MyConstants.IMAGE_DIR;
		
		File fileSaveDir = new File(savePath);
		
		String imageUrlFromPart = null;
		
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		String contentDisp = imgPart.getHeader("content-disposition");
		
		String[] items = contentDisp.split(";");
		
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		
		if(imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "no new img";
		}
		
		return imageUrlFromPart;
	}

	
	
}
