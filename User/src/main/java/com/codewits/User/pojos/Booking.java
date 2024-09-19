package com.codewits.User.pojos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Booking")
public class Booking {
	
	@Id
	private String bookingId;
	
	
	private double totalcost;
	
	private int quantity;
	
	
	private User user;
	
	
	
	
	
	private Products product;
	
	
	
	
	
	
	
	public String getBookingId() {
		return bookingId;
	}







	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}







	public double getTotalcost() {
		return totalcost;
	}







	public void setTotalcost(double d) {
		this.totalcost = d;
	}







	public int getQuantity() {
		return quantity;
	}







	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}







	public User getUser() {
		return user;
	}







	public void setUser(User user) {
		this.user = user;
	}







	public Products getProduct() {
		return product;
	}







	public void setProduct(Products product) {
		this.product = product;
	}







	
	
	

}
