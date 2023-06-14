package com.assignment.springbootassignment;

import com.assignment.springbootassignment.controller.MovieController;
import com.assignment.springbootassignment.model.Movie;
import com.assignment.springbootassignment.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(value = MovieController.class)
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void testFindAllMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        movies.add(new Movie());

        when(movieService.findAll()).thenReturn(movies);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/movies");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(movies);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(outputJson, expectedJson);
    }

    @Test
    void testUpdateMovie() throws Exception {
        Movie movie = new Movie(1, "new movie", 2023, 1);

        String inputJson = this.mapToJson(movie);
        String URI = "/api/movies";

        when(movieService.save(any(Movie.class))).thenReturn(movie);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        String outputJson = response.getContentAsString();
//        assertThat(outputJson, is(equalTo(inputjson)));
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void testFindMovieById() throws Exception {
        Movie mock = new Movie(1, "new", 2023, 2);
        when(movieService.findById(1)).thenReturn(mock);

        String uri = "/api/movies/1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(mock);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson, is(equalTo(expectedJson)));
    }

//    @Test
//    void testFindMovieById_ThrowsException() throws Exception {
//        Exception e = assertThrows(MovieNotFoundException.class, () -> {
//            when(movieService.findById(0)).thenThrow(new MovieNotFoundException("Movie ID Not Found - 0"));
//
//            String uri = "/api/movies/0";
//
////            throw new MovieNotFoundException("Movie ID not found - 0");
////
//            RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        });
//
//        String expectedMessage = "Movie ID Not Found - 0";
//        String actualMessage = e.getMessage();
//
//        assertEquals(expectedMessage, actualMessage);
//    }

    @Test
    void testPostMovie() throws Exception {
        Movie movie = new Movie(1, "new movie", 2023, 1);

        String inputJson = this.mapToJson(movie);
        String URI = "/api/movies";

        when(movieService.save(any(Movie.class))).thenReturn(movie);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        String outputJson = response.getContentAsString();
//        assertThat(outputJson, is(equalTo(inputjson)));
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    void testDeleteMovie() throws Exception {
        int id = 1;
        Movie movie = new Movie("new", 2023, 2);
        movie.setId(1);
        when(movieService.findById(id)).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/{id}", id)).andExpect(MockMvcResultMatchers.status().isOk());
        verify(movieService, times(1)).findById(id);
    }


    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
