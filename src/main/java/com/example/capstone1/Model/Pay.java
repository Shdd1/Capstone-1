package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class Pay {

    @NotNull(message = "Payment ID must not be null")
    private int id;


    @NotNull(message = "product ID must not be null")
    private int productId;

    @NotNull(message = "User ID must not be null")
    private Integer userId;

    @NotNull(message = "Amount must not be null")
    @Min(value = 0, message = "Amount must be a positive number")
    private Double amount;

    @NotEmpty(message = "Payment method must not be empty")
    @Pattern(regexp = "^(debit_card|Tabby|Recieving)$", message = "Payment method must be either 'debit_card', 'Tabby', or 'Tamara'")
    private String paymentMethod;

    @NotNull(message = "Payment date must not be null")
    private LocalDate paymentDate;


}
