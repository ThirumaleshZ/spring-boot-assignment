package com.assignment.springbootassignment;

import com.assignment.springbootassignment.dao.MovieRepository;
import com.assignment.springbootassignment.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MovieServiceTest {


	@Mock
	private MovieRepository movieRepository;

	@Mock
	private MovieService movieService;


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

//	@Test
//	public void testGetMovieById() {
//		Movie movie = new Movie();
//		movie.setId(1);
//		movie.setName("her");
//
//		when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
//
//		Movie res = movieService.findById(1);
//		assertEquals(movie, res);
//		verify(movieRepository, times(1)).findById(1);
//	}
}
