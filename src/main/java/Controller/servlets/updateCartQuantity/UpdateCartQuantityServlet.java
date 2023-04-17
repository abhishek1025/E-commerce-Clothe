package Controller.servlets.updateCartQuantity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.DatabaseOperations.manageCartItems.CartOperationsHandleler;

/**
 * Servlet implementation class UpdateCartQuantity
 */
@WebServlet("/UpdateCartQuantity")
public class UpdateCartQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartQuantityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] cartItemIDs = request.getParameterValues("cartItemID");
		String[] cartQuantities = request.getParameterValues("quantity");
		
		String UPDATE_CART_ITEM_QUERY = "UPDATE CARTITEMS SET quantity = ? WHERE cartItemID = ?";
		
		int totalUpdatedRows = 0;
		
		PrintWriter out = response.getWriter();		

		try {
			
			for(int i = 0;i < cartItemIDs.length; i++ ) {
				
				int cartItemID = Integer.parseInt(cartItemIDs[i]);
				int quantity = Integer.parseInt(cartQuantities[i]);
			
				int updatedRows = CartOperationsHandleler.updateCartItem(UPDATE_CART_ITEM_QUERY,quantity, cartItemID,0,0);
			
				totalUpdatedRows += updatedRows;
			}
			
			request.getRequestDispatcher("View/pages/cart-checkout.jsp").include(request, response);
			
			if(totalUpdatedRows == cartItemIDs.length) {
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Cart Updated'), 500);");
				out.println("</script>");
				
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Unable to update quantity'), 500);");
				out.println("</script>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			request.getRequestDispatcher("View/pages/cart-checkout.jsp").include(request, response);
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Server Error'), 500);");
			out.println("</script>");
			
		}
	}

}
