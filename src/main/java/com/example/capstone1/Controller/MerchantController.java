package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Servic.MerchantService;
import com.example.capstone1.Servic.MerchantStockService;
import com.example.capstone1.Servic.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

 private final MerchantService merchantService;
 private final ProductService productService;
private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity getMerchant(){
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid@RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchants(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable int id, @Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=merchantService.updateMerchants(id,merchant);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){
        boolean isDeleted=merchantService.deleteMerchants(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }

}
