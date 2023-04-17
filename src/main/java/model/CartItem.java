package model;

public class CartItem {
	
	int productID, userID, quantity;
	
	public CartItem(int userID, int productID, int quantity) {
		this.productID = productID;
		this.userID = userID;
		this.quantity = quantity;

	}
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
