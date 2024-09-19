package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Admin;


@Repository
public interface AdminRepository extends MongoRepository<Admin, Integer> {
	
	
	

}
