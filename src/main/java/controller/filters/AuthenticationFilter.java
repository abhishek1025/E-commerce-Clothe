package controller.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ManageCookie;

public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false);
		
		boolean isAdminloggedIn = session != null && session.getAttribute("adminLogInSession") != null;
		boolean isUserloggedIn = session != null && session.getAttribute("userLogInSession") != null;
		
		String[] allowedURLForUser = {
				"/Advance-Programming-CW/", 
				"/Advance-Programming-CW/home.jsp",
				"/Advance-Programming-CW/View/pages/product-page.jsp", 
				"/Advance-Programming-CW/sign-in.jsp", 
				"/Advance-Programming-CW/sign-up.html", 
				"/Advance-Programming-CW/userRegister", 
				"/Advance-Programming-CW/userLogIn",
				"/Advance-Programming-CW/SignOutServlet",
				"/Advance-Programming-CW/error-file.html"
		};
		
		String[] allowedURLForAdminAfterAuthentication = {
				"/Advance-Programming-CW/View/admin-panel/dashboard/dashboard.jsp",
				"/Advance-Programming-CW/View/admin-panel/add-product/add-product.jsp",
				"/Advance-Programming-CW/AddProduct",
				"/Advance-Programming-CW/View/admin-panel/orders/view-orders.jsp",
				"/Advance-Programming-CW/View/admin-panel/orders/order-items.jsp",
				"/Advance-Programming-CW/View/admin-panel/dashboard/edit-product.jsp",
				"/Advance-Programming-CW/UpdateProductDetails",
				"/Advance-Programming-CW/DeleteProduct",
				
		};
		


		String uri = req.getRequestURI();
		
		// When user is logged in
	    if(uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".svg")) {
	    	
	    	chain.doFilter(request, response);
	    	
	    } else {
	    	if (isUserloggedIn) {
	    		
	    		
		        // Check if user is trying to access allowedURLForAdminAfterAuthentication
		        if (Arrays.asList(allowedURLForAdminAfterAuthentication).contains(uri)) {
		            res.sendRedirect(req.getContextPath() + "/sign-in.jsp");
		        } else {
		            chain.doFilter(request, response);
		        }
		    }
		    // Admin is logged in
		    else if (isAdminloggedIn) {
		    	
		    	
		        // Check if admin is trying to access other URLs than allowedURLForUser and allowedURLForAdminAfterAuthentication
		        if (!Arrays.asList(allowedURLForUser).contains(uri) && !Arrays.asList(allowedURLForAdminAfterAuthentication).contains(uri)) {
		        	res.sendRedirect(req.getContextPath() + "/sign-in.jsp");
		        } else {
		            chain.doFilter(request, response);
		        }
		    }
		    // User or admin is not logged in
		    else {
		    	
		        // Check if user is trying to access allowedURLForUser
		        if (Arrays.asList(allowedURLForUser).contains(uri)) {
		            chain.doFilter(request, response);
		            
		        } else {
		            res.sendRedirect(req.getContextPath() + "/sign-in.jsp");
		            
		        }
		    }
	    }
	    
	}
	
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
