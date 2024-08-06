package com.example.capstone1.Servic;

import com.example.capstone1.Model.Merchant;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
   private final ProductService productService;
   private final MerchantStockService merchantStockService;
   ArrayList<Merchant>merchants=new ArrayList<>();

   public MerchantService(ProductService productService, MerchantStockService merchantStockService) {
      this.productService = productService;
      this.merchantStockService = merchantStockService;
   }

   public ArrayList<Merchant> getMerchants(){
      return merchants;
   }

   public void addMerchants(Merchant merchant){
      merchants.add(merchant);
   }
   public boolean updateMerchants(int id,Merchant merchant){
      for(int i=0;i<merchants.size();i++){
         if(merchants.get(i).getId()==id){
            merchants.set(i,merchant);
            return true;
         }

      }
      return false;
   }
   public boolean deleteMerchants(int id){
      for(int i=0;i<merchants.size();i++){
         if(merchants.get(i).getId()==id){
            merchants.remove(i);
            return true;
         }
      }
      return false;
   }

}
