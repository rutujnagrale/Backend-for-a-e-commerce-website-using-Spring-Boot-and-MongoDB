package com.codewits.User.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codewits.User.Repository.BookingRepository;
import com.codewits.User.Repository.ProductRepository;
import com.codewits.User.Repository.UserRepository;
import com.codewits.User.pojos.Booking;
import com.codewits.User.pojos.Products;
import com.codewits.User.pojos.User;







@RestController
public class UserController {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
    private	ProductRepository productrepo;
	
	@Autowired
	private BookingRepository bookingrepo;
	

	
	@PostMapping("/adduser")
	public User addusers(@RequestBody User user) {
		userrepo.save(user);
		return user;
	}
	
	
	
	
	@GetMapping("/auth/{email}/{password}")
	public ResponseEntity<Object> findbyuserpassemail(@PathVariable String email, @PathVariable String password) {
	    for (User u : userrepo.findAll()) {
	        if (u.getPassword().equalsIgnoreCase(password) && u.getEmail().equalsIgnoreCase(email)) {
	            return ResponseEntity.ok(new Object[]{u, "Success: Logged in successfully!"});
	        }
	    }
	    
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Object[]{null, "Failed: Login failed!"});
	}
	

	@GetMapping("/allproduct")
	public List<Products> findallproduct() {
		return productrepo.findAll();
	}
	
	
	@GetMapping("/findproductsbycategory/{category}")
	public ResponseEntity<Object> findProductbycategory(@PathVariable String category) {
	    try {
	    	 Products p  = productrepo.findByCategory(category);
	    	 if (p != null) {
	            return ResponseEntity.ok(p);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object[] {null,"The Category is not present"});
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
	
	
	@PostMapping("/addtocart")
	public String addtocart(@RequestBody Booking bk) {
		User ur=bk.getUser();
		Products p=bk.getProduct();
		
		
		
		
		Products pr=productrepo.findByname(p.getName());
		if(pr==null) {
			return "Not Found";
		}
		if(pr.getStock()<bk.getQuantity()) {
			return "Out Of Stock";
		}
		else{
			bk.setTotalcost(bk.getQuantity()*pr.getPrice());
			pr.setStock(pr.getStock()-bk.getQuantity());
			productrepo.save(pr);
            bookingrepo.save(bk);
			return "Added to Cart";
		}
		
		
		
		
		
		
	}
	
	@GetMapping("/showcart/{userid}")
	
	public List<Booking> showcart(@PathVariable int userid) {
		List<Booking>mylist=bookingrepo.findAll()	;
		List<Booking>cart=new ArrayList<Booking>();
		for(Booking b:mylist) {
			if(b.getUser().getUserid()==userid) {
				cart.add(b);
			}
		}
		return cart;
	}
	
	
	

	

}
