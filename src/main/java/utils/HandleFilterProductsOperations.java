package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import appConstants.MyConstants;
import dao.ProductDAO;
import model.Product;

public class HandleFilterProductsOperations {
	
	public List<Product> displayProductsInProductPage(HttpServletRequest request){
		
		List<Product> filteredProducts = new ArrayList<Product>();
		
		List<Product> products = ProductDAO.getAllProducts(true);
		
		String operationType = request.getParameter("operationType");

		if(operationType != null && operationType.equals("filterProducts")) {
			
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

			
			for (Product product : products) {
				
				if(Arrays.asList(categoriesArray).contains(product.getProductCategory()) 
					&& Arrays.asList(brandsArray).contains(product.getBrandName())
					&& (product.getProductPrice() >= priceFrom && product.getProductPrice() <= priceTo)
					&& (product.getProductRating() >= ratingFrom && product.getProductRating() <= ratingTo)
				){
				
					filteredProducts.add(product);
				}
				
				
			}
			
			return filteredProducts;
			
			
		} else if(operationType!= null && operationType.equals("searchProducts")){	
			
			String searchBy = request.getParameter("searchBy");
			String searchContent = request.getParameter("q").toLowerCase();
			

			if(searchBy.equals("productPrice")) {
				
				float price = Float.parseFloat(searchContent);
				filteredProducts = products.stream()
						.filter(product -> product.getProductPrice() == price)
						.collect(Collectors.toCollection(ArrayList::new));

			} else if(searchBy.equals("productCategory")){
				
				filteredProducts = products.stream()
						.filter(product -> product.getProductCategory().equals(searchContent))
						.collect(Collectors.toCollection(ArrayList::new));
				
			} else if(searchBy.equals("brandName")){
				
				filteredProducts = products.stream()
						.filter(product -> product.getBrandName().equals(searchContent))
						.collect(Collectors.toCollection(ArrayList::new));
				
			} else if(searchBy.equals("productName")) {
				
				filteredProducts = products.stream()
						.filter(product -> product.getProductName().toLowerCase().contains(searchContent))
						.collect(Collectors.toCollection(ArrayList::new));	
			}
			
			
			return filteredProducts;
			
		}
		
		return products;
					
	}
	
}
