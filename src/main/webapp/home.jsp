<%@page import="Resources.MyConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>TRENDY ATTIRE</title>
  <link rel="stylesheet" href="CSS/header-footer.css" />
  <link rel="stylesheet" href="CSS/home-page.css" />
 
</head>

<body>

	<script src="https://kit.fontawesome.com/1c6c06e40c.js" crossorigin="anonymous"></script>

 	<!-- Including Header JSP -->
 	<jsp:include page="View/header.jsp"></jsp:include>

	<!-- Home page Content -->
  <section class="section-home-content">

    <div class="wrapper-home-section">
      <div class="white-collection-banner">
        <div class="required-image">
          <img src="Images/image1.png" alt="" />
          <img src="Images/image2.png" alt="" />
        </div>
        <div class="required-texts">
          <h1>
            The White <br />
            Collection
          </h1>
          <div class="shop-now-button">
            <button id="shop-now">Shop Now</button>
          </div>
        </div>
      </div>
      <div class="code-discount">
        <div class="ten-off">
          <h1>Get 10% Off</h1>
          <p>on orders over 1500</p>
        </div>
        <div class="twenty-off">
          <h1>Get 20% Off</h1>
          <p>on orders over 4000</p>
        </div>
        <div class="thirty-off">
          <h1>Get 30% Off</h1>
          <p>on orders over 10000</p>
        </div>
        <div class="code-discount-button">
          <button id="code-discount">Code: Discount</button>
        </div>
      </div>
      <div class="dress-description-section">
        <div class="women-collection">
          <h1>Women's Collection</h1>
          <a href="">Shop Now</a>
        </div>
        <div class="men-collection">
          <h1>Men's Collection</h1>
          <a href="">Shop Now</a>
        </div>
        <div class="unisex-collection">
          <h1>Unisex's Collection</h1>
          <a href="">Shop Now</a>
        </div>
        <div class="kids-collection">
          <h1>Kids Collection</h1>
          <a href="">Shop Now</a>
        </div>
      </div>

      <!-- customer feedback section -->
      <h1 class="customer-feedback-heading">Customer Feedback</h1>
      <div class="customer-feedback">
        <div class="feedback-1">
          <div class="customer-image">
            <img src="Images/customer1.png" alt="" />
          </div>
          <div class="customer-message">
            <br />
            <p>
              “We love our new duds and continue to be grateful for your
              efforts on behalf of our planet.”
            </p>
            <br />
            <span>Mary Jane</span>
          </div>
        </div>
        <div class="feedback-2">
          <div class="customer-image">
            <img src="Images/customer2.png" alt="" />
          </div>
          <div class="customer-message">
            <br />
            <p>
              "Thank you for your gracious hospitality hosting the W.H.A.T.
              Group! The tops I bought are beautiful!"
            </p>
            <br />
            <span>Olivia Gracia</span>
          </div>
        </div>
        <div class="feedback-3">
          <div class="customer-image">
            <img src="Images/customer3.png" alt="" />
          </div>
          <div class="customer-message">
            <br />
            <p>
              “We love our new duds and continue to be grateful for your
              efforts on behalf of our planet.”
            </p>
            <br />
            <span>Ada Smith</span>
          </div>
        </div>
      </div>
    </div>


  </section>

	<!-- Including Footer  -->
	<jsp:include page="View/footer.jsp"></jsp:include>
	
</body>

</html>