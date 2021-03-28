package com.movies.backend.model;

import lombok.Data;

@Data
public class PurchaseModel {
    private String movieId;
    private String email;
    private int price;
}
