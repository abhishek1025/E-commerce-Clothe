<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Trendy Attire | Sign in</title>
  <link rel="stylesheet" href="CSS/sign-in.css" />
</head>

<body>

  <div class="store-name">
    <h1>
      <a href="home.jsp">Trendy Attire</a>
    </h1>
    <p>
      <a href="home.jsp">Transform your style</a>
    </p>
  </div>

  <section class="wrapper-sign-in-page">
  
    <div class="wrapper-sign">
    
      <div class="company-desc-section">
        <img src="Images/profile-man.png" alt="Banner" />
      </div>
      
      <div class="sign-in-form-section">
      
        <form action="userLogIn" id="form-sign-in" method="POST" enctype="multipart/form-data">
        
          <h2>Sign in</h2>
          
          <div class="email-pass">
          
          <select name="accountType" style="width:302px; height:36px; margin-top:20px;" required>
          
          	<option value="">Choose Account Type</option>
          	<option value="admin">Admin</option>
          	<option value="user">User</option>
          
          </select>
          
          <br />
		  <br />
          
          	<%
          		String isPasswordMatched = (String) request.getAttribute("isPasswordMatched");
          	
          		if(isPasswordMatched != null && isPasswordMatched.equals("wrong password")){
          	%>		
	          		<input type="text" name="email" placeholder="Email"  value="<%=request.getParameter("email") %>" required />
	           		 <br />
	            	<br />
	            	<input type="password" name="password" placeholder="Password" value="<%=request.getParameter("password") %>" required />
          	
          	<%} else { %>
         
			 		<input type="text" name="email" placeholder="Email" required />
		            <br />
		            <br />
		            <input type="password" name="password" placeholder="Password" required /> 
            <%} %>
          </div>
          
          <div class="button-login-sign-in">
          
            <button id="login-sign-in" type="submit">Login</button>
            
          </div>
          
          <p>Don't have an account? <a href="./sign-up.html">Sign up</a></p>
          
        </form>
        
      </div>
      
    </div>
    
  </section>
</body>

</html>    