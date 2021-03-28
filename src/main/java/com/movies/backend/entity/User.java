package com.movies.backend.entity;

import com.movies.backend.entity.Movie;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String email;
    private String phone;
    private List<Movie> purchasedMovie;

}
