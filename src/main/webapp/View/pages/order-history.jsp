<%@page import="model.OrderDetails"%>
<%@page import="java.util.List"%>
<%@page import="dao.OrderDAO"%>
<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Product Page</title>

    <script src="https://kit.fontawesome.com/1c6c06e40c.js" crossorigin="anonymous"></script>


     <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/profile-page.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/view-orders.css">

</head>


<body>

    <script src="https://kit.fontawesome.com/9c3cc2a6a3.js" crossorigin="anonymous"></script>
    
    <jsp:include page="/View/header.jsp"></jsp:include>

    <div class="wrapper-profile-page">


        <section class="profile-page-section">
            <div class="dashboard-user">
                <div class="personal-info">
                    <i class="fa-solid fa-user"></i>
                    <p><a href="${pageContext.request.contextPath}/View/pages/profile-page.jsp">Personal Info</a></p>
                </div>
                <div class="change-password">
                    <i class="fa-solid fa-lock"></i>
                    <p><a href="${pageContext.request.contextPath}/View/pages/change-password.jsp">Change Password</a>
                    </p>
                </div>
                <div class="history">
                    <i class="fa-solid fa-clock-rotate-left"></i>
                    <p><a href="order-history.jsp">My Orders</a></p>
                </div>
            </div>
            
            <div class="profile-desc-page">

            	<h1 style="text-align:center;">My Orders</h1>

	            <div class="order-list">
	                <table style="width:100%;">
	                    <tr>
	                        <th style="width: 15%;">SN</th>
	                        <th>Customer Name</th>
	                        <th style="width: 20%;">Order Total</th>
	                        <th style="width: 23%;">Order Date</th>
	                    </tr>
	
	
	                    <%
	                   		String[] userData = UserDAO.getCookiesData(request, "userData");	
	                    
	                    	OrderDAO orderdao = new OrderDAO();
	                    
	                    	List<OrderDetails> allOrders = orderdao.getAllOrderDetails();
	                    	
	                    	int SN = 1;
	
	                    	for(OrderDetails order: allOrders){
	                    		
	                    		if(order.getUserID() == Integer.parseInt(userData[0])){
						%>
								<tr>
			                        <td><%=SN++%></td>
			                        <td><%=order.getCustomerName() %></td>
			                        <td>NPR <%=order.getOrderTotal() %></td>
			                        <td><%=order.getOrderDate().toString().replace("T", " ") %></td>
			                        
			                        <td>

			                        	<form method="POST" action="order-items-list.jsp">
			                        	
			                        		<input type="hidden" name="orderID" value="<%=order.getOrderID()%>">
			                        		<input type="hidden" name="customerName" value="<%=order.getCustomerName()%>">
			                        		<input type="hidden" name="orderTotal" value="<%=order.getOrderTotal()%>">
			                        		<input type="hidden" name="orderDate" value="<%=order.getOrderDate().toString().replace("T", " ")%>">
			                        		
			                        		<button type="submit" style="all:unset; cursor:pointer; color:blue;">View Items</button>
			                        	
			                        	</form>
			                        </td>
			                        
			                    </tr>
			                    
	                    <%		}
	                    	}
	                    %>
	
	                </table>
	
	            </div>
            
            </div>


        </section>

    </div>
    
    <jsp:include page="/View/footer.jsp"></jsp:include>

    <script src="${pageContext.request.contextPath}/JS/change-password.js"> </script>
    

</body>

</html>