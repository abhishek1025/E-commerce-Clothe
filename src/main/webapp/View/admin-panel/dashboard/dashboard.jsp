<%@page import="Resources.MyConstants"%>
<%@page import="Model.Product"%>
<%@page import="java.util.List"%>
<%@page import="Controller.product.ProductOperationsHandeler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>    
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    
    <!-- Google Font CDN  -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    
    <!-- CSS files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard-sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard.css">
</head>

<body>

 	<%! Boolean isProductDeleted = false; %>
	
	<%
    	String productID = request.getParameter("productID");
		String operationType = request.getParameter("operationType");
		
		if(operationType != null && operationType.equals("delete")){		
			isProductDeleted = ProductOperationsHandeler.deleteProduct(Integer.parseInt(productID));
		}
	%>

	<sql:setDataSource var="dbConnect"
		driver = "<%=MyConstants.DRIVER_NAME %>"
		url= "<%=MyConstants.DB_URL %>"
		user="root"
		password=""		
	/>

	<sql:query var="productsFromDB" dataSource="${dbConnect}">
		select * from products;	
	</sql:query>
		
	<sql:query var="countProducts" dataSource="${dbConnect}">
			SELECT COUNT(productID) as totalNoOfProducts FROM Products;
	</sql:query>
	
	
    <aside>

        <section class="admin-details-wrapper">
            <div class="admin-img">
                <img src="${pageContext.request.contextPath}/assets/admin.png" alt="Admin" height="130px">
            </div>

            <div class="admin-details">
                <h3>Admin</h3>
                <p>admin@gmail.com</p>
            </div>
        </section>

        <section class="panel-functions">

            <div class="active-dashboard-tab panel-function">

                <img class="blue" src="${pageContext.request.contextPath}/assets/dashboard.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/dashboard/dashboard.jsp">Dashboard</a>

            </div>

            <div class="panel-function">
                <img src="${pageContext.request.contextPath}/assets/product.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/add-product/add-product.jsp"> Add Product </a>
            </div>

            <div class="panel-function">

                <img src="${pageContext.request.contextPath}/assets/cart.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/orders/view-orders.jsp">View Orders</a>

            </div>

        </section>

    </aside>


    <div class="admin-panel-wrapper">

        <section class="admin-panel-content">

            <h1>Dashboard</h1>

            <div class="business-summary-wrapper">
                <div class="business-summary">
                    <img src="${pageContext.request.contextPath}/assets/dollar.svg" alt="Total Sales">

                    <div>
                        <p>Total Sales</p>
                        <span>124456</span>
                    </div>
                </div>

                <div class="business-summary">
                    <img src="${pageContext.request.contextPath}/assets/order.svg" alt="Total Order">

                    <div>
                        <p>Total Orders</p>
                        <span>124456</span>
                    </div>
                </div>

                <div class="business-summary">
                    <img src="${pageContext.request.contextPath}/assets/colorful-product.svg" alt="Total Products">

                    <div>
                        <p>Total Products</p>
                        
                        <c:forEach var="result" items="${countProducts.rows}">
    						<span>${result.totalNoOfProducts}</span>
						</c:forEach>
                    </div>
                </div>
            </div>

            <div class="product-list">

                <h2>Products</h2>

                <div style="border-top: 1px solid black; margin-top:15px"></div>

                    <div class="product-cards-wrapper">
                    
                    	<!-- Displaying products -->
                    	<c:forEach var="product" items="${productsFromDB.rows}" >
	                    	<div class="product-card">
					            <img class="product-img" src="http://localhost:8080/images/${product.productImg}" alt="${product.productImg}">
					            <p class="product-title">${product.productName}</p>
                                
					            <div class="product-desc">
                                    <p><i>NPR ${product.productPrice} </i></p>
                                    <p>In Stock (${product.productStock})</p>
                                </div>
					
					            <div class="product-card-btn">
					
								<a href="edit-product.jsp?operationType=update&productID=${product.productID}">
					                <button class="edit-btn">
					                    <img src="${pageContext.request.contextPath}/assets/edit.svg" height="12px"> <span>Edit</span>
					                </button>
					             </a>
					
								<a href="dashboard.jsp?operationType=delete&productID=${product.productID}">
					                <button class="delete-btn">
					                    <img src="${pageContext.request.contextPath}/assets/delete.svg" height="12px"><span>Delete</span>
					                </button>
								</a>
								
					            </div>
					        </div>                    
                    	</c:forEach>
                    	
                    </div>


            </div>

        </section>

    </div>
    
    
    <%
		if(isProductDeleted){
			
			isProductDeleted = false;
	%>
			<script type="text/javascript">
				setTimeout(() => alert('Product Deleted'), 300)
			</script>
	<%		
		} 
	%>

	
    

</body>

	

	
</html>