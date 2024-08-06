package com.example.capstone1.Servic;

import com.example.capstone1.Model.OrderTracking;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    ArrayList<User>users=new ArrayList<>();

    public ArrayList<User> getUser(){
        return users ;
    }

    public void addUser(User user){
        users.add(user);
    }
    public boolean updateUser(int id,User user){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId()==id){
                users.set(i,user);
                return true;
            }

        }
        return false;
    }

    public boolean deleteUser(int id){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId()==id){
                users.remove(i);
                return true;
            }
        }
        return false;
    }
//
////12- Create endpoint where user can buy a product directly
//    public boolean bayProduct(int userid,int productId,int merchantId) {
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).getId() == userid && users.get(i).getRole().equalsIgnoreCase("customer")) {
//
//                for (int j = 0; j < productService.products.size(); j++) {
//                    if (productService.products.get(j).getId() == productId) {
//
//                        for (int h = 0; h < merchantStockService.merchantStocks.size(); h++) {
//                            if (merchantStockService.merchantStocks.get(h).getMerchant_id() == merchantId && merchantStockService.merchantStocks.get(h).getStock()!=0) {
//
//                            merchantStockService.getMerchantsStock().get(h).setStock(merchantStockService.merchantStocks.get(h).getStock() - 1);
//                            if (users.get(i).getBalance() >productService.products.get(j).getPrice()) {
//                                users.get(h).setBalance(users.get(h).getBalance() - (productService.products.get(h).getPrice()));
//                                return true;
//                            }
//
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
//        return false;
//
//    }

//   // *****User checks the status of his shipment********
//    public String checkStatus(int trackId , int userId){
//        for(int i=0;i<orderTrackingService.orderTrackings.size();i++){
//            if(orderTrackingService.orderTrackings.get(i).getId()==trackId && orderTrackingService.orderTrackings.get(i).getUserId()==userId){
//               return orderTrackingService.orderTrackings.get(i).getOrderStatus();
//
//            }
//        }
//        return null;
//    }


}


