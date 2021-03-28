package com.movies.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Document(collection = "movie")
public class Movie implements Serializable {
    @Id
    private String id;
    @Indexed @NonNull
    private String title;
    private String overview;
    private List<String> genres;
    @NonNull
    private int likes;
    @NonNull
    private int releaseYear;
}
