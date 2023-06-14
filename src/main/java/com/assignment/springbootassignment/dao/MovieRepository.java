package com.assignment.springbootassignment.dao;

import com.assignment.springbootassignment.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
