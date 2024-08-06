package com.example.capstone1.Servic;

import com.example.capstone1.Model.OrderTracking;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class OrderTrackingService {
    private final UserService userService;

    ArrayList<OrderTracking>orderTrackings=new ArrayList<>();

    public ArrayList<OrderTracking> getOrderTrackings(){
        return orderTrackings;
    }

    public void addOrderTracking(OrderTracking orderTracking){
        orderTrackings.add(orderTracking);
    }

    public boolean updateOrderTracking(int id,OrderTracking orderTracking){
        for(int i=0;i<orderTrackings.size();i++){
            if(orderTrackings.get(i).getId()==id){
                orderTrackings.set(i,orderTracking);
                return true;
            }
        }
        return false;
    }

    public boolean deleteOrderTracking(int id){
        for(int i=0;i<orderTrackings.size();i++) {
            if (orderTrackings.get(i).getId() == id) {
                orderTrackings.remove(i);
                return true;
            }
        }
        return false;
    }

    //************* Search to track shipment by id ***************
    public OrderTracking searchOrderTracking(int id){
        for(int i=0;i<orderTrackings.size();i++) {
            if(orderTrackings.get(i).getId()==id){
               return orderTrackings.get(i);

            }
        }
        return null;

    }

    //*** Update order status by Admin ***
    public boolean updateOrderStatus(int adminId,int orderTrackId){
        for (int i=0;i<userService.users.size();i++){
            if(userService.users.get(i).getId()==adminId){
              if (userService.users.get(i).getRole().equalsIgnoreCase("admin")) {
                  for (int o = 0; o < orderTrackings.size(); o++) {
                      if (orderTrackings.get(o).getId() == orderTrackId) {
                          if (orderTrackings.get(o).getOrderStatus().equalsIgnoreCase("pending")) {
                              orderTrackings.get(o).setOrderStatus("processing");
                          } else
                              orderTrackings.get(o).setOrderStatus("shipped");
                          return true;
                      }
                  }

              }
            }

        }
        return false;
    }


      //***** User checks the status of his shipment ********
    public String checkStatus(int trackId , int userId){
        for(int i=0;i<orderTrackings.size();i++){
            if(orderTrackings.get(i).getId()==trackId && orderTrackings.get(i).getUserId()==userId){
                return orderTrackings.get(i).getOrderStatus();

            }
        }
        return null;
    }

}
