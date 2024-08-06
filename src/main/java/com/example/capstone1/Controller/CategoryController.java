package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Servic.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    public final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("it is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategroy(@PathVariable int id,@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=categoryService.updateCategory(id,category);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Is Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategroy(@PathVariable int id){
        boolean isDeleted=categoryService.deleteCategory(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }

}
