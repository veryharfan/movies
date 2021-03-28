package com.movies.backend.controller;

import com.movies.backend.entity.Movie;
import com.movies.backend.model.PurchaseModel;
import com.movies.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/discovery/{year}")
    public ResponseEntity<List<Movie>> discovery(@PathVariable int year) {
        List<Movie> movies = movieService.discovery(year);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/research")
    public ResponseEntity<List<Movie>> research(@RequestBody List<String> id) {
        List<Movie> movies = movieService.research(id);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/booking")
    public RedirectView booking(@RequestBody PurchaseModel purchase) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://www.google.com");
        movieService.booking(purchase.getMovieId(), purchase.getEmail(), purchase.getPrice());
        return redirectView;
    }
}
