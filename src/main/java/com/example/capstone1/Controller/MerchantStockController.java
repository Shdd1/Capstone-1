package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Servic.MerchantService;
import com.example.capstone1.Servic.MerchantStockService;
import com.example.capstone1.Servic.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantsStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id, @Valid@RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=merchantStockService.updateMerchantsStock(id,merchantStock);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id){
        boolean isDeleted=merchantStockService.deleteMerchantsStock(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));

    }
    //***************Q11************************************************
    @PutMapping("/update/{productId}/{merchantId}/{id}/{additionalStock}")
    public ResponseEntity addStock(@PathVariable int productId,@PathVariable int merchantId,@PathVariable int id,@PathVariable int additionalStock){
        boolean isUpdated=merchantStockService.merchantAddStocks(productId,merchantId,id,additionalStock);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("IS Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //***************Q12***********************************************
    @PutMapping("/update/{userid}/{productId}/{merchantId}")
  public ResponseEntity buyProduct(@PathVariable int userid,@PathVariable int productId,@PathVariable int merchantId) {
        int buy = merchantStockService.buyProduct(userid, productId, merchantId);
        return switch (buy) {
            case 1 -> ResponseEntity.status(400).body(new ApiResponse("Bad request: the balance less than price"));
            case 2 -> ResponseEntity.status(400).body(new ApiResponse("Bad request: the merchant Id not found "));
            case 3 -> ResponseEntity.status(400).body(new ApiResponse("Bad request: product Id not found"));
            case 4 -> ResponseEntity.status(400).body(new ApiResponse("Bad request: user id not found"));
            default -> ResponseEntity.status(200).body(new ApiResponse("is updated"));
        };

    }
    //**************** Discount *************************************************
    @PutMapping("/discount/{productId}/{merchantId}/{userId}")
    public ResponseEntity discount(@PathVariable int productId,@PathVariable int merchantId,@PathVariable int userId){
        boolean discount=merchantStockService.discount(productId,merchantId,userId);
        if(discount){
            return ResponseEntity.status(200).body(new ApiResponse("Successful discount"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }


}
