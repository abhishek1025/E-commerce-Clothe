<%@page import="Model.Product"%>
<%@page import="java.util.List"%>
<%@page import="Controller.product.ProductOperationsHandeler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard-sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard.css">
</head>

<body>


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

            <h1>Dashboard</h1>

            <div class="business-summary-wrapper">
                <div class="business-summary">
                    <img src="${pageContext.request.contextPath}/assets/dollar.svg" alt="Total Sales">

                    <div>
                        <p>Total Sales</p>
                        <span>124456</span>
                    </div>
                </div>

                <div class="business-summary">
                    <img src="${pageContext.request.contextPath}/assets/order.svg" alt="Total Order">

                    <div>
                        <p>Total Orders</p>
                        <span>124456</span>
                    </div>
                </div>

                <div class="business-summary">
                    <img src="${pageContext.request.contextPath}/assets/colorful-product.svg" alt="Total Products">

                    <div>
                        <p>Total Products</p>
                        <span>124456</span>
                    </div>
                </div>
            </div>

            <div class="product-list">

                <h2>Products</h2>

                <div style="border-top: 1px solid black; margin-top:15px"></div>

                <table>
                
                    <tr>
                        <th style="width: 10%;">SN</th>
                        <th>Product Name</th>
                        <th style="width: 20%;">Product Price</th>
                        <th style="width: 15%;">Stock</th>
                    </tr>
                    
                    <%
                    
                    	List<Product> products = ProductOperationsHandeler.getAllProducts();
                    	
                    	int SN = 1; 
                    
                    	for(Product product: products){
                    		
                    %> 
                    
                    	<%-- <div>
                    	
                    	
                    
                    		<img src="data:image/png;base64, <%=product.getProductImgFromDB()%>" alt="Img" height="200px">

                    	
                    	</div> --%>
                    
	                    <tr>
	                        <td><%=SN++ %></td>
	                        <td><%=product.getProductName() %></td>
	                        <td><%=product.getProductPrice() %></td>
	                        <td><%=product.getProductStock() %></td>
	                        <td>
	                             <a href="dashboard.jsp?operationType=delete&productID=<%=product.getProductID()%>">
	                                <img src="${pageContext.request.contextPath}/assets/delete.svg" alt="Delete" height="18px">
	                            </a> 
	                            
	                            <%-- <form method="post">
	                            	<input type="hidden" name="deleteProductID" value="<%=product.getProductID()%>">
	                            	
	                            	<button type="submit"> 
	                            		<img src="${pageContext.request.contextPath}/assets/delete.svg" alt="Delete" height="18px">
	                            	</button>
	                            	
	                            </form> --%>
	
	                        </td>
	
	                        <td>
	                            <a href="edit-product.jsp?operationType=update&productID=<%=product.getProductID()%>">
	                                <img src="${pageContext.request.contextPath}/assets/edit.svg" alt="Edit" height="18px">
	                            </a>
	
	                        </td>
	                    </tr> 
                    
                    <%
                    		
                    	}
                    %>
                    
                </table>

            </div>

        </section>

    </div>
    
    
    <%! int result = 0; %>
	
	<%
    	String productID = request.getParameter("productID");
		String operationType = request.getParameter("operationType");
		
		if(operationType != null && operationType.equals("delete")){		
			result = ProductOperationsHandeler.deleteProduct(Integer.parseInt(productID));
		}
	%>
    
    <%
		if(result > 0){
	%>
			<script type="text/javascript">
				alert("Product Deleted Succesfully");
			</script>
	<%		
		}/*  else if(result == 0){	 */	
	%>
		<%-- <script>
			alert("Unable to delete product");
		</script>
	<%
		}	
	%> --%>
	
    

</body>

	

	
</html>