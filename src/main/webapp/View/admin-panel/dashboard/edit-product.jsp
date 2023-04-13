<%@page import="Controller.product.ProductOperationsHandeler"%>
<%@page import="Model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product Details</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard-sidebar.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/add-product.css">
    </head>

    <body>
    
    	<%! Product product = null; %>
    
    	<%
	    	String operationType = request.getParameter("operationType");
			String productID = request.getParameter("productID");
			    		
			if(operationType != null && productID != null && operationType.equals("update")){
				
				product = ProductOperationsHandeler.getProductById(Integer.parseInt(productID));
				
			}
    	%>
    
        <aside>

        <section class="admin-details-wrapper">
            <div class="admin-img">
                <img src="${pageContext.request.contextPath}/assets/admin.png" alt="Admin" height="130px">
            </div>

            <div class="admin-details">
                <h3>Admin</h3>
                <p>admin@gmail.com</p>
            </div>
        </section>

        <section class="panel-functions">

            <div class="active-dashboard-tab panel-function">

                <img class="blue" src="${pageContext.request.contextPath}/assets/dashboard.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/dashboard/dashboard.jsp">Dashboard</a>

            </div>

            <div class="panel-function">
                <img src="${pageContext.request.contextPath}/assets/product.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/add-product/add-product.jsp"> Add Product </a>
            </div>

            <div class="panel-function">

                <img src="${pageContext.request.contextPath}/assets/cart.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/orders/view-orders.jsp">View Orders</a>

            </div>

        </section>

    </aside>
       


        <div class="admin-panel-wrapper">

            <section class="admin-panel-content">

                <h1>Edit Product Details</h1>

                <form action="${pageContext.request.contextPath}/UpdateProductDetails" method="post" class="add-product-form" enctype="multipart/form-data">
                
                	<input type="hidden" name="productID" value="<%=product.getProductID() %>">

                    <div class="input-fields-wrapper">
                        <div>
                            <label>Product Name</label>
                        </div>

                        <div>
                            <input type="text" value="<%=product.getProductName()%>" name="productName"  required>
                        </div>
                    </div>

                    <div class="input-fields-wrapper">
                        <div>
                            <label>Brand Name</label>
                        </div>

                        <div>
                            <input type="text" name="brandName" value="<%=product.getBrandName() %>" required>
                        </div>
                    </div>

                    <div class="input-fields-wrapper">
                        <div>
                            <label>Category</label>
                        </div>

                        <div>
                            <select name="productCategory" required id="productCategory">
                                <option value="">Select Category</option>
                                <option value="men">Men</option>
                                <option value="women">Women</option>
                                <option value="kids">Kids</option>
                                <option value="unisex">Unisex</option>
                            </select>
                        </div>
                    </div>

                    <div class="input-fields-wrapper">
                        <div>
                            <label>Image</label>
                        </div>

                        <div>
                            <input type="file" name="productImage" id="productImage" accept="image/*" value="http://localhost:8080/images/<%=product.getProductImgUrl()%>">
                            
                             <input type="hidden" name="oldProductImgUrl" accept="image/png,image/jpeg" value="<%=product.getProductImgUrl()%>">
                        </div>


                        <div id="displayProductImg">
                        	<img src="http://localhost:8080/images/<%=product.getProductImgUrl()%>" alt="<%=product.getProductName()%>">
                        </div>


                    </div>

                    <div class="input-multiple-fields-wrapper">

                        <div>
                            <div>
                                <label>Price</label>
                            </div>

                            <div>
                                <input type="number" step="any" min="100" name="productPrice" value="<%=product.getProductPrice() %>" required>
                            </div>
                        </div>

                        <div>
                            <div>
                                <label>Rating</label>
                            </div>

                            <div>
                                <input type="number" name="productRating" step=".01" max="5" min="0" value="<%=product.getProductRating() %>" required>
                            </div>
                        </div>

                        <div>
                            <div>
                                <label>Stock</label>
                            </div>

                            <div>
                                <input type="number" name="productStock"  min="1" value="<%=product.getProductStock() %>" required>
                            </div>
                        </div>

                    </div>


                    <button type="submit" class="add-product-btn">Save Changes</button>


                </form>

            </section>
        </div>
        
        <script>
        
        	const selectCategoryEl = document.querySelector('#productCategory');
        
        	<% 
        	
        		if(product != null){
        	%>
        	
        		selectCategoryEl.value = "<%=product.getProductCategory()%>";
        	
        	<% 
        		}
        	%>
        
        </script>

        <script src="${pageContext.request.contextPath}/JS/add-product.js"></script>
    </body>

    </html>