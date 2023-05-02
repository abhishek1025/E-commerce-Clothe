package controller.servlets.deleteProduct;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String productID = request.getParameter("productID");
		
		PrintWriter out = response.getWriter();
		
		if(productID != null) {
			
			Boolean isProductDeleted = ProductDAO.deleteProduct(Integer.parseInt(productID));
			
			request.getRequestDispatcher("View/admin-panel/dashboard/dashboard.jsp").include(request, response);
			
			if(isProductDeleted) {
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Product is deleted sucessfully'), 500);");
				out.println("</script>");		
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Unable to delete the product'), 500);");
				out.println("</script>");
			}
			
		}
		
	}

}
