package controller.servlets.addToCart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDAO;
import dao.ProductDAO;
import model.CartItem;

/**
 * Servlet implementation class HandleAddToCartServlet
 */
@WebServlet("/product-page")
public class HandleAddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleAddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operationType = request.getParameter("cartOperationType");
		
		
		if(operationType == null) {
			
			int productID = Integer.parseInt(request.getParameter("productID"));
			int userID = Integer.parseInt(request.getParameter("userID")); 
			int quantity = 1;
			
		
			CartItem cartitem = new CartItem(userID, productID, quantity);
			
			String CART_ITEM_INSERT_QUERY = "INSERT INTO CARTITEMS (userID, productID, quantity) values (?,?,?)";
			
			int queryResult = CartDAO.addProductToCart(CART_ITEM_INSERT_QUERY, cartitem);
			
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
		} else {
			request.getRequestDispatcher("View/pages/product-page.jsp").include(request, response);
		}
		
		
		
	}

}
