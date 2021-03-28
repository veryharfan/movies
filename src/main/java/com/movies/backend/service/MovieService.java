package com.movies.backend.service;

import com.movies.backend.entity.Movie;
import com.movies.backend.entity.Purchase;
import com.movies.backend.entity.User;
import com.movies.backend.repository.MovieRepository;
import com.movies.backend.repository.PurchaseRepository;
import com.movies.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    public List<Movie> discovery(int year) {
        List<Movie> movies = movieRepository.findCustomDiscovery(year);
        return movies;
    }

    public List<Movie> research(List<String> id) {
        List<Movie> movies = movieRepository.findCustomResearch(id.get(0), id.get(1));
        return movies;
    }

    @Transactional
    public void booking(String movieId, String email, int price) {
        User user = userRepository.findByEmail(email);
        Movie movie = movieRepository.findById(movieId).get();
        Purchase purchase = Purchase.builder()
                .user(user)
                .movie(movie)
                .purchaseDate(new Date())
                .price(price)
                .build();
        purchaseRepository.save(purchase);
    }
}
