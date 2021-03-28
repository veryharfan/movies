package com.movies.backend.repository;

import com.movies.backend.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>, MovieRepositoryCustom {

}
