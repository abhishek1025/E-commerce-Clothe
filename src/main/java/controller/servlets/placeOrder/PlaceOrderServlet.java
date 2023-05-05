package controller.servlets.placeOrder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDAO;
import dao.OrderDAO;
import model.OrderDetails;

/**
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int orderID = 0;
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		String userFullName = request.getParameter("userFullName");
		
		double orderTotal = Double.parseDouble(request.getParameter("orderTotal"));
		
		LocalDateTime orderDate = LocalDateTime.now();
		
		OrderDAO orderdao = new OrderDAO();
		CartDAO cartdao = new CartDAO();
		
		OrderDetails orderDetails = new OrderDetails(userFullName, orderDate, orderTotal);
		orderDetails.setUserID(userID);
		
		
		orderID =  orderdao.storeOrderDetails(orderDetails);
		
		ResultSet cartDetails = cartdao.getAllCartItems(request);
		
		
		try {
			
			
			while(cartDetails.next()) {
			
				int cartItemID = cartDetails.getInt(1);
				
				orderdao.storeOrderItems(cartItemID, orderID);
				orderdao.updateStatusOfCartItems(cartItemID);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		
		request.getRequestDispatcher("View/pages/cart-checkout.jsp").include(request, response);
		
		
		if(orderID != 0) {
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Order is placed sucessfully', 600));");
			out.println("</script>");
			
		} else {
			
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Unable to place the order', 600));");
			out.println("</script>");
			
		}
		
		

		
	}

}
