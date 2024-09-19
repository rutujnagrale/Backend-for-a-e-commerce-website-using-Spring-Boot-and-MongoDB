package com.codewits.User.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.codewits.User.pojos.Booking;

public interface BookingRepository extends MongoRepository<Booking,String>{
	

}
