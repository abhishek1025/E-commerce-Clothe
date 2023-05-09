package controller.servlets.cartOperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDAO;
import dao.ProductDAO;
import model.CartItem;

/**
 * Servlet implementation class cartOperationsServelt
 */
@WebServlet("/cartOperationsServelt")
public class CartOperations extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartOperations() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operationType = request.getParameter("cartOperationType");
		
		String pagePath = request.getParameter("pagePath");
		
		CartDAO cartdao = new CartDAO();
		

		if(operationType != null && operationType.equals("deleteCartItem")) {
			
			int cartItemID = Integer.parseInt(request.getParameter("cartItemID"));
			int productID = Integer.parseInt(request.getParameter("productID"));
			int quantity =  Integer.parseInt(request.getParameter("quantity"));
			
			try {
				ProductDAO productdao = new ProductDAO();
				productdao.manageProductStock("INCREASE STOCK", productID, quantity);
				
			} catch (SQLException e) {
				response.sendRedirect(request.getContextPath() + "/error-file.html");
			}
			
			int queryResult = cartdao.deletCartItem(cartItemID);
			
			PrintWriter out = response.getWriter();
			
					
			if(queryResult == 1) {
				
				request.getRequestDispatcher("View/pages/cart-checkout.jsp").include(request, response);
				
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Cart item is deleted sucessfully'), 500);");
				out.println("</script>");
				
			} else if(queryResult == -1 ) {
				
				response.sendRedirect(request.getContextPath() + "/error-file.html");
			}
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String operationType = request.getParameter("cartOperationType");
		
		CartDAO cartdao = new CartDAO();
		
		ProductDAO productdao = new ProductDAO();
		
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
				out.println("setTimeout(() => alert('Product already exists in cart. The quantity of product is increased by 1'), 500);");
				out.println("</script>");
				
			} else {
				response.sendRedirect(request.getContextPath() + "/error-file.html");
			}
			
		} else if(operationType.equals("updateCartQuantity")) {
			
			String[] cartItemIDs = request.getParameterValues("cartItemID");
			String[] productIDs = request.getParameterValues("productID");
			String[] cartOldQuantities = request.getParameterValues("oldQuantity");
			String[] cartNewQuantities = request.getParameterValues("newQuantity");
			
			System.out.print(Arrays.toString(productIDs));

			int totalUpdatedRows = 0;
			
			PrintWriter out = response.getWriter();		

				
			for(int i = 0; i < cartItemIDs.length; i++ ) {
				
				int cartItemID = Integer.parseInt(cartItemIDs[i]);
				int oldQuantity = Integer.parseInt(cartOldQuantities[i]);
				int newQuantity = Integer.parseInt(cartNewQuantities[i]);
				
				int productID = Integer.parseInt(productIDs[i]);
				
				int changeInQuantity = newQuantity - oldQuantity;
				
				/*
				 * New Quantity | old Quantity |  -> changeInQuantity = new - old
				 *	10	 		|	 5 		   |  -> 5 (+ve) decrease Stock 
				 *	2	  		|    5 		   |  -> -3 (-ve) increase stock
				 */
				
				try {
					
					if(changeInQuantity > 0) {
						int result = productdao.manageProductStock("DECREASE STOCK", productID, Math.abs(changeInQuantity));
					} else {
						int result = productdao.manageProductStock("INCREASE STOCK", productID, Math.abs(changeInQuantity));
					}
					
				} catch (SQLException e) {
					response.sendRedirect(request.getContextPath() + "/error-file.html");
				}

			
				int updatedRows = cartdao.updateCartItem(cartItemID, newQuantity);
			
				totalUpdatedRows += updatedRows;
			}
			
			
			if(totalUpdatedRows == cartItemIDs.length) {
				
				request.getRequestDispatcher("View/pages/cart-checkout.jsp").include(request, response);
				
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Cart Updated'), 500);");
				out.println("</script>");
				
			} else {
				response.sendRedirect(request.getContextPath() + "/error-file.html");
			}
			
		}
		
	}

}
