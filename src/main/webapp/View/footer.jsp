<%@page import="appConstants.MyConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Footer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/header-footer.css">
   
</head>

<body>

	 <script src="https://kit.fontawesome.com/1c6c06e40c.js" crossorigin="anonymous"></script>

    <footer>

        <div class="footer-section-1">

            <div class="categories">
                <p>Categories</p>
                <br />
                <a href="${pageContext.request.contextPath}/<%=MyConstants.URL_FOR_FILTERING_PRODUCTS%>&category=men">Men</a>
                <a href="${pageContext.request.contextPath}/<%=MyConstants.URL_FOR_FILTERING_PRODUCTS%>&category=women">Women</a>
                <a href="${pageContext.request.contextPath}/<%=MyConstants.URL_FOR_FILTERING_PRODUCTS%>&category=kids">Kids</a>
                <a href="${pageContext.request.contextPath}/<%=MyConstants.URL_FOR_FILTERING_PRODUCTS%>&category=unisex">Unisex</a>
            </div>

            <div class="account">
                <p>Account</p>
                <br />
                <a href="${pageContext.request.contextPath}/sign-up.html">Sign up</a>
                <a href="${pageContext.request.contextPath}/sign-in.jsp">Sign in</a>
            </div>

            <div class="quick-links">
                <p>Quick Links</p>
                <br />
                <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
                <a href="${pageContext.request.contextPath}/View/pages/product-page.jsp">Products</a>
                <a href="${pageContext.request.contextPath}/View/admin-panel/dashboard.jsp">Dashboard</a>
            </div>

        </div>

        <div style="border-top:1px solid white; margin:30px 0;"></div>

        <div class="footer-section-2">
            <div class="footer-logo-text">
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

        <div style="border-top:1px solid white; margin:30px 0;"></div>

        <div class="footer-section-3">
            <p><i>© 2023 Trendy Attire. All Rights Reserved.</i></p>
            <br />
        </div>
    </footer>
</body>

</html>    