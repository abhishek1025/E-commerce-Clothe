<%@page import="utils.ManageCookie"%>
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
                    <p><a href="${pageContext.request.contextPath}/View/pages/order-history.jsp">My Orders</a></p>
                </div>
            </div>
            
             <%
	        	String[] userData = ManageCookie.getCookiesData(request, "userData");
        	%>
    
            <div class="profile-desc-page">

                 <form action="${pageContext.request.contextPath}/ChangePassword" method="POST" class="change-password-form" id="changePasswordForm">
                 
                 	<input type="hidden" name="email" value="<%=userData[4]%>">

                    <div>

                        <h1>Change Password</h1>

                        <% 
                        	String passwordStatus = (String) request.getAttribute("passwordStatus");
                        	if(passwordStatus != null && passwordStatus.equals("wrongPassword")){
                        %>
	                        	<div>
		                            <input type="password" name="currentPassword" value="<%=request.getParameter("currentPassword")%>"  required>
		                        </div>
		
		                        <div>
		                            <input type="password" name="newPassword" value="<%=request.getParameter("newPassword")%>"  id="newPassword" >
		                        </div>
		
		                        <div>
		                            <input type="password" name="confirmPassword" value="<%=request.getParameter("confirmPassword")%>" 
		                                id="confirmPassword" required>
		                        </div>
	                        
                        <% } else {  %>
                        
	                        	<div>
		                            <input type="password" name="currentPassword" placeholder="Current Password" required>
		                        </div>
		
		                        <div>
		                            <input type="password" name="newPassword" placeholder="New Password" id="newPassword" >
		                        </div>
		
		                        <div>
		                            <input type="password" name="confirmPassword" placeholder="Confirm Password"
		                                id="confirmPassword" required>
		                        </div>
                        
                        <%} %>

                        <div style="text-align: center;">

                            <button type="submit">
                                Change Password
                            </button>

                        </div>

                    </div>

                </form>


            </div>


        </section>

    </div>
    
    <jsp:include page="/View/footer.jsp"></jsp:include>

    <script src="${pageContext.request.contextPath}/JS/change-password.js"> </script>
    

</body>

</html>