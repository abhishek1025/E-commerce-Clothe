package utils;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import appConstants.MyConstants;
import dao.DbConnection;
import dao.ProductDAO;
import model.Product;

public class HandleFilterProductsOperations {
	
public static List<Product> displayProductsInProductPage(HttpServletRequest request) throws SQLException{
		
		List<Product> products = new ArrayList<Product>();
			
		String operationType = request.getParameter("operationType");
		
		Connection con = DbConnection.getDbConnection();
		
		PreparedStatement statement = null;
		
//		String[] categoriesArray = request.getParameterValues("category");
//		String[] brandsArray = request.getParameterValues("brand");
//		String priceFrom = request.getParameter("priceFrom");
//		String priceTo = request.getParameter("priceTo");
//		String ratingFrom = request.getParameter("ratingFrom");
//		String ratingTo = request.getParameter("ratingTo");
		
		
		
		if(operationType == null) {

			statement = con.prepareStatement(MyConstants.GET_All_PRODUCTS);
			 
		} else if(operationType.equals("filterProducts")) {
			
			String[] categoriesArray = request.getParameterValues("category");
			String[] brandsArray = request.getParameterValues("brand");
			float priceFrom = Float.parseFloat(request.getParameter("priceFrom"));
			float priceTo = Float.parseFloat(request.getParameter("priceTo"));
			float ratingFrom = Float.parseFloat(request.getParameter("ratingFrom"));
			float ratingTo = Float.parseFloat(request.getParameter("ratingTo"));
			 
			 
			 /* if user has selected one or more category, then converting it to the string*/
			if(categoriesArray == null ){
				categoriesArray = MyConstants.PRODUCT_CATEGORIES;
			}
				
			/* if user has selected one or more brand name, then converting it to the string*/
			if(brandsArray == null){
				brandsArray = MyConstants.PRODUCT_BRANDS;
			}
			
			statement = con.prepareStatement(MyConstants.PRODUCT_FILTER_QUERY);
			
			Array categoriesArrayInVarchar = con.createArrayOf("VARCHAR", categoriesArray);
			Array brandArrayInVarchar = con.createArrayOf("VARCHAR", categoriesArray);
			
//			statement.se
			statement.setArray(1, categoriesArrayInVarchar);
			statement.setArray(2, brandArrayInVarchar);
			statement.setFloat(3, priceFrom);
			statement.setFloat(4, priceTo);
			statement.setFloat(5, ratingFrom);
			statement.setFloat(6, ratingTo);
			
		} else if(operationType.equals("searchProducts")){	
			
			String searchBy = request.getParameter("searchBy");
			String searchContent = request.getParameter("q");
			
			float price = 0;
			
			if(searchBy.equals("productPrice")) {
				price = Float.parseFloat(searchContent);
				statement = con.prepareStatement(MyConstants.PRODUCT_SEARCH_QUERY_BY_PRICE);
				statement.setFloat(1, price);
				statement.setFloat(2, price + 200);

			} else {
				
				switch (searchBy) {
					case "productCategory":
						statement = con.prepareStatement(MyConstants.SEARCH_QUERY_BY_CATEGORY);
						break;
					
					case "brandName": 
						statement = con.prepareStatement(MyConstants.SEARCH_QUERY_BY_BRAND);
						break;

					case "productName":
						statement = con.prepareStatement(MyConstants.SEARCH_QUERY_BY_NAME);
						break;

				}
				
				statement.setString(1, searchContent.toLowerCase());
			}
			
		}
		
		products = ProductDAO.getAllProducts(statement);
		
		return products;
			
					
	}
	
}
