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
    private double price;

    @NotNull(message = "category ID can not be null")
    private int categoryID;


    @NotNull(message = "Product sales must not be null")
    @Min(value = 0, message = "Product sales must be a positive number or zero")
    private Integer sales= 0;

    @NotEmpty(message = "Product Status can not be empty")
    @Pattern(regexp = "^(New|Available|Unavailable)$")
    private String productStatus;

}
