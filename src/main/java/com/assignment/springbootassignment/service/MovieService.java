package com.assignment.springbootassignment.service;

import com.assignment.springbootassignment.model.Movie;

import java.util.List;

public interface MovieService {
	public List<Movie> findAll();

	public Movie findById(int id);

	public void save(Movie movie);

	public void deleteById(int id);
}
