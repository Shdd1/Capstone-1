package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotNull(message = "id can not be null")
    private int id;
    @NotEmpty(message = "Name can not be empty")
    @Size(min =4,message = "name have to be more than 3 length long")
    private String name;

}
