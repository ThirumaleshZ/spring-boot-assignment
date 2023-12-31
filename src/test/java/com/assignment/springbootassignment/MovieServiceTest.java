package com.assignment.springbootassignment;

import com.assignment.springbootassignment.dao.MovieRepository;
import com.assignment.springbootassignment.exception.MovieNotFoundException;
import com.assignment.springbootassignment.model.Movie;
import com.assignment.springbootassignment.service.MovieService;
import com.assignment.springbootassignment.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService = new MovieServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindMovieById() throws Exception {
        Movie movie = new Movie(1, "new", 2023, 2);

        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        Movie res = movieService.findById(1);
        assertEquals(movie, res);
        verify(movieRepository, times(1)).findById(1);
    }

    @Test
    void testFindMovieById_ThrowsException() throws Exception {
        Exception e = assertThrows(MovieNotFoundException.class, () -> {
            when(movieService.findById(0)).thenThrow(new MovieNotFoundException("Movie ID Not Found - 0"));
        });

        String expectedMessage = "Movie ID Not Found - 0";
        String actualMessage = e.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testFindAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        movies.add(new Movie());

        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> res = movieService.findAll();

        assertEquals(movies, res);
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void testSaveMovie() {
        Movie movie = new Movie(1, "new", 2023, 2);

        when(movieRepository.save(movie)).thenReturn(movie);
        Movie res = movieService.save(movie);

        assertEquals(movie, res);
        verify(movieRepository, atLeastOnce()).save(movie);
    }

    @Test
    void testDeleteMovie() {
        Movie movie = new Movie(1, "new", 2023, 2);

        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        movieService.deleteById(1);
        verify(movieRepository, times(1)).deleteById(1);
    }
}
