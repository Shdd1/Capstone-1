package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Servic.ProductService;
import com.example.capstone1.Servic.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getProduct(){
        return ResponseEntity.status(200).body(productService.getProduct());
    }
    @PostMapping("/add")
        public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("Is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id, @Valid@RequestBody Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=productService.updateProduct(id,product);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        boolean isDeleted=productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    @GetMapping("/get/{main}/{max}")
    public ResponseEntity getsales(@PathVariable int main, @PathVariable int max){
        return ResponseEntity.status(200).body(productService.rangeSales(main,max));
    }

}
