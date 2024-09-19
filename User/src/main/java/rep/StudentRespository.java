package rep;

import org.springframework.data.mongodb.repository.MongoRepository;

import models.person;

public interface StudentRespository extends MongoRepository<person,Integer>{

}
