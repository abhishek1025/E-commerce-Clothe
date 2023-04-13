package Controller.product;

import java.io.IOException;
import java.io.InputStream;
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

//This annotation defines the maximum
//file size which can be taken.
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productName = request.getParameter("productName");
		String brandName = request.getParameter("brandName");
		String productCategory = request.getParameter("productCategory");
		Part productImgPart = request.getPart("productImage");
		
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
		
		
		String fileName = Product.getImageUrl(productImgPart);
		
		product.setProductImgUrl(fileName);
		
		String savePath = MyConstants.IMAGE_DIR;
		
		 if(!fileName.isEmpty() && fileName != null) {
			 productImgPart.write(savePath + fileName);
		}
		
		int result = 0;
		
		try {
			result = ProductOperationsHandeler.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./View/admin-panel/add-product/add-product.jsp");
		dispatcher.include(request, response);
		
		if(result > 0)
		{		
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert('Prodcuct Added'), 500);");
//			out.println("location='index.jsp';");
			out.println("</script>");
			
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("setTimeout(() => alert(' Unable to add product'), 500);");
//			out.println("location='index.jsp';");
			out.println("</script>");
		}
		
		
	}

}
