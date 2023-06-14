package com.assignment.springbootassignment.controller;

import com.assignment.springbootassignment.exception.MovieNotFoundException;
import com.assignment.springbootassignment.model.Movie;
import com.assignment.springbootassignment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getMovies(Model model) {
        return movieService.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable int id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            throw new MovieNotFoundException("Movie ID Not Found - " + id);
        }
        return movie;
    }

    @PostMapping("/movies")
    public Movie saveMovie(@RequestBody Movie movie) {
        movie.setId(0);
        movieService.save(movie);
        return movie;
    }

    @PutMapping("/movies")
    public Movie updateMovie(@RequestBody Movie movie) {
        movieService.save(movie);
        return movie;
    }

    @DeleteMapping("/movies/{id}")
    public String deleteMovie(@PathVariable int id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            throw new MovieNotFoundException("Movie ID Not Found - " + id);
        }
        movieService.deleteById(id);
        return "Deleted Movie ID - " + id;
    }
}

//    mvnw verify sonar:sonar -Dsonar.host.url=https://sonarcloud.io  -Dsonar.organization=thirumaleshzemoso -Dsonar.login=6e15c1130f6a8d98e7201fd49af032031608d7c3 -Dsonar.projectKey=ThirumaleshZ_spring-boot-assignment