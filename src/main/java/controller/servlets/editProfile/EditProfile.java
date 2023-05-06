package controller.servlets.editProfile;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
@MultipartConfig(maxFileSize = 16177215)
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstName = request.getParameter("fName");
		String lastName = request.getParameter("lName");
		String phoneNum = request.getParameter("phoneNum");
		String address = request.getParameter("address"); 
		String email = request.getParameter("email"); 
		
		String oldUserImg = request.getParameter("oldUserImg");
		Part userImgPart = request.getPart("userImage");
		
		String imgFilePath = MyConstants.IMAGE_DIR + "userImages/";
		
		String fileName = MyConstants.getImageUrl(userImgPart,  imgFilePath);
		
		UserDAO userdao = new UserDAO();
		
		if(fileName.equals("no new img")) {
			fileName = oldUserImg;
		}
		
		User user = new User(firstName, lastName, phoneNum, address, email, "", fileName);
		
		Boolean isUserDetailsUpdated = userdao.updateUserDetails(user);
		
		PrintWriter out = response.getWriter();
		
		if(isUserDetailsUpdated) {
			response.sendRedirect("View/pages/profile-page.jsp");
		} else {
			
			request.getRequestDispatcher("View/pages/profile-page.jsp").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Unable to Update User Details');");
			out.println("</script>");
		}
		
		
	}

}
