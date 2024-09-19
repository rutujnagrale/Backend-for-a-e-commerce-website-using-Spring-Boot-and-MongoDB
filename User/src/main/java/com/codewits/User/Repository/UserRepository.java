package com.codewits.User.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.codewits.User.pojos.User;

public interface UserRepository extends MongoRepository<User, Integer>{
	List<User>findByemail(String email);
	List<User>findBypassword(String password);
	User findByuserid(int userid);
	

}
