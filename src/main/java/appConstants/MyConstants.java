package appConstants;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

public class MyConstants {
	
	public static final String IMAGE_DIR = "/Applications/XAMPP/xamppfiles/tomcat/apache-tomcat-8.5.86/webapps/ROOT/images/";
	

	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/TrendyAttire";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD =""	;	
	
	public static final String GET_USER_BY_EMAIL_QUERY = "SELECT * FROM Users where email = ?";
	
	public static final String INSERT_USER_QUERY = "INSERT INTO USERS(firstName, lastName,email, userImg, phoneNumber, "
			+ "address, encryptedPassword) VALUES(?,?,?,?,?,?,?)";
	
	public static final String PRODUCT_INSERT_QUERY = "insert into Products(productName, brandName, productCategory, productImg, productPrice, "
			+ "productRating, productStock) values(?,?,?,?,?,?,?)";
	
	public static final String PRODUCT_DELETE_QUERY = "DELETE FROM Products where productID = ?";
	
	public static final String GET_PRODUCT_BY_ID_QUERY = "SELECT * FROM Products WHERE productID = ?";
	
	public static final String GET_All_PRODUCTS = "SELECT * FROM products";
	
	
	public static String convertArrayToString(String[] array) {
		
		String result = "";
		
		for (String s : array) {
		    result += "\"" + s.toLowerCase() + "\", ";
		}
		
		result = result.substring(0, result.length() - 2);
		
		return result;
	}
	
	
	public static final String[] PRODUCT_BRANDS = {"Nike", "H&M", "Adidas", "Zara", "Lewis"};
	public static final String[] PRODUCT_CATEGORIES = {"Men", "Women", "Kids", "Unisex"};
	
	
	public static final String PRODUCT_FILTER_QUERY = "SELECT * FROM products WHERE productCategory IN (?) AND brandName IN (?)"
			+ " AND (productPrice BETWEEN ? AND ?) AND (productRating BETWEEN ? AND ?);"; 
	
	public static final String URL_FOR_FILTERING_PRODUCTS = "View/pages/product-page.jsp?operationType=filterProducts&priceFrom="
			+ "&priceTo=5000&ratingFrom=0&ratingTo=5";
	
	public static final String PRODUCT_SEARCH_QUERY_BY_PRICE = "SELECT * FROM products WHERE productPrice BETWEEN ? AND ?;"; 
	public static final String SEARCH_QUERY_BY_CATEGORY= "SELECT * FROM products WHERE productCategory = ?;"; 
	public static final String SEARCH_QUERY_BY_NAME= "SELECT * FROM products WHERE productName = ? ;"; 
	public static final String SEARCH_QUERY_BY_BRAND= "SELECT * FROM products WHERE brandName = ?;"; 

	
	public static String getImageUrl(Part imgPart, String filePath) {
			
			File fileSaveDir = new File(filePath);
			
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
			
			try {
				if (!imageUrlFromPart.isEmpty() && imageUrlFromPart != null) {
					imgPart.write(filePath + imageUrlFromPart);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return imageUrlFromPart;
		}
	
	
}
