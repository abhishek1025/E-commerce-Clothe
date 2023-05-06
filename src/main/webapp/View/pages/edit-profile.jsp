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
            
            	UserDAO userdao = new UserDAO();
	        
	        	User user = userdao.getUserDataUsingEmail(userData[4]);
        	%>

            <div class="profile-desc-page">

                <form action="${pageContext.request.contextPath}/EditProfile" method="POST" enctype="multipart/form-data">

                    <div class="profile-img-wrapper">

                        <div class="profile-image" id="view-pp-img">
                            <img src="http://localhost:8080/images/userImages/<%=user.getUserImgUrl() %>" alt="" />
                        </div>

                        <div style="margin:20px 0;">
                            <input type="file" id="profileImgInput" name="userImage" accept="image/*">
                        </div>
                    </div>

                    <div class="user-input-edit-fields">
                    
                        <div class="full-name">
                            <input type="text" name="fName" placeholder="First name" value="<%=user.getfName()%>" required />
                            <input type="text" name="lName" placeholder="Last name" value="<%=user.getlName()%>" required />
                        </div>
                        <br />
                        <div class="phone-address">
                            <input type="text" name="phoneNum" placeholder="Phone number" value="<%=user.getPhoneNum() %>" required />
                            <input type="text" name="address" placeholder="Address" value="<%=user.getAddress() %>" required />
                        </div>
                        
                        <br />

                        <input type="hidden" name="email" placeholder="Email" value="<%=user.getEmail() %>" required />
						<input type="hidden" name="oldUserImg" value="<%=user.getUserImgUrl() %>" required />


                        <button type="submit" class="profile-save-changes-btn">
                            Save Changes
                        </button>

                    </div>

                </form>


            </div>


        </section>

    </div>
    
     <jsp:include page="/View/footer.jsp"></jsp:include>

    <script src="${pageContext.request.contextPath}/JS/edit-profile.js"> </script>
    

</body>

</html>