package com.project.myapp.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.myapp.model.Movies;

public interface MoviesRepository extends MongoRepository<Movies, String>{

	Movies findBy_id(ObjectId _id);
	
}
