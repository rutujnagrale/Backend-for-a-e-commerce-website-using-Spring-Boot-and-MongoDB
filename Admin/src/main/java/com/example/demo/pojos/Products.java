package com.example.demo.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Products")  // Specifies that this class is mapped to the "Products" collection in MongoDB
public class Products {

    @Id  // Denotes that this is the primary key for MongoDB
    private int productID;
    
    private String name;
    private String description;
    private double price;
    private String category;
    private int stock;

    // Constructor
   
    public Products(int productID, String name, String description, double price, String category, int stock) {

		this.productID = productID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.stock = stock;
	}

	// Getters and Setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double d) {
        this.price = d;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

   
    @Override
    public String toString() {
        return "Products [productID=" + productID + 
               ", name=" + name + 
               ", description=" + description + 
               ", price=" + price + 
               ", category=" + category + 
               ", stock=" + stock + "]";
    }
}
