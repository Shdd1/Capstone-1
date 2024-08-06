package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotNull(message = "id must be not null")
    private int id;

    @NotNull(message = "product id must be not null")
    private int product_id ;

    @NotNull(message = "merchant id must be not null")
    private int merchant_id;

    @NotNull(message = "stock must be not null")
    @Min(value = 11,message = "stock  have to be more than 10 at start) ")
    private int stock;

}
