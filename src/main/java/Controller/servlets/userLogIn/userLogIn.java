package Controller.servlets.userLogIn;

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

import Controller.DatabaseOperations.UserOperationsHandleler;
import Model.User;

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
		
		String userEmail = request.getParameter("email");
		String password = request.getParameter("password");
		
		int isUserLoggedIn = UserOperationsHandleler.logInUser(userEmail, password);
		
		PrintWriter out = response.getWriter();
		
		if(isUserLoggedIn == 1) {
			/*
			 * The boolean parameter 'false' has been passed so that a new session is not 
			 * created since the session already exists
			 * */
			
			User user = UserOperationsHandleler.getUserDataUsingEmail(userEmail);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("UserName", user.getfName());
			
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);

			Cookie userName = new Cookie("userData", user.getUserID() + "|" +user.getfName() + "|" + user.getlName() + "|" + user.getUserImgUrl());
			
			userName.setMaxAge(30*60);
			
			response.addCookie(userName);
			
			response.sendRedirect("home.jsp");
			
		} else if(isUserLoggedIn == 2) {
			
			request.setAttribute("isPasswordMatched", "wrong password");
			
			request.getRequestDispatcher("sign-in.jsp").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Password does not match'), 500);");
			out.println("</script>");
			
		} else if(isUserLoggedIn == 0) {
			
			request.getRequestDispatcher("sign-in.jsp").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('User not found, please create an account'), 500);");
			out.println("</script>");
			
		} else {
			request.getRequestDispatcher("sign-in.jsp").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Server Error'), 500);");
			out.println("</script>");
			
		}
		
	}

}
