package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class User {
    @NotNull(message = "ID must not be null")
    private int id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 6, message = "Username must be more than 5 characters long")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 7, message = "Password must be more than 6 characters long")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Password must have characters and digits")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
    private String role;

    @NotNull(message = "Balance must not be null")
    @Positive(message = "Balance must be positive")
    private double balance;

    @NotNull(message = "can not be null")
    private Boolean isPrime;



}
