package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.OrderTracking;
import com.example.capstone1.Servic.OrderTrackingService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/track")
@RequiredArgsConstructor
public class OrderTrackingController {
    private final OrderTrackingService orderTrackingService;
    @GetMapping("/get")
    public ResponseEntity getOrderTracking(){
        return ResponseEntity.status(200).body(orderTrackingService.getOrderTrackings());
    }

    @PostMapping("/add")
    public ResponseEntity addOrderTracking(@Valid@RequestBody OrderTracking orderTracking, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        orderTrackingService.addOrderTracking(orderTracking);
        return ResponseEntity.status(200).body(new ApiResponse("Is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrderTracking(@PathVariable int id,@Valid@RequestBody OrderTracking orderTracking,Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean IsUpdate=orderTrackingService.updateOrderTracking(id,orderTracking);
        if(IsUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }

    @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteOrderTracking(@PathVariable int id){
        boolean IsDelete=orderTrackingService.deleteOrderTracking(id);
        if(IsDelete){
            return ResponseEntity.status(200).body(new ApiResponse("is Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }
    //***************************Extra End Point***********************************
    //Search to track shipment by id
    @GetMapping("/search/{id}")
    public ResponseEntity SearchTrackShipment(@PathVariable int id){
        OrderTracking orderTracking=orderTrackingService.searchOrderTracking(id);
        if(orderTracking==null){
            return ResponseEntity.status(400).body(new ApiResponse("not found"));

        }
        return ResponseEntity.status(200).body(orderTrackingService.searchOrderTracking(id));
    }

    //**** Update order status by Admin **********
    @PutMapping("/updatestat/{adminId}/{orderId}")
    public ResponseEntity updateOrderStatus(@PathVariable int adminId,@PathVariable int orderId){
        boolean IsUpdate=orderTrackingService.updateOrderStatus(adminId,orderId);
        if(IsUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));


    }

     //*****User checks the status of his shipment********
    @GetMapping("/get/{trackID}/{userId}")
    public ResponseEntity chaeckStatus(@PathVariable int trackID,@PathVariable int userId){
        String status=orderTrackingService.checkStatus(trackID,userId);
        if(status==null){
            return ResponseEntity.status(400).body(new ApiResponse("Not found"));
        }
        return ResponseEntity.status(200).body(orderTrackingService.checkStatus(trackID,userId));
    }

}
