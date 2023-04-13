package Model;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.http.Part;

import Resources.MyConstants;




public class Product {

	private String productName, brandName, productCategory, productImgUrl;
	private float productPrice, productRating;
	private int  productStock, productID;
	
	
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getProductCategory() {
		return productCategory;
	}
	
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	
	public float getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	
	public float getProductRating() {
		return productRating;
	}
	
	public void setProductRating(float productRating) {
		this.productRating = productRating;
	}
	
	public int getProductStock() {
		return productStock;
	}
	
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	
	
	public static String getImageUrl(Part part) {
		
		String savePath = MyConstants.IMAGE_DIR;
		
		File fileSaveDir = new File(savePath);
		
		String imageUrlFromPart = null;
		
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		String contentDisp = part.getHeader("content-disposition");
		
		String[] items = contentDisp.split(";");
		
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		
		if(imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "no new img";
		}
		
		return imageUrlFromPart;
	}

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}
	
	
	

}
