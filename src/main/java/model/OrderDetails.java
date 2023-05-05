package model;

import java.time.LocalDateTime;

public class OrderDetails {
	
	private int orderID;
	private int userID;
	private String customerName;
	private LocalDateTime orderDate;
	private double orderTotal;
	

	public OrderDetails(String customerName, LocalDateTime orderDate, double orderTotal) {
		super();
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
	}
		
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
