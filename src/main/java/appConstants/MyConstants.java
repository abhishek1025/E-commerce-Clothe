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
	public static final String GET_ADMIN_BY_EMAIL_QUERY = "SELECT * FROM Admins where email = ?";
	
	public static final String INSERT_USER_QUERY = "INSERT INTO USERS(firstName, lastName,email, userImg, phoneNumber, "
			+ "address, encryptedPassword) VALUES(?,?,?,?,?,?,?)";
	
	public static final String PRODUCT_INSERT_QUERY = "insert into Products(productName, brandName, productCategory, productImg, productPrice, "
			+ "productRating, productStock) values(?,?,?,?,?,?,?)";
	
	public static final String PRODUCT_DELETE_QUERY = "DELETE FROM Products where productID = ?";
	
	public static final String GET_PRODUCT_BY_ID_QUERY = "SELECT * FROM Products WHERE productID = ?";
	
	public static final String GET_All_PRODUCTS_QUERY = "SELECT * FROM products";
	
	public static final String CART_ITEM_INSERT_QUERY = "INSERT INTO CARTITEMS(userID, productID, quantity, status) values (?,?,?,?)";
	
	public static final String UPDATE_CART_ITEM_QUERY = "UPDATE CARTITEMS SET quantity = ? WHERE cartItemID = ?";
	
	public static final String GET_CART_ITEM_QUERY = "SELECT  cartItemID, quantity FROM CARTITEMS WHERE userID = ? AND productID = ? AND status = ?";
	
	public static final String GET_CART_ITEMS_DETAILS_QUERY = "SELECT CI.cartItemID, P.productName, P.productPrice, P.productImg, CI.quantity, CI.productID, P.productStock FROM CartItems as "
			+ "CI INNER JOIN Products as P ON CI.productID = P.productID WHERE CI.userID = ? AND CI.status = ?";

	
	public static String convertArrayToString(String[] array) {
		
		String result = "";
		
		for (String s : array) {
		    result += "\"" + s.toLowerCase() + "\", ";
		}
		
		result = result.substring(0, result.length() - 2);
		
		return result;
	}
	
	
	public static final String[] PRODUCT_BRANDS = {"nike", "h&m", "adidas", "zara", "lewis"};
	public static final String[] PRODUCT_CATEGORIES = {"men", "women", "kids", "unisex"};
	
	
	public static final String URL_FOR_FILTERING_PRODUCTS = "View/pages/product-page.jsp?operationType=filterProducts&priceFrom=100&priceTo=5000"
			+ "&ratingFrom=0&ratingTo=5";
	


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
