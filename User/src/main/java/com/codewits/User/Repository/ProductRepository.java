package com.codewits.User.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codewits.User.pojos.Products;

@Repository
public interface ProductRepository extends MongoRepository<Products,Integer> {
	public Products findByproductID(int productID);
	public Products findByCategory(String category);
	public Products findByname(String name);

}
