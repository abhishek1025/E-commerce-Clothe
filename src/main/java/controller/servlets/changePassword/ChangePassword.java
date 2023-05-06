package controller.servlets.changePassword;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String oldPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		
		UserDAO userdao = new UserDAO();
		
		User user = userdao.getUserDataUsingEmail(email);
		
		System.out.println(user.getPassword());
		System.out.println(oldPassword);
		
		Boolean isPasswordChanged = userdao.changePassword(user.getPassword(), email, oldPassword, newPassword);
		
		PrintWriter out = response.getWriter();
		
		if(isPasswordChanged) {
			request.getRequestDispatcher("View/pages/change-password.jsp").include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Password is changed'), 500);");
			out.println("</script>");	
			
		} else if(isPasswordChanged != null) {
			
			request.setAttribute("passwordStatus", "wrongPassword");
			request.getRequestDispatcher("View/pages/change-password.jsp").include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Your current password is wrong'), 500);");
			out.println("</script>");
			
		} else{
			request.getRequestDispatcher("View/pages/change-password.jsp").include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Unable to change the password'), 500);");
			out.println("</script>");
		}
		
	}

}
