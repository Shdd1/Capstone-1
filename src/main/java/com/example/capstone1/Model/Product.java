package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotNull(message = "ID must not be null")
    private int id;

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 4,message = "Name have to be more than 3 length long")
    private String name;

    @NotNull(message = "price can not be null")
    @Positive(message = "price must be positive number")
    private int price;

    @NotNull(message = "category ID can not be null")
    private int categoryID;

    @NotNull(message = "Product rating must not be null")
    @Min(value = 0, message = "Product rating must be at least 0")
    @Max(value = 5, message = "Product rating must be at most 5")
    private Double rating;
    @NotNull(message = "Product sales must not be null")
    @Min(value = 0, message = "Product sales must be a positive number or zero")
    private Integer sales;
    @NotNull(message = "Product views must not be null")
    @Min(value = 0, message = "Product views must be a positive number or zero")
    private Integer views;

}
