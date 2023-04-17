<%@page import="Controller.DatabaseOperations.UserOperationsHandleler"%>
<%@page import="java.util.Arrays"%>
<%@page import="Resources.MyConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
    
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>T R E N D Y A T T I R E | PRODUCTS </title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/header-footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/product-page.css" />
  <script src="https://kit.fontawesome.com/1c6c06e40c.js" crossorigin="anonymous"></script>
</head>

<body>

	<%! int noOfProducts = 0; %>

	<sql:setDataSource var="dbConnect"
		driver = "<%=MyConstants.DRIVER_NAME %>"
		url= "<%=MyConstants.DB_URL %>"
		user="root"
		password=""		
	/>
		
	<sql:query var="productsFromDB" dataSource="${dbConnect}">
	<%
		/* Geting all the paramater from URL i.e. GET method */
		String operationType = request.getParameter("operationType");
		String[] categoriesArray = request.getParameterValues("category");
		String[] brandsArray = request.getParameterValues("brand");
		String priceFrom = request.getParameter("priceFrom");
		String priceTo = request.getParameter("priceTo");
		String ratingFrom = request.getParameter("ratingFrom");
		String ratingTo = request.getParameter("ratingTo");
		
		String searchBy = request.getParameter("searchBy");
		String searchContent = request.getParameter("q");

		if(operationType == null) 
		{
	%>
			SELECT * FROM products;
	<%
		} else if(operationType.equals("filterProducts")) 
		{
			/* Converting array to the string. This is default values for product category and brand name */
			String categoriesString = MyConstants.convertArrayToString(MyConstants.PRODUCT_CATEGORIES);
			String brandsString = MyConstants.convertArrayToString(MyConstants.PRODUCT_BRANDS);
				
			/* if user has selected one or more category, then converting it to the string*/
			if(categoriesArray != null ){
				categoriesString = MyConstants.convertArrayToString(categoriesArray);
			}
			
			/* if user has selected one or more brand name, then converting it to the string*/
			if(brandsArray != null){
				brandsString =  MyConstants.convertArrayToString(brandsArray);
			}	
	%>
			SELECT * FROM products WHERE productCategory IN (<%=categoriesString%>) AND brandName IN (<%=brandsString%>) 
			AND (productPrice BETWEEN <%=Integer.parseInt(priceFrom)%> AND <%=Integer.parseInt(priceTo)%>) 
			AND (productRating BETWEEN <%=Float.parseFloat(ratingFrom)%> AND <%=Float.parseFloat(ratingTo) %>); 
	<%
		} else if(operationType.equals("searchProducts")){	
			
			if(searchBy.equals("productPrice")){
			%>
				SELECT * FROM products WHERE productPrice BETWEEN <%=Float.parseFloat(searchContent) %> AND <%=Float.parseFloat(searchContent)+200.0 %> ;
			<%
			} else {
			%>
				SELECT * FROM products WHERE <%=searchBy%> LIKE "%<%=searchContent.toLowerCase()%>%";
			<%
			} 
		}
	%>
	</sql:query>

	
	

  <div class="wrapper-product-page">
	
	<!-- Including Header JSP -->
 	<jsp:include page="/View/header.jsp"></jsp:include>

    <div class="product-banner">
      <img src="${pageContext.request.contextPath}/Images/product-page-banner.png" />
    </div>


    <div class="product-section-contents">

      <!-- Filter Details -->
      <div class="product-filter-details">

		<form method="get">
			
			<input type="hidden" name="operationType" value="filterProducts">	
			
        	<div class="categories-product">
	
	          <h2>Categories</h2>
	
			  <p><a href="product-page.jsp">View All products</a></p>
			  
			  <%
			  	for(String category: MyConstants.PRODUCT_CATEGORIES){
			  %>
	         	 <p>
	         	 	<input type="checkbox" name="category" value="<%=category.toLowerCase()%>"> <%=category %>
	         	 </p>
	          <%}%>
	          
	        </div>
	        
	        <div class="brand-product">
	        
	          <h2>Brand</h2>
	        	
	        	<%
			  		for(String brandName: MyConstants.PRODUCT_BRANDS){
			  	%>
	         	 <p>
	         	 	<input type="checkbox" name="brand" value="<%=brandName.toLowerCase()%>"> <%=brandName %>
	         	 </p>
	          <%}%>
	        
	        </div>
	        
	        <div class="price-product">
	        
	          <h2>Price</h2>
	        
	          <div class="multi-range">
	            <input type="range" min="100" max="5000" value="100" id="lower" name="priceFrom">
	            <input type="range" min="100" max="5000" value="5000" id="upper" name="priceTo">
	          </div>
	        
	          <p>
	        
	            <span id="priceFrom">NPR 0</span>
	            -
	            <span id="priceTo">NPR 0</span>
	        
	          </p>
	        
	        </div>
	        
	        <div class="rating-product price-product">
	          <h2>Rating</h2>
	          <div class="multi-range">
	            <input type="range" min="0" max="5" step="0.1" value="0"  id="lowerRating" name="ratingFrom">
	            <input type="range" min="0" max="5" step="0.2" value="5"  id="upperRating" name="ratingTo">
	          </div>
	        
	          <p>
	        
	            <span id="ratingFrom"><i class="fa-solid fa-star rating-icon"></i> 0 </span>
	            -
	            <span id="ratingTo"><i class="fa-solid fa-star rating-icon"></i> 0 </span>
	        
	          </p>
	        
	        </div>
	        
	        <div class="reset-apply-button">
	          <a href="product-page.jsp"><button id="reset-button" type="button">Reset</button></a>
	          <button id="apply-button" type="submit">Apply</button>
	        </div>
	
       </form>

      </div>

      <div class="product-details">

        <div class="product-cards">

	
			<!-- Displaying products in product page  -->
			<c:forEach var="product" items="${productsFromDB.rows}" >
			
	 			<% noOfProducts++; %> 
			
				 <div class="product-card">
		            <div class="product-image">
		              <img src="http://localhost:8080/images/${product.productImg}" alt="" />
		            </div>
		
		            <div class="product-descriptions">
		              <h3>${product.productName }</h3>
		              <p>${product.brandName}</p>
		              <p>In Stock (${product.productStock})</p>
		
		              <div class="price-and-rating">
		                <span>NPR ${product.productPrice}</span>
		                <p><i class="fa-solid fa-star rating"></i>${product.productRating}</p>
		              </div>
		
		            </div>
		
		
		            <div class="add-to-card-btn">
		              
		              <form action="${pageContext.request.contextPath}/product-page" method="post">
		             	
		             	<input type="hidden" name="productID" value="${product.productID }"/>
		             	<input type="hidden" name="productPrice" value="${product.productPrice}"/>
		             	
		             			             		
		             <%
		             	String[] userDataFromCookies = UserOperationsHandleler.getCookiesData(request);
		             	
		             	if(userDataFromCookies.length != 0){
		             %>
		             
		             	<input type="hidden" name="userID" value="<%=userDataFromCookies[0]%>"/>
		             
		             	<button type="submit">
		             			<i class="fa-solid fa-bag-shopping"></i> Add to card
		             	</button>
		             	
		             <% } else { %>
		             
		             	<button type="button" id="addToCartBtn">
		             			<i class="fa-solid fa-bag-shopping"></i> Add to card
		             	</button>
		             	
		             <% } %>		
		             				             	
		             			              
		              </form>
		              
		            </div>
		
		          </div>
			
			</c:forEach>
			
			 <%
				if(noOfProducts == 0){
			%>
					<h1>No Results Found<h1>
			<%
				} else{
					noOfProducts = 0;
				}
			%>

        </div>


      </div>

    </div>

	
	<!-- Including Footer  -->
	<jsp:include page="/View/footer.jsp"></jsp:include>

  </div>

  <script src="${pageContext.request.contextPath}/JS/product-page.js"></script>


</body>

</html>