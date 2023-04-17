<%@page import="java.sql.ResultSet"%>
<%@page import="dao.CartDAO"%>
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

	<%!int totalCost;%>

    <div class="checkout-sec-wrapper">

        <h1>SHOPPING CART</h1>

        <div>
            <div class="cart-checkout-items">
            
                <form method="POST" action="${pageContext.request.contextPath}/UpdateCartQuantity">
                
                	<table cellspacing=0>
                    
	                    <tr>
	                        <th style="width:50%">Product</th>
	                        <th style="width:20%">Price</th>
	                        <th>Quantity</th>
	                    </tr>
	
						
						<%
							ResultSet cartItems = CartDAO.getAllCartItems(request);
							int itemSN = -1;
							totalCost = 0;
							if(cartItems != null){
																			
								while(cartItems.next()){
									totalCost += (cartItems.getInt(5) * cartItems.getInt(3)); 								
									itemSN++;
						%>			
									<tr>
				                       <td>
				                            <div class=" cart-checkout-item-desc">
				                                <div class="item-img">
				                                    <img src="http://localhost:8080/images/<%=cartItems.getString(4) %>" width="100px">
				                                </div>
				                                <div>
				                                    <p>
				                                       <%=cartItems.getString(2) %>
				                                    </p>
				                                </div>
				                            </div>
				                        </td>
	
				                        <td>
				                            NPR <%=cartItems.getInt(3) %>
				                        </td>
	
				                        <td>
				                            <div class="cart-checkout-item-quantity-wrapper">
				                            
				                                <div class=" cart-checkout-item-quantity">
				                                
				                                    <button type="button" class="decreaseBtn" onclick="decreaseQuantity(<%=itemSN%>)">
				                                        -
				                                    </button>
				                                    
													<input type="hidden" name="cartItemID" value="<%=cartItems.getInt(1)%>"/>
													
				                                    <input type="number" min="1" name="quantity" value="<%=cartItems.getInt(5)%>" class="productQuantity">
	
				                                    <button type="button" class="increaseBtn" onclick="increaseQuantity(<%=itemSN%>)">
				                                        +
				                                    </button>
				                                </div>
	
				                                
				                                <a class="cart-checkout-delt-btn" href="${pageContext.request.contextPath}/View/pages/cart-checkout.jsp?cartOperationType=deleteCartItem&cartItemID=<%=cartItems.getInt(1)%>">
				                                	<!-- <button type="button" class="cart-checkout-delt-btn"> -->
				                                   		 &#10005;
				                               		<!-- </button> -->
				                                </a>
				                                	
	
				                            </div>
	
				                        </td>
				                    </tr>
				                    
						<%
								}	
							}
						%>
						                  

                	</table>
                
                
                	<button type="submit" class="cart-update-btn">Update Cart</button>
                
                </form>
                
                
                
            </div>

            <div class="order-price-details">
                <p><span>Subtotal</span> <span>NPR <%=totalCost %></span></p>
                <p><span>Shipping Cost</span> <span>NPR 100</span></p>
                <div style="border-top: 1px solid #ccc; margin:20px 0;"></div>
                <p><span>Grand Total</span> <span>NPR <%=totalCost + 100 %></span></p>

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