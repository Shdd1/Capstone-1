package com.example.capstone1.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderTracking {

    @NotNull(message = "id can not be null")
    private int id;

    @NotNull(message = "id can not be null")
    private int userId;

    @NotNull(message = "id can not be null")
    private int product_id;

    @NotNull(message = "merchant id must be not null")
    private int merchant_id;

    @NotEmpty(message = "Order Status can not be empty")
    @Pattern(regexp = "^(pending|processing|shipped)$", message = "Order Status must be either 'pending' or 'shipped'")
    private String OrderStatus;

    private String shipmentLocation;

    @FutureOrPresent(message = "should be a date in the Future Or Present ")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date delivery_date;


}
