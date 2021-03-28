package com.movies.backend.repository;

import com.movies.backend.entity.Movie;

import java.util.List;

public interface MovieRepositoryCustom {

    List<Movie> findCustomDiscovery(int year);
    List<Movie> findCustomResearch(String id1, String id2);
}
