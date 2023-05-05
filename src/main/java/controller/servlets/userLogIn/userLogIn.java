package controller.servlets.userLogIn;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.Admin;
import model.User;

/**
 * Servlet implementation class userLogIn
 */
@WebServlet("/userLogIn")
@MultipartConfig(maxFileSize = 16177215)
public class userLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userLogIn() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accountType = request.getParameter("accountType");
		String userEmail = request.getParameter("email");
		String password = request.getParameter("password");
		
		int isUserLoggedIn = UserDAO.logInUser(userEmail, password, accountType);
		
		PrintWriter out = response.getWriter();
		
		if(isUserLoggedIn == 1) {
			
			if(accountType.equals("user")) {
				
				User user = UserDAO.getUserDataUsingEmail(userEmail);
				
				HttpSession session = request.getSession();
				session.setAttribute("userLogInSession", "log in session");
				session.setMaxInactiveInterval(30*60);
				
				Cookie cookieObj = new Cookie("userData", user.getUserID() + "|" + user.getfName() + "|" + user.getlName() + "|" + user.getUserImgUrl() + "|" + userEmail);
				
				cookieObj.setMaxAge(30*60);
				
				response.addCookie(cookieObj);
				
				response.sendRedirect("home.jsp");
				
			} else {
				
				Admin admin = UserDAO.getAminDataUsingEmail(userEmail);
				
				HttpSession session = request.getSession();
				session.setAttribute("adminLogInSession", "log in session");
				session.setMaxInactiveInterval(30*60);

				Cookie cookieObj = new Cookie("adminData", admin.getName().replace(" ", "|") + "|" + admin.getAdminImg());
				
				cookieObj.setMaxAge(30*60);
				
				response.addCookie(cookieObj);
				
				response.sendRedirect("View/admin-panel/dashboard/dashboard.jsp");
				
			}
			
		} else if(isUserLoggedIn == 2) {
			
			request.setAttribute("isPasswordMatched", "wrong password");
			
			request.getRequestDispatcher("sign-in.jsp").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Password does not match', 600));");
			out.println("</script>");
			
		} else if(isUserLoggedIn == 0) {
			
			request.getRequestDispatcher("sign-in.jsp").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('User not found', 600));");
			out.println("</script>");
			
		} else {
			request.getRequestDispatcher("sign-in.jsp").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Server Error'), 600));");
			out.println("</script>");
			
		}
		
	}

}
