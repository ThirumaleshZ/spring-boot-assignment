package com.assignment.springbootassignment.controller;

import com.assignment.springbootassignment.model.Movie;
import com.assignment.springbootassignment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api")
public class MovieController {
	@Autowired
	private MovieService movieService;

	@GetMapping ("/movies")
	public List<Movie> getMovies(Model model) {
		return movieService.findAll();
	}

	@GetMapping ("/movies/{id}")
	public Movie getMovie(@PathVariable int id) {
		Movie movie = movieService.findById(id);
		if (movie == null) {
			throw new RuntimeException("Movie ID not found - " + id);
		}
		return movie;
	}

	@PostMapping ("/movies")
	public Movie saveMovie(@RequestBody Movie movie) {
		movie.setId(0);
		movieService.save(movie);
		return movie;
	}

	@PutMapping("/movies")
	public Movie updateMovie(@RequestBody Movie movie)	{
		movieService.save(movie);
		return movie;
	}

	@DeleteMapping ("/movies/{id}")
	public String deleteMovie(@PathVariable int id) {
		Movie movie = movieService.findById(id);
		if (movie == null) {
			throw new RuntimeException("Movie ID not found - " + id);
		}
		movieService.deleteById(id);
		return "Deleted Movie ID - " + id;
	}
}
