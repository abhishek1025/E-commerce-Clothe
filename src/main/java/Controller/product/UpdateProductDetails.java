package Controller.product;

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

import Model.Product;
import Resources.MyConstants;

/**
 * Servlet implementation class UpdateProductDetails
 */
@WebServlet("/UpdateProductDetails")
@MultipartConfig(maxFileSize =  16177215)
public class UpdateProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productID = Integer.parseInt(request.getParameter("productID"));
		String productName = request.getParameter("productName");
		String brandName = request.getParameter("brandName");
		String productCategory = request.getParameter("productCategory");
		Part productImgPart = request.getPart("productImage");
		String oldProductImgUrl = request.getParameter("oldProductImgUrl");

		float productPrice = Float.parseFloat(request.getParameter("productPrice"));
		float productRating = Float.parseFloat(request.getParameter("productRating"));
		int productStock = Integer.parseInt(request.getParameter("productStock"));
		

		Product product = new Product();
		
		product.setProductID(productID);
		product.setProductName(productName);
		product.setBrandName(brandName);
		product.setProductCategory(productCategory);
		product.setProductPrice(productPrice);
		product.setProductRating(productRating);
		product.setProductStock(productStock);
		
	
		String fileName = ProductOperationsHandeler.getImageUrl(productImgPart);
		
		if(fileName.equals("no new img")) {
			
			product.setProductImgUrl(oldProductImgUrl);
						
		} else{
			product.setProductImgUrl(fileName);
			
			String savePath = MyConstants.IMAGE_DIR;
			
			 if(!fileName.isEmpty() && fileName != null) {
				 productImgPart.write(savePath + fileName);
			}
		}
	
		
		try {
			
			PrintWriter out = response.getWriter();
			Boolean isProductDetailsChanged = ProductOperationsHandeler.updateProductDetails(product);
			
			if(isProductDetailsChanged)
			{		
				response.sendRedirect("./View/admin-panel/dashboard/dashboard.jsp");
//				out.println("<script type=\"text/javascript\">");
//				out.println("alert('Prodcuct Added');");
//				out.println("</script>");
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Unable to Update Details of the product');");
				out.println("</script>");
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

			
	}

}
