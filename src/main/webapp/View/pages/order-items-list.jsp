<%@page import="java.sql.ResultSet"%>
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
            
             <%
	        	String[] userData = UserDAO.getCookiesData(request, "userData");
        	%>
    
            <div class="profile-desc-page">

				 <section class="admin-panel-content">
		
		            <div class="order-item-list-wrapper" style="width:100%;">
		            
		            	                
		                <h1 style="text-align:center; margin:0;"> TRENDY ATTIRE</h1>
		                <h3 style="text-align:center;margin:0 0 20px 0;">INVOICE</h3>
		                
		
		                <div class="customer-details">
		                
		                <%
		                	int orderID = Integer.parseInt(request.getParameter("orderID"));
		                	String customerName = request.getParameter("customerName").toUpperCase();
		                	String orderDate = request.getParameter("orderDate");
		                	String orderTotal = request.getParameter("orderTotal");
		                	
		                	OrderDAO orderdao = new OrderDAO();
		                	
		                	ResultSet orderDetail = orderdao.getOrderDetailsByOrderID(orderID);
		                %>
		
		                    <div>
		                        <h4><%=customerName %></h4>
		                        <p>Date: <%=orderDate %></p>
		                        <p>Grand Total: <i> NPR <%=orderTotal %></i> </p>
		                    </div>
		
		                    <div>
		                        <p>Order ID: <%=orderID %></p>
		                    </div>
		
		                </div>
		
		                <table>
		                    <tr>
		                        <th style="text-align: left;">ITEM</th>
		                        <th style="width:16%">QUANTITY</th>
		                        <th style="width:21%">PRICE</th>
		                        <th style="width:21%">TOTAL</th>
		                    </tr>
		                    
		                    <%
		                    	while(orderDetail.next()){
		                    %>
		                    
		                    		<tr>
				                        <td style="text-align: left;" class="order-list-item-name">
				                            <img src="http://localhost:8080/images/<%=orderDetail.getString(2)%>"
				                                height="40px" width="auto">
				                            <p><%=orderDetail.getString(1) %></p>
				                        </td>
				                        <td><%=orderDetail.getInt(4)%></td>
				                        <td><%=orderDetail.getFloat(3)%></td>
				                        <td><%= orderDetail.getInt(4) * orderDetail.getFloat(3) %></td>
		                    		</tr>
		                    		
		                    <%		
		                    	}
		                    %>
		
		                  
		
		                </table>
		
		            </div>
		
		        </section>
            	
            </div>


        </section>

    </div>
    
    <jsp:include page="/View/footer.jsp"></jsp:include>

    <script src="${pageContext.request.contextPath}/JS/change-password.js"> </script>
    

</body>

</html>