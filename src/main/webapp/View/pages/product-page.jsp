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
  <link rel="stylesheet" href="../../CSS/header-footer.css" />
  <link rel="stylesheet" href="../../CSS/product-page.css" />
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

    <header class="header">
    
      <div class="header-section-1">
      
        <div class="search-box" id="search-box">
        
          	<form action="${pageContext.request.contextPath}/View/pages/product-page.jsp" method="get">
          	
          		<input type="hidden" name="operationType" value="searchProducts">
          	
          		<select name="searchBy" id="searchBy" required>
          			<option value="">Search By</option>
          			<option value="productCategory">Category</option>
          			<option value="productPrice">Price</option>
          			<option value="brandName">Brand Name</option>
          			<option value="productName">Product Name</option>
          			
          		</select>
          		<%-- 
          		value=" <%=request.getParameter("q") %> " 
          		 --%>
          		<%if(request.getParameter("q") != null){%>
	          		<input type="search" id="searchBox" name="q" value="<%=request.getParameter("q") %>" required>
          		<%} else {%>
          			<input type="search" id="searchBox" name="q" required>          			
          		<%}%>
          		
          		<button type="submit"> Search </button>
          		
          	</form>
        </div>
        
        <div class="logo-text">
          <p>Trendy Attire</p>
        </div>
        
        <div class="profile-cart-icon">
          <i class="fa-regular fa-user"></i>
          <i class="fa-solid fa-cart-plus"></i>
        </div>
        
      </div>

      <div class="header-section-2">
        <nav>
          <ul>
            <li><a href="../../home.html">Home</a></li>
            <hr />
            <li><a href="product-page.jsp">Products</a></li>
            <hr />
            <li><a href="contact-us.html">Contact us</a></li>
          </ul>
        </nav>
      </div>
    </header>

    <div style=" border-bottom: 1px solid #ccc; margin-bottom:10px;"></div>

    <div class="product-banner">
      <img src="../../Images/product-page-banner.png" />
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
		              <button><i class="fa-solid fa-bag-shopping"></i> Add to card</button>
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

    <footer>

      <div class="footer-section-1">

        <div class="categories">
          <p>Categories</p>
          <br />
          <a href="#">Men</a>
          <a href="#">Women</a>
          <a href="#">Kids</a>
          <a href="#">Unisex</a>
        </div>

        <div class="account">
          <p>Account</p>
          <br />
          <a href="#">Sign up</a>
          <a href="#">Sign in</a>
        </div>

        <div class="quick-links">
          <p>Quick Links</p>
          <br />
          <a href="#">Home</a>
          <a href="#">Products</a>
          <a href="#">Contact us</a>
        </div>

        <div class="sign-up">
          <p>Sign up for newsletter</p>
          <br />
          <div class="text-button">
            <input type="email" placeholder="Enter email address..." />
            <div class="button-div-subscribe">
              <button id="subscribe">Subscribe</button>
            </div>
          </div>
        </div>
      </div>
      <br />
      <hr />
      <br />
      <div class="footer-section-2">
        <div class="logo">
          <p class="trendy">Trendy Attire</p>
          <p class="transform">Transform your style</p>
        </div>
        <div class="store-location">
          <span>Store Location</span>
          <p>Kamalpokhari, Kathmandu</p>
          <p>+977 984758877</p>
          <p>trendy.attire@gmail.com</p>
        </div>
        <div class="socials">
          <span>Follow us</span>
          <br />
          <i class="fa-brands fa-facebook"></i>
          <i class="fa-brands fa-instagram"></i>
          <i class="fa-brands fa-youtube"></i>
        </div>
      </div>
      <br />
      <hr />
      <div class="footer-section-3">
        <br />
        <p>© 2023 Trendy Attire. All Rights Reserved.</p>
        <br />
      </div>
    </footer>


  </div>

  <script src="../../JS/product-page.js"></script>
  
  <script>
  
		const searchByEl = document.querySelector("#searchBy");
		const searchBoxEl = document.querySelector("#searchBox");
  
  		const changeInputType = (element) => {
  			if(element.value === "productPrice"){
  				searchBoxEl.type = "number";
  				searchBoxEl.min = 0;
  				searchBoxEl.max = 5000;
  			} else{
  				searchBoxEl.type = "search";
  			}
  		}
 
  		searchByEl.onchange = (e) => {
  			changeInputType(e.target);
  		}
  		
  		<% if(request.getParameter("searchBy") != null) { %>
  			searchByEl.value = "<%=request.getParameter("searchBy") %>";
  		<%}%>
  		
  		changeInputType(searchByEl);  	
  		
  </script>

</body>

</html>