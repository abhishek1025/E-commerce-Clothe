package controller.servlets.cartOperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDAO;
import model.CartItem;

/**
 * Servlet implementation class cartOperationsServelt
 */
@WebServlet("/cartOperationsServelt")
public class CartOperationsServelt extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartOperationsServelt() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operationType = request.getParameter("cartOperationType");
		
		String pagePath = request.getParameter("pagePath");
		
		CartDAO cartdao = new CartDAO();
		

		if(operationType != null && operationType.equals("deleteCartItem")) {
			
			int cartItemID = Integer.parseInt(request.getParameter("cartItemID"));
			
			int queryResult = cartdao.deletCartItem(cartItemID);
			
			PrintWriter out = response.getWriter();
			
			request.getRequestDispatcher("View/pages/cart-checkout.jsp").include(request, response);

					
			if(queryResult == 1) {
				
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Cart item is deleted sucessfully'), 500);");
				out.println("</script>");
				
			} else if(queryResult == 2) {
				
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Unable to delete the cart item'), 500);");
				out.println("</script>");
			}
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String operationType = request.getParameter("cartOperationType");
		
		CartDAO cartdao = new CartDAO();
		
		//Adding product to the cart
		if(operationType == null) {
			
			int productID = Integer.parseInt(request.getParameter("productID"));
			int userID = Integer.parseInt(request.getParameter("userID")); 
			int quantity = 1;
			
		
			CartItem cartitem = new CartItem(userID, productID, quantity);
			
			
			int queryResult = cartdao.addProductToCart(cartitem);
			
			PrintWriter out = response.getWriter();
			
			request.getRequestDispatcher("View/pages/product-page.jsp").include(request, response);
					
			if(queryResult == 1) {
				
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Product is added to the cart'), 500);");
				out.println("</script>");
				
			} else if(queryResult == 2) {
				
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Product already exists in cart. The quantity of product is increase by 1'), 500);");
				out.println("</script>");
				
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Server Error'), 500);");
				out.println("</script>");
			}
			
		} else if(operationType.equals("updateCartQuantity")) {
			
			String[] cartItemIDs = request.getParameterValues("cartItemID");
			String[] cartQuantities = request.getParameterValues("quantity");

			int totalUpdatedRows = 0;
			
			PrintWriter out = response.getWriter();		

				
			for(int i = 0; i < cartItemIDs.length; i++ ) {
				
				int cartItemID = Integer.parseInt(cartItemIDs[i]);
				int quantity = Integer.parseInt(cartQuantities[i]);
			
				int updatedRows = cartdao.updateCartItem(cartItemID, quantity);
			
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
			
		}
		
	}

}
