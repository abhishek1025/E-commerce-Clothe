<%@page import="utils.ManageCookie"%>
<%@page import="dao.OrderDAO"%>
<%@page import="dao.UserDAO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="appConstants.MyConstants"%>
<%@page import="dao.ProductDAO"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
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

	<script src="https://kit.fontawesome.com/9c3cc2a6a3.js" crossorigin="anonymous"></script>
 	
 	<%! List<Product> products = null; %>
 	<% products = ProductDAO.getAllProducts(false); %>
 	
 	<%! String[] adminData = {}; %>
	<% adminData = ManageCookie.getCookiesData(request, "adminData"); %>
	
	<%
		OrderDAO orderdao = new OrderDAO();
		double[] totalSalesAndOrdersData = orderdao.getTotalSalesAndTotalOrders();
	%>

 	<aside>

        <section class="admin-details-wrapper">
        
            <div class="admin-img">
            
            	<% if(adminData.length != 0){ %>
            
               	 <img src="http://localhost:8080/images/userImages/<%=adminData[2] %>" alt="Admin" height="130px">
               	 
               	<%}%>
                
            </div>

            <div class="admin-details">
                <% if(adminData.length != 0){
                	out.print("<h3>"+ adminData[0] + adminData[1]+"</h3>");
                }%>
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
            
             <div class="panel-function">

                <img src="${pageContext.request.contextPath}/assets/sign-out.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/SignOutServlet">Sign out</a>

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
                        <span>NPR <%=totalSalesAndOrdersData[1] %></span>
                    </div>
                </div>

                <div class="business-summary">
                    <img src="${pageContext.request.contextPath}/assets/order.svg" alt="Total Order">

                    <div>
                        <p>Total Orders</p>
                        <!-- Type caseting-->
                        <span><%=(int) totalSalesAndOrdersData[0]%></span>
                    </div>
                </div>

                <div class="business-summary">
                    <img src="${pageContext.request.contextPath}/assets/colorful-product.svg" alt="Total Products">

                    <div>
                        <p>Total Products</p>
						<span><%=products.size() %></span>
                    </div>
                </div>
            </div>

            <div class="product-list">

                <h2>Products</h2>

                <div style="border-top: 1px solid black; margin-top:15px"></div>

                    <div class="product-cards-wrapper">
                    

                    	<!-- Displaying products -->
                    <% 
                    
                    	int formIndex = 0;
                    
                    	for(Product product: products){ 
                    %>
                    	
	                    	<div class="product-card">
					            <img class="product-img" src="http://localhost:8080/images/<%=product.getProductImgUrl()%>" alt="<%=product.getProductImgUrl()%>">
					            <p class="product-title"><%=product.getProductName()%></p>
                                
					            <div class="product-desc">
                                    <p><i>NPR <%=product.getProductPrice()%> </i></p>
                                    <p>In Stock (<%=product.getProductStock()%>)</p>
                                </div>
					
					            <div class="product-card-btn">
					
								<a href="${pageContext.request.contextPath}/View/admin-panel/dashboard/edit-product.jsp?operationType=update&productID=<%=product.getProductID()%>">
					                <button class="edit-btn">
					                    <img src="${pageContext.request.contextPath}/assets/edit.svg" height="12px"> <span>Edit</span>
					                </button>
					             </a>
					
								
					            <form method="POST" action="${pageContext.request.contextPath}/DeleteProduct" onsubmit="confirmDeltOperation(<%formIndex++;%>)" class="deletProductForm">
					            
					                <input type="hidden" name="productID" value="<%=product.getProductID()%>">
					                
									<button type="submit" class="delete-btn">
						         		<img src="${pageContext.request.contextPath}/assets/delete.svg" height="12px"><span>Delete</span>
						            </button>
					                
								</form>
								
								
					            </div>
					            
					            
					        </div>
					       
					  <% } %>                    
                    </div>


            </div>

        </section>

    </div>
    
    <script>
    
	    const productDeltBtn = document.querySelector("#productDeltBtn");
	    const deletProductForms = document.querySelectorAll(".deletProductForm");
	    
	  	function confirmDeltOperation(formIndex){
	  		
	        const deleteProduct = window.confirm("Are you sure you want to delete this product?")
	
	        if (deleteProduct) {
	            deletProductForm[formIndex].submit();
	        }
	
	
	    } 
    </script>
    

</body>

</html>