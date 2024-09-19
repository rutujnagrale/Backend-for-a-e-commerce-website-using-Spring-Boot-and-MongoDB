package com.example.demo.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojos.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.pojos.Admin;
import com.example.demo.pojos.Products;


@RestController
public class AdminController {
	
	@Autowired
	private AdminRepository adminrepo;
	
	
	@Autowired
	private UserRepository userrepo;

	
	@Autowired
    private	ProductRepository productrepo;
	
	@PostMapping("/addadmin")
	public Admin addadmin(@RequestBody Admin admin) {
		adminrepo.save(admin);
		return admin;
	}
	
	@PostMapping("/addproduct")
	public Products addproduct(@RequestBody Products product) {
	    productrepo.save(product);
	    return product;
	}

	
	
	
	@GetMapping("/allproduct")
	public List<Products> findallproduct() {
		return productrepo.findAll();
	}
	
	

	
	@GetMapping("/alladmin")
	public List<Admin> findalladmin() {
		return adminrepo.findAll();
	}
	
	
	@GetMapping("/allusers")
	public List<User> findallusers() {
		return userrepo.findAll();
	}
	
	
	@GetMapping("/auth/{email}/{password}")
	public ResponseEntity<Object> findbyadminpassemail(@PathVariable String email, @PathVariable String password) {
	    
	    for (Admin u : adminrepo.findAll()) {
	        
	        if (u.getPassword().equalsIgnoreCase(password) && u.getEmail().equalsIgnoreCase(email)) {
	           
	            return ResponseEntity.ok(new Object[]{u, "Success: Logged in successfully!"});
	        }
	    }
	   
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Object[]{null, "Failed: Login failed!"});
	}

	
	@GetMapping("/findproductsbycategory/{category}")
	public ResponseEntity<Object> findProductbycategory(@PathVariable String category) {
	    try {
	    	 Products p  = productrepo.findByCategory(category);
	    	 if (p != null) {
	            return ResponseEntity.ok(p);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	
	@GetMapping("/findproductbyId/{productID}")
	public ResponseEntity<Object> findProduct(@PathVariable int productID) {
	    try {
	        Products p = productrepo.findByproductID(productID);
	        if (p != null) {
	            return ResponseEntity.ok(p);  
	        } else {
	           
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	        }
	    } catch (Exception e) {
	       
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching the product.");
	    }
	}
	
	
	@DeleteMapping("/deleteproduct/{productID}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productID) {
	    try {
	        Products p = productrepo.findByproductID(productID);
	        if (p != null) {
	            productrepo.delete(p);
	            return ResponseEntity.ok("Product deleted successfully!");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the product.");
	    }
	}
	
	
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {
	    try {
	        // Fetch the user based on userId
	        User user = userrepo.findById(userId).orElse(null);
	        if (user != null) {
	            // If user exists, delete the user
	            userrepo.delete(user);
	            return ResponseEntity.ok("User deleted successfully!");
	        } else {
	            // If user doesn't exist, return a not found message
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the user.");
	    }
	}

	
	
	
	@PutMapping("/updateproduct/{productID}")
	@Transactional
	@ResponseBody
	public ResponseEntity<Object> updateProduct(@PathVariable  int productID, @RequestBody Products updatedProduct) {
	    try {
	        Products existingProduct = productrepo.findByproductID(productID);
	        if (existingProduct != null) {
	            // Update the fields
	            existingProduct.setName(updatedProduct.getName());
	            existingProduct.setDescription(updatedProduct.getDescription());
	            existingProduct.setPrice(updatedProduct.getPrice());
	            existingProduct.setCategory(updatedProduct.getCategory());
	            existingProduct.setStock(updatedProduct.getStock());
	            
	            productrepo.save(existingProduct);
	            return ResponseEntity.ok("Product updated successfully!");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the product.");
	    }
	}
	
	
	
	
	





	
	
	

}
