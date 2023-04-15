<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trendy Attire | CHECKOUT </title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/checkout-page.css">

</head>

<body class="contact-us-page-body">

	<jsp:include page="/View/header.jsp"></jsp:include>


    <div class="checkout-sec-wrapper">

        <h1>SHOPPING CART</h1>

        <div>
            <div class="cart-checkout-items">
                <table cellspacing=0>
                    <tr>
                        <th style="width:50%">Product</th>
                        <th style="width:20%">Price</th>
                        <th>Quantity</th>
                    </tr>

                    <tr>

                        <td>
                            <div class=" cart-checkout-item-desc">
                                <div class="item-img">
                                    <img src="../../Images/kids.png" width="100px">
                                </div>
                                <div>
                                    <p>
                                        Legendary Whitetails Wmen's.
                                    </p>
                                </div>
                            </div>
                        </td>

                        <td>
                            NPR 2000
                        </td>

                        <td>
                            <div class="cart-checkout-item-quantity-wrapper">
                                <div class=" cart-checkout-item-quantity">
                                    <button class="decreaseBtn" onclick="decreaseQuantity(0)">
                                        -
                                    </button>

                                    <input type="number" min="1" value="1" class="productQuantity">

                                    <button class="increaseBtn" onclick="increaseQuantity(0)">
                                        +
                                    </button>
                                </div>

                                <button class="cart-checkout-delt-btn">
                                    &#10005;
                                </button>
                            </div>

                        </td>
                    </tr>


                    <tr>

                        <td>
                            <div class=" cart-checkout-item-desc">
                                <div class="item-img">
                                    <img src="${pageContext.request.contextPath}/Images/kids.png" width="100px">
                                </div>
                                <div>
                                    <p>
                                        Legendary Whitetails Wmen's.
                                    </p>
                                </div>
                            </div>
                        </td>

                        <td>
                            NPR 2000
                        </td>

                        <td>
                            <div class="cart-checkout-item-quantity-wrapper">
                                <div class="cart-checkout-item-quantity">
                                    <button class="decreaseBtn" onclick="decreaseQuantity(1)">
                                        -
                                    </button>

                                    <input type="number" min="1" value="1" class="productQuantity">

                                    <button class="increaseBtn" onclick="increaseQuantity(1)">
                                        +
                                    </button>
                                </div>

                                <button class="cart-checkout-delt-btn">
                                    &#10005;
                                </button>
                            </div>

                        </td>
                    </tr>


                    <tr>

                        <td>
                            <div class=" cart-checkout-item-desc">
                                <div class="item-img">
                                    <img src="${pageContext.request.contextPath}/Images/kids.png" width="100px">
                                </div>
                                <div>
                                    <p>
                                        Legendary Whitetails Wmen's.
                                    </p>
                                </div>
                            </div>
                        </td>

                        <td>
                            NPR 2000
                        </td>

                        <td>
                            <div class="cart-checkout-item-quantity-wrapper">
                                <div class=" cart-checkout-item-quantity">
                                    <button class="decreaseBtn" onclick="decreaseQuantity(2)">
                                        -
                                    </button>

                                    <input type="number" min="1" value="1" class="productQuantity">

                                    <button class="increaseBtn" onclick="increaseQuantity(2)">
                                        +
                                    </button>
                                </div>

                                <button class="cart-checkout-delt-btn">
                                    &#10005;
                                </button>
                            </div>

                        </td>
                    </tr>

                </table>

                <button class="cart-update-btn">Update Cart</button>
            </div>

            <div class="order-price-details">
                <p><span>Subtotal</span> <span>NPR 1230</span></p>
                <p><span>Shipping Cost</span> <span>NPR 100</span></p>
                <div style="border-top: 1px solid #ccc; margin:20px 0;"></div>
                <p><span>Grand Total</span> <span>NPR 1330</span></p>

                <div class="cart-checkout-btn">
                    <button>
                        Proceed to Checkout
                    </button>
                </div>

            </div>
        </div>

    </div>
    
    <jsp:include page="/View/footer.jsp"></jsp:include>

    <script src="${pageContext.request.contextPath}/JS/checkout-page.js"></script>

</body>

</html>