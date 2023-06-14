package com.assignment.springbootassignment.service;

import com.assignment.springbootassignment.dao.MovieRepository;
import com.assignment.springbootassignment.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int id) {
        Optional<Movie> res = movieRepository.findById(id);
        Movie movie = null;

        if (res.isPresent()) {
            movie = res.get();
        } else {
            throw new RuntimeException("Movie ID Not Found - " + id);
        }
        return movie;
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(int id) {
        movieRepository.deleteById(id);
    }
}
