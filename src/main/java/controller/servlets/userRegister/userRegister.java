package controller.servlets.userRegister;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import appConstants.MyConstants;
import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class userRegister
 */
@WebServlet("/userRegister")
@MultipartConfig(maxFileSize = 16177215)
public class userRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userRegister() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("fName");
		String lastName = request.getParameter("lName");
		String phoneNum = request.getParameter("phoneNum");
		String address = request.getParameter("address"); 
		String email = request.getParameter("email"); 
		String password = request.getParameter("password");
		
		Part userImgPart = request.getPart("userImage");
		
		String imgFilePath = MyConstants.IMAGE_DIR + "userImages/";
		
		String imgUrl = MyConstants.getImageUrl(userImgPart,imgFilePath);
		
		User user = new User(firstName, lastName, phoneNum, address, email, password, imgUrl);

		PrintWriter out = response.getWriter();
		
		int isUserRegistered = UserDAO.registerUser(MyConstants.INSERT_USER_QUERY, user);
		
		if(isUserRegistered == 1) {
			request.getRequestDispatcher("sign-up.html").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('User Registered'), 500);");
			out.println("</script>");
			
			
		} else if (isUserRegistered == 0) {
			request.getRequestDispatcher("sign-up.html").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('User Already Exists'), 500);");
			out.println("</script>");
			
			
			
		} else {
			request.getRequestDispatcher("sign-up.html").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Server Error'), 500);");
			out.println("</script>");
						
		}	
			

	}

}
