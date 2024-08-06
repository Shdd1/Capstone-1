package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Pay;
import com.example.capstone1.Model.User;
import com.example.capstone1.Servic.PayServeic;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayServeic payServeic;

    @GetMapping("/get")
    public ResponseEntity getPay(){
        return ResponseEntity.status(200).body(payServeic.getPay());
    }
    @PostMapping("/add")
    public ResponseEntity addPay(@Valid @RequestBody Pay pay, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        payServeic.addPay(pay);
        return ResponseEntity.status(200).body(new ApiResponse("is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePay(@PathVariable int id,@Valid @RequestBody Pay pay, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=payServeic.updatePay(id,pay);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("is Update"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePay(@PathVariable int id){

        boolean isDeleted=payServeic.deletePay(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //***************Extra endpoint payment methods************************
    @PutMapping("/update/{productId}/{userId}/{paymentMethod}")
    public ResponseEntity PaymentMethod(@PathVariable int productId,@PathVariable int userId ,@PathVariable String paymentMethod){
        int isPay=payServeic.methodPaying(productId,userId,paymentMethod);
        return switch (isPay){
            case 1->
                    ResponseEntity.status(400).body(new ApiResponse("User role not customer or user id not found"));
            case 2->
                    ResponseEntity.status(400).body(new ApiResponse("Product not found"));

            default -> ResponseEntity.status(200).body(new ApiResponse("Success paying"));
        };


    }
}
