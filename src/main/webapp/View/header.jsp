<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/header-footer.css">
</head>

<body>

    <script src="https://kit.fontawesome.com/1c6c06e40c.js" crossorigin="anonymous"></script>
    
    <div class="header-page-body"> 
    
 		<header class="header">

        <div class="header-section-1">

            <div class="logo-text">
                <p>Trendy Attire</p>
            </div>

            <div class="search-box-btn-wrapper">

                <button id="show-search-box-btn">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>

                <form action="${pageContext.request.contextPath}/View/pages/product-page.jsp" method="GET" class="search-form" id="search-box">
                    
                    <input type="hidden" name="operationType" value="searchProducts">
                    
                    <select name="searchBy" id="searchBy" required>
                        <option value="">Search By</option>
                        <option value="productCategory">Category</option>
                        <option value="productPrice">Price</option>
                        <option value="brandName">Brand Name</option>
                        <option value="productName">Product Name</option>
                    </select>

                    <div class="search-box-wrapper search-area">
                        <input type="text" placeholder="Search..." name="q" id="searchBox">
                        <button type="submit">
                            <i class="fa-solid fa-magnifying-glass search-area"></i>
                        </button>
                    </div>

                </form>

            </div>

            <div class="profile-cart-icons">

                <div id="profile-display-btn">
                    <i class="fa-regular fa-user"></i>

                    <div class="profile-drop-down">
                        <div>
                            <div class="user-desc">
                                <div class="user-img">
                                    <img src="${pageContext.request.contextPath}/Images/rabi.jpg" alt="">
                                </div>
                                <p>Rabi Giri</p>
                            </div>

                            <div style="border-top: 1px solid #ccc; margin:15px 0;"></div>

                            <ul class="user-functions">
                                <!-- Display when user is not signed in -->
                                 <li>
                                    <a href="${pageContext.request.contextPath}/sign-in.html">
                                        <i class="fa-solid fa-right-to-bracket"></i> Sign In
                                    </a>
                                </li>

                                <li>
                                    <a href="${pageContext.request.contextPath}/sign-up.html">
                                        <i class="fa-solid fa-user-plus"></i> Sign Up
                                    </a>
                                </li> 

                                <li>
                                    <a href="#">
                                        <i class="fa-regular fa-user"></i> View Profile
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <i class="fa-solid fa-pen-to-square"></i> Manage Profile
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <i class="fa-solid fa-lock"></i> Change Password
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <span>
                                            <i class="fa-solid fa-right-from-bracket"></i>
                                        </span>
                                        <span>
                                            Log Out
                                        </span>
                                    </a>
                                </li>
                            </ul>

                        </div>

                    </div>

                </div>

                <div>
                    <button id="cart-display-btn">
                        <i class="fa-solid fa-cart-plus"></i>
                    </button>
                </div>

            </div>

        </div>

        <div class="header-section-2">
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/home.jsp">Home</a></li>
                    <hr />
                    <li><a href="${pageContext.request.contextPath}/View/pages/product-page.jsp">Products</a></li>
                    <hr />
                    <li><a href="${pageContext.request.contextPath}/View/pages/contact-us.jsp">Contact us</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div style="border-bottom: 1px solid #ccc; margin-bottom:10px;"></div>

    <div class="cart-preview-wrapper-bg" id="cart-preview-bg">
        <div class="cart-preview-wrapper" id="cart-preview-sec">

            <p class="cart-preview-heading">
                <span>Shoping Cart</span>
                <button id="cart-hide-btn">
                    <span>&#10005;</span>
                </button>
            </p>

            <div style="border-top: 1px solid #ccc; margin:10px 0"></div>

            <div class="cart-preview-items">

                <div class="cart-preview-item">

                    <div class="cart-preview-item-img">
                        <img src="${pageContext.request.contextPath}/Images/kids.png" alt="">
                    </div>

                    <div class="cart-preview-item-desc">
                        <div>
                            <h3 class="item-name">
                                Legendary Whitetails Women's
                            </h3>
                            <p class="item-price">
                                <span>NPR 1231.00</span> X 3
                            </p>
                        </div>

                        <div class="cart-preview-item-delt-btn">
                            <button>&#10005;</button>
                        </div>
                    </div>

                </div>
            </div>

            <div class="cart-preview-details">
                <div style=" border-bottom: 2px solid #ccc; margin-bottom:10px;"></div>
                <p><span>Subtotal:</span> <span>NPR 2000</span></p>

                <div class="card-checkout-btn">
                    <a href="${pageContext.request.contextPath}/View/pages/cart-checkout.jsp">
                        <button>Checkout</button>
                    </a>
                </div>

            </div>

        </div>
    </div>
    
    </div>

    <script src="${pageContext.request.contextPath}/JS/header.js"></script>
</body>

</html>