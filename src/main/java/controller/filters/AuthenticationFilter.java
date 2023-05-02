package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
//		
		HttpSession session = req.getSession(false);
//		
		boolean isAdminloggedIn = session != null && session.getAttribute("adminLogInSession") != null;
//		
		String uri = req.getRequestURI();
//		
//		boolean isCartCheckOutPage = uri.endsWith("cart-checkout.jsp") || uri.endsWith("cartOperations");
//		
//		//checking if the request made for admin pane or dashboard
		boolean isRequestForDashboard = uri.contains("admin-panel");
		


		if(!isAdminloggedIn &&  isRequestForDashboard ) {
			
			res.sendRedirect(req.getContextPath() + "/sign-in.jsp");
			
		} else {
			chain.doFilter(request, response);
		}
		 
		 
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
