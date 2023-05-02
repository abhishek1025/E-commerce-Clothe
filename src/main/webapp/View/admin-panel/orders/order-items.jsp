<%@page import="dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Add Product</title>
    
	<!-- Google Font CDN  -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
	    
	 <!-- CSS Files-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard-sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/view-orders.css">
</head>

<body>

   	<%! String[] adminData = {}; %>
	<% adminData = UserDAO.getCookiesData(request, "adminData"); %>

 	<aside>

        <section class="admin-details-wrapper">
        
            <div class="admin-img">
                <img src="http://localhost:8080/images/userImages/<%=adminData[2] %>" alt="Admin" height="130px">
            </div>

            <div class="admin-details">
                <h3><%=adminData[0]%>  <%=adminData[1] %></h3>
            </div>
            
        </section>

        <section class="panel-functions">

            <div class="panel-function">

                <img src="${pageContext.request.contextPath}/assets/dashboard.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/dashboard/dashboard.html">Dashboard</a>

            </div>

            <div class="panel-function">
                <img src="${pageContext.request.contextPath}/assets/product.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/add-product/add-product.jsp"> Add Product </a>
            </div>

            <div class="active-dashboard-tab panel-function">

                <img class="blue" src="${pageContext.request.contextPath}/assets/cart.svg" alt="Dashboard" height="18.5px">

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

            <h1>Orders</h1>

            <div class="order-item-list-wrapper">

                <div class="customer-details">

                    <div>
                        <h4>ABHISHEK SHRESTHA</h4>
                        <p>New Baneshwor, Kathmandu</p>
                    </div>

                    <div>
                        <p>Date: 2023/05/07</p>
                        <p>Order ID: 10</p>
                    </div>

                </div>

                <table>
                    <tr>
                        <th style="text-align: left;">ITEM</th>
                        <th style="width:16%">QUANTITY</th>
                        <th style="width:21%">PRICE</th>
                        <th style="width:21%">TOTAL</th>
                    </tr>

                    <tr>
                        <td style="text-align: left;" class="order-list-item-name">
                            <img src="https://cdn.shopify.com/s/files/1/0017/2100/8243/products/LRX-4_BLACK_2000x.jpg?v=1675198623"
                                height="40px" width="auto">
                            <p>Item Name</p>
                        </td>
                        <td>4</td>
                        <td>400</td>
                        <td>1600</td>
                    </tr>

                    <tr>
                        <td style="text-align: left;" class="order-list-item-name">
                            <img src="https://cdn.shopify.com/s/files/1/0017/2100/8243/products/LRX-4_BLACK_2000x.jpg?v=1675198623"
                                height="40px" width="auto">
                            <p>Item Name</p>
                        </td>
                        <td>4</td>
                        <td>400</td>
                        <td>1600</td>
                    </tr>

                    <tr>
                        <td style="text-align: left;" class="order-list-item-name">
                            <img src="https://cdn.shopify.com/s/files/1/0017/2100/8243/products/LRX-4_BLACK_2000x.jpg?v=1675198623"
                                height="40px" width="auto">
                            <p>Item Name</p>
                        </td>
                        <td>4</td>
                        <td>400</td>
                        <td>1600</td>
                    </tr>

                    <tr>
                        <td style="text-align: left;" class="order-list-item-name">
                            <img src="https://cdn.shopify.com/s/files/1/0017/2100/8243/products/LRX-4_BLACK_2000x.jpg?v=1675198623"
                                height="40px" width="auto">
                            <p>Item Name</p>
                        </td>
                        <td>4</td>
                        <td>400</td>
                        <td>1600</td>
                    </tr>
                </table>

            </div>

        </section>
    </div>
</body>

</html>