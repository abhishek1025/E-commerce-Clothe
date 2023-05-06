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
          <p><a href="profile-page.jsp">Personal Info</a></p>
        </div>
        <div class="change-password">
          <i class="fa-solid fa-lock"></i>
          <p><a href="change-password.jsp">Change Password</a>
          </p>
        </div>
        <div class="history">
          <i class="fa-solid fa-clock-rotate-left"></i>
          <p><a href="order-history.jsp">My Orders</a></p>
        </div>
      </div>

      <div class="profile-desc-page">
        <%
        	String[] userData = ManageCookie.getCookiesData(request, "userData");
        
        	UserDAO userdao = new UserDAO();
        
        	User user = userdao.getUserDataUsingEmail(userData[4]);
        %>
        <div class="profile-img-wrapper">
          <div class="profile-image">
            <img src="http://localhost:8080/images/userImages/<%=user.getUserImgUrl() %>" alt="User" />
          </div>
        </div>
        
      

        <div class="user-desc-form">

          <div class="first-name-user">
            <label>First Name:</label>
            <p><%=user.getfName() %></p>
          </div>
          
          <div class="last-name-user">
            <label>Last Name:</label>
            <p><%=user.getlName() %></p>
          </div>
          
          <div class="email-user">
            <label>Email:</label>
            <p><%=user.getEmail() %></p>
          </div>
          
          <div class="address-user">
            <label>Address:</label>
            <p><%=user.getAddress() %></p>
          </div>
          
          <div class="phonenumber-user">
            <label>Phone number:</label>
            <p><%=user.getPhoneNum()%></p>
          </div>
          
          <div class="edit-changes-button">
            <a href="edit-profile.jsp">
            	<button id="edit-changes">Edit Changes</button>
            </a>
          </div>

        </div>
      </div>

    </section>

  </div>

 <jsp:include page="/View/footer.jsp"></jsp:include>

</body>

</html>