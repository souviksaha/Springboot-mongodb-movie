package com.project.myapp.controller;

import java.util.List;

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
	public List<Movies> getAllMovies() {	
		
		return repo.findAll();		
		
	}
	
	// Get a movie document using id	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Movies getMovieById(@PathVariable("id") ObjectId id) {
		
		return repo.findBy_id(id);
		
	}
	
	// Create a document for movie collections
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Movies createMovies(@Valid @RequestBody Movies movies) {
		
		movies.set_id(ObjectId.get());
		repo.save(movies);
		return movies;
		
	}

	// Update any movie record using id
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyMovieById (@PathVariable("id") ObjectId id, @Valid @RequestBody Movies movie) {
		
		movie.set_id(id);
		repo.save(movie);
		
	}
	
	// Delete a movie by id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMovieById(@PathVariable("id") ObjectId id) {
		
		repo.delete(repo.findBy_id(id));
		
	}
	
	// Delete all movies from the database	
	@RequestMapping(value = "/all", method = RequestMethod.DELETE)
	public void deleteAllMovies() {
		
		repo.deleteAll();
		
	}
	
}