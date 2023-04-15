package Controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.Product;
import Resources.MyConstants;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")

@MultipartConfig(maxFileSize = 16177215)

public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productName = request.getParameter("productName");
		String brandName = request.getParameter("brandName");
		String productCategory = request.getParameter("productCategory");
		Part productImgPart = request.getPart("productImage");

		System.out.print(request.getParameter("productPrice"));

		float productPrice = Float.parseFloat(request.getParameter("productPrice"));
		float productRating = Float.parseFloat(request.getParameter("productRating"));
		int productStock = Integer.parseInt(request.getParameter("productStock"));

		Product product = new Product();

		product.setProductName(productName);
		product.setBrandName(brandName);
		product.setProductCategory(productCategory);
		product.setProductPrice(productPrice);
		product.setProductRating(productRating);
		product.setProductStock(productStock);

		String fileName = ProductOperationsHandeler.getImageUrl(productImgPart);

		product.setProductImgUrl(fileName);

		String savePath = MyConstants.IMAGE_DIR;

		if (!fileName.isEmpty() && fileName != null) {
			productImgPart.write(savePath + fileName);
		}

		try {

			Boolean isProductAdded = ProductOperationsHandeler.addProduct(product);

			PrintWriter out = response.getWriter();

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./View/admin-panel/add-product/add-product.jsp");
			dispatcher.include(request, response);

			if (isProductAdded) {
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert('Prodcuct Added'), 500);");
				out.println("</script>");

			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("setTimeout(() => alert(' Unable to add product'), 500);");
				out.println("</script>");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
