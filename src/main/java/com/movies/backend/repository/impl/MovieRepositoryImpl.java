package com.movies.backend.repository.impl;

import com.mongodb.DBObject;
import com.movies.backend.entity.Movie;
import com.movies.backend.repository.MovieRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class MovieRepositoryImpl implements MovieRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Movie> findCustomDiscovery(int year) {
        Query query = new Query(Criteria.where("releaseYear").is(year));
        query.with(Sort.by(Sort.Direction.DESC, "likes")).limit(10);
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    @Override
    public List<Movie> findCustomResearch(String id1, String id2) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(new ObjectId[]{ new ObjectId(id1), new ObjectId(id2)}));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }
}
