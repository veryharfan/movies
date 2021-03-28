package com.movies.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.backend.entity.Movie;
import com.movies.backend.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieService movieService;

    private List<Movie> movieDiscovery = new ArrayList<>();
    private List<Movie> movieResearch = new ArrayList<>();

    @BeforeEach
    void setUp() {
        movieDiscovery.add(new Movie("Movie Abc3", 3000, 2021));
        movieDiscovery.add(new Movie("Movie Abc9", 1300, 2021));
        movieDiscovery.add(new Movie("Movie Abc8", 1500, 2021));
        movieDiscovery.add(new Movie("Movie Abc10", 1200, 2021));
        movieDiscovery.add(new Movie("Movie Abc7", 1100, 2021));
        movieDiscovery.add(new Movie("Movie Abc1", 1000, 2021));
        movieDiscovery.add(new Movie("Movie Abc2", 900, 2021));
        movieDiscovery.add(new Movie("Movie Abc6", 800, 2021));
        movieDiscovery.add(new Movie("Movie Abc5", 700, 2021));
        movieDiscovery.add(new Movie("Movie Abc4", 500, 2021));

        Movie movie1 = new Movie("Movie Abc123", 100, 2019);
        movie1.setId("abc123");
        Movie movie2 = new Movie("Movie Def456", 150, 2018);
        movie2.setId("def456");
        movieResearch.add(movie1);
        movieResearch.add(movie2);
    }

    @Test
    void discoveryValidInput_thenReturn200() throws Exception {
        when(movieService.discovery(2021)).thenReturn(movieDiscovery);
        String expectedRespondBody = objectMapper.writeValueAsString(movieDiscovery);
        mockMvc.perform(get("/api/discovery/2021"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedRespondBody)));
        verify(movieService).discovery(2021);
    }

    @Test
    void research() throws Exception {
        when(movieService.research(Arrays.asList("abc123", "def456"))).thenReturn(movieResearch);
        String expectedRespondBody = objectMapper.writeValueAsString(movieResearch);
        mockMvc.perform(get("/api/research")
                .contentType("application/json")
                .content("[\"abc123\", \"def456\"]"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedRespondBody)));
        verify(movieService).research(Arrays.asList("abc123", "def456"));
    }

    @Test
    void booking() throws Exception {
        mockMvc.perform(post("/api/booking")
                .content("{\"movieId\": \"abc123\", \"email\": \"very@gmail.com\", \"price\":20000}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("http://www.google.com"))
                .andExpect(status().is3xxRedirection());;
        verify(movieService).booking("abc123","very@gmail.com", 20000);
    }
}