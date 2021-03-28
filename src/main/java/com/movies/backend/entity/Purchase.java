package com.movies.backend.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@Document(collection = "purchase")
public class Purchase implements Serializable {
    @Id
    private String id;
    private User user;
    private Movie movie;
    private Date purchaseDate;
    private int price;
}
