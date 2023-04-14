package Resources;

public class MyConstants {
	
	public static final String IMAGE_DIR = "/Applications/XAMPP/xamppfiles/tomcat/apache-tomcat-8.5.86/webapps/ROOT/images/";
	

	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/TrendyAttire";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD =""	;	
	
	public static final String PRODUCT_INSERT_QUERY = "insert into Products(productName, brandName, productCategory, productImg, productPrice, "
			+ "productRating, productStock) values(?,?,?,?,?,?,?)";
	
	public static final String PRODUCT_DELETE_QUERY = "DELETE FROM Products where productID = ?";
	
	public static final String GET_PRODUCT_BY_ID_QUERY = "SELECT * FROM Products WHERE productID = ?";
}
