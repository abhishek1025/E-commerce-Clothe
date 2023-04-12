<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard-sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/view-orders.css">
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

            <div class="panel-function">

                <img src="${pageContext.request.contextPath}/assets/dashboard.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/dashboard/dashboard.jsp">Dashboard</a>

            </div>

            <div class="panel-function">
                <img src="${pageContext.request.contextPath}/assets/product.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/add-product/add-product.jsp"> Add Product </a>
            </div>

            <div class="active-dashboard-tab panel-function">

                <img class="blue" src="${pageContext.request.contextPath}/assets/cart.svg" alt="Dashboard" height="18.5px">

                <a href="${pageContext.request.contextPath}/View/admin-panel/orders/view-orders.jsp">View Orders</a>

            </div>

        </section>

    </aside>


    <div class="admin-panel-wrapper">

        <section class="admin-panel-content">

            <h1>Orders</h1>

            <div class="order-list">
                <table>
                    <tr>
                        <th style="width: 15%;">Order ID</th>
                        <th>Customer Name</th>
                        <th style="width: 20%;">Order Total</th>
                        <th style="width: 20%;">Order Date</th>
                    </tr>


                    <tr>
                        <td>1</td>
                        <td>Abhishek Shrestha</td>
                        <td>5000</td>
                        <td>2022/05/08</td>
                        <td><a href="order-items.jsp">View Items</a></td>
                    </tr>

                 

                </table>
            </div>

        </section>
    </div>
</body>

</html>