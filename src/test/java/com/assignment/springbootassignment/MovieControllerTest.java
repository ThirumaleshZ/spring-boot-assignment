package com.assignment.springbootassignment;

import com.assignment.springbootassignment.controller.MovieController;
import com.assignment.springbootassignment.model.Movie;
import com.assignment.springbootassignment.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MovieControllerTest {

	@Mock
	private MovieService movieService;

	@InjectMocks
	private MovieController movieController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
	}

	@Test
	public void testList() throws Exception {
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie());
		movies.add(new Movie());

		when(movieService.findAll()).thenReturn(movies);

		mockMvc.perform(MockMvcRequestBuilders.get("/movies/list")).andExpect(status().isOk()).andExpect(view().name("movies" + "-list")).andExpect(model().attribute("movies", hasSize(2)));
	}
}
