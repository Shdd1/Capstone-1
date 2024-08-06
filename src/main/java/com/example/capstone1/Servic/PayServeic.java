package com.example.capstone1.Servic;

import com.example.capstone1.Model.OrderTracking;
import com.example.capstone1.Model.Pay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PayServeic {
    private final UserService userService;
    private final ProductService productService;
    ArrayList<Pay>pays=new ArrayList<>();

    public ArrayList<Pay> getPay(){
        return pays;
    }

        public void addPay(Pay pay){
        pays.add(pay);
    }

    public boolean updatePay(int id,Pay pay){
        for(int i=0;i<pays.size();i++){
            if(pays.get(i).getId()==id){
                pays.set(i,pay);
                return true;
            }
        }
        return false;
    }

    public boolean deletePay(int id){
        for(int i=0;i<pays.size();i++) {
                if (pays.get(i).getId() == id) {
                pays.remove(i);
                return true;
            }
        }
        return false;
    }
     //***************Extra endpoint payment methods************************
        public int methodPaying(int productId,int userId,String paymentMethod) {
        for (int i = 0; i < productService.products.size(); i++){
            if (productService.products.get(i).getId() == productId) {
                for (int j = 0; j < userService.users.size(); j++) {
                    if (userService.users.get(j).getRole().equalsIgnoreCase("Customer") && userService.users.get(j).getId() == userId) {
                        if (paymentMethod.equalsIgnoreCase("debit_card")&& userService.users.get(j).getBalance() >= productService.products.get(i).getPrice()) {
                                userService.users.get(j).setBalance(userService.users.get(j).getBalance() - productService.products.get(i).getPrice());

                        }else if (paymentMethod.equalsIgnoreCase("Tabby")) {
                                int t = productService.products.get(i).getPrice() / 4;
                                userService.users.get(j).setBalance(userService.users.get(j).getBalance() - t);
                        }else if (paymentMethod.equalsIgnoreCase("Receiving")) {
                            userService.users.get(j).getBalance();
                           }
                    }else {return 1;}

                }

            } else {return 2;}

        }
        return 3;
    }
}
