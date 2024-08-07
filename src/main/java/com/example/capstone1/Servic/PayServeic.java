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

}
