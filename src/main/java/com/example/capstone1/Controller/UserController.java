package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.User;
import com.example.capstone1.Servic.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
       return ResponseEntity.status(200).body(userService.getUser());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id,@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=userService.updateUser(id,user);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("is Update"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){

        boolean isDeleted=userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }

    @GetMapping("/prime/{userId}")
    public ResponseEntity primeUser(@PathVariable int userId){
        boolean isPrime=userService.registerUserToPrime(userId);
        if(isPrime){
            return ResponseEntity.status(200).body(new ApiResponse("User successfully registered to Prime."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user not found"));
    }
}
