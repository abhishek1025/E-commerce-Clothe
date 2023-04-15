<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Contact us</title>
  <link rel="stylesheet" href="../../CSS/contact-us.css" />
  <link rel="stylesheet" href="../../CSS/header-footer.css">
</head>

<body>
   <script src="https://kit.fontawesome.com/1c6c06e40c.js" crossorigin="anonymous"></script>

  <section class="section-contact-us">
  
	<!-- Including Header  -->
	<jsp:include page="/View/header.jsp"></jsp:include>
	
    <div class="contact-us">
      <h2 class="heading">
        Love to hear from you <br>
        Get in touch 👋
      </h2>

      <form action="" class="form">
        <div class="form-section-1">
          <div class="form-page-1">
            <input type="text" placeholder="First Name" required />
            <input type="text" placeholder="Last Name" required />
          </div>

          <br />

          <div class="form-page-2">
            <input type="email" placeholder="Email" required />
            <br />
            <textarea name="textarea" id="textarea" cols="30" rows="10" placeholder="Write a message"
              required></textarea>
            <br />
            <div class="button-div-send">
              <button id="send-message">Send Message</button>
            </div>
          </div>
        </div>

        <div class="form-section-2">
          <h1 class="heading-1">Contact Information</h1>

          <div class="form-page-3">
            <div class="location">
              <i class="fa-solid fa-location-dot"></i>
              <p>Kathmandu, Nepal</p>
            </div>
            <div class="contact">
              <i class="fa-solid fa-phone"></i>
              <p>+977 9845668866</p>
            </div>
            <div class="email">
              <i class="fa-solid fa-envelope-open"></i>
              <p>trendy.attire@gmail.com</p>
            </div>
          </div>
        </div>
      </form>
    </div>
    <div class="map-section">
      <div style="width: 100%">
        <iframe width="100%" height="320px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
          src="https://maps.google.com/maps?width=100%25&amp;height=320px&amp;hl=en&amp;q=Islington%20college,%20Nepal+(Trendy%20Attire)&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"><a
            href="https://www.maps.ie/distance-area-calculator.html">measure area map</a></iframe>
      </div>
    </div>
    
    <!-- Including Footer  -->
	<jsp:include page="/View/footer.jsp"></jsp:include>

  </section>
</body>

</html>