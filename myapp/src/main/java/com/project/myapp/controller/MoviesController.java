package com.project.myapp.controller;

import java.util.LinkedHashMap;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.model.Movies;
import com.project.myapp.repositories.MoviesRepository;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	private MoviesRepository repo;	
	
	// Get all the documents from the movie collection
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public LinkedHashMap<String, Object> getAllMovies() {	
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		
		map.put("code", 200);
		map.put("success", "true");
		map.put("total_records", repo.findAll().size());
		map.put("records", repo.findAll());
		
		return map;			
		
	}
	
	// Get a movie document using id	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public LinkedHashMap<String, Object> getMovieById(@PathVariable("id") ObjectId id) {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		Movies movie = repo.findBy_id(id);
		
		// Validation error		
		if (movie == null) {
			
			map.put("code", 400);
			map.put("success", "false");
			map.put("message", "Invalid Id");
					
			return map;
			
		}
		
		map.put("code", 200);
		map.put("success", "true");
		map.put("record", movie);
		
		return map;
		
	}
	
	// Create a document for movie collections
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public LinkedHashMap<String, Object> createMovies(@Valid @RequestBody Movies movies) {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();	
		
		movies.set_id(ObjectId.get());
		repo.save(movies);			
		
		map.put("code", 200);
		map.put("success", "true");
		map.put("record", movies);
		
		return map;
		
	}

	// Update any movie record using id
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public LinkedHashMap<String, Object> modifyMovieById (@PathVariable("id") ObjectId id, @Valid @RequestBody Movies movie) {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		
		Movies findMovie = repo.findBy_id(id);

		// Check whether the id is valid or not
		if (findMovie == null) {			

			map.put("code", 400);
			map.put("success", "false");
			map.put("message", "Invalid Id");
					
			return map;		
			
		}
		
		movie.set_id(id);
		repo.save(movie);

		map.put("code", 200);
		map.put("success", "true");
		map.put("message", "Updated successfully");
		map.put("record", movie);
				
		return map;		
			
	}
	
	// Delete a movie by id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public LinkedHashMap<String, Object> deleteMovieById(@PathVariable("id") ObjectId id) {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		
		Movies findMovie = repo.findBy_id(id);

		// Check whether the id is valid or not
		if (findMovie == null) {			

			map.put("code", 400);
			map.put("success", "false");
			map.put("message", "Invalid Id");
					
			return map;		
			
		}

		repo.delete(repo.findBy_id(id));
		
		map.put("code", 200);
		map.put("success", "true");
		map.put("message", "Deleted successfully");
		map.put("Id", id);
		return map;						
		
	}
	
	// Delete all movies documents from the database	
	@RequestMapping(value = "/all", method = RequestMethod.DELETE)
	public LinkedHashMap<String, Object> deleteAllMovies() {

		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		
		int size = repo.findAll().size();
		
		if (size == 0) {
			
			map.put("code", 400);
			map.put("success", "false");
			map.put("total_records", size);
			map.put("message", "No records");		
			return map;							
			
		}
		
		repo.deleteAll();
		
		map.put("code", 200);
		map.put("success", "true");
		map.put("total_records", size);
		map.put("message", "Deleted all records successfully");		
		return map;										
		
	}
	
}
