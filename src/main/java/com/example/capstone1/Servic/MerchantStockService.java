package com.example.capstone1.Servic;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@RequiredArgsConstructor
@Service
public class MerchantStockService {
    private final ProductService productService;
    private final UserService userService;
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantsStock(MerchantStock merchantStc) {
        merchantStocks.add(merchantStc);
    }

    public boolean updateMerchantsStock(int id, MerchantStock merchantStc) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i, merchantStc);
                return true;
            }

        }
        return false;
    }

    public boolean deleteMerchantsStock(int id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }


    //11- Create endpoint where merchant can add more stocks of product to a merchant Stock
    public boolean merchantAddStocks(int productId, int merchantId, int StockId, int additionalStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getProduct_id() == productId) {

                for (int b = 0; b < merchantStocks.size(); b++) {
                    if (merchantStocks.get(b).getMerchant_id() == merchantId) {
                        ;

                        for (int c = 0; c < merchantStocks.size(); c++)
                            if (merchantStocks.get(c).getId() == StockId && additionalStock > 0) {
                                merchantStocks.get(c).setStock(merchantStocks.get(c).getStock() + additionalStock);
                                return true;
                            }
                    }

                }
            }
        }
        return false;
    }

    //12- Create endpoint where user can buy a product directly
    public int buyProduct(int userid, int productId, int merchantId) {
        for (int i = 0; i < userService.users.size(); i++) {
            if (userService.users.get(i).getId() == userid && userService.users.get(i).getRole().equalsIgnoreCase("customer")) {

                for (int j = 0; j < productService.products.size(); j++) {
                    if (productService.products.get(j).getId() == productId) {


                        for (int h = 0; h < merchantStocks.size(); h++) {
                            if (merchantStocks.get(h).getMerchant_id() == merchantId && merchantStocks.get(h).getStock() != 0) {

                                if (userService.users.get(i).getBalance() >= productService.products.get(j).getPrice()) {
                                    userService.users.get(h).setBalance(userService.users.get(h).getBalance() - (productService.products.get(h).getPrice()));
                                    merchantStocks.get(h).setStock(merchantStocks.get(h).getStock() - 1);
                                    productService.products.get(j).setSales(productService.products.get(j).getSales()+1);
                                } else {
                                    return 1;
                                }

                            } else {
                                return 2;
                            }
                        }

                    } else {
                        return 3;
                    }
                }
            } else {
                return 4;
            }
        }
        return 5;
    }
//**************** Discount *************************************************
    public boolean discount(int productId, int merchantId, int userId) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getProduct_id() == productId && merchantStocks.get(i).getMerchant_id() == merchantId) {

                for (int j = 0; j < userService.users.size(); j++) {
                    if (userService.users.get(j).getId() == userId && userService.users.get(j).getRole().equalsIgnoreCase("Customer")) {

                        for (int p = 0; p < productService.products.size(); p++) {
                            if (productService.products.get(p).getId() == productId) {

                                userService.users.get(j).setBalance(userService.users.get(j).getBalance() - (productService.products.get(p).getPrice()*0.5));
                                return true;
                            }
                        }
                    }

                }


            }

        }  return false;
    }

     //*****************************product Status******************************8
  public boolean productStatus(int productId ,int merchantId){
      for (int i = 0; i < merchantStocks.size(); i++) {
          if(merchantStocks.get(i).getProduct_id()==productId&&merchantStocks.get(i).getMerchant_id()==merchantId){
              for(int j=0;j<productService.products.size();j++){

                  if (merchantStocks.get(i).getStock()<=0){
                      productService.products.get(j).setProductStatus("Unavailable");
                      return true;
                  }
                  if (merchantStocks.get(i).getStock()>0){
                  productService.products.get(j).setProductStatus("Available");
                  return true;
                 }
              }
          }
      }
      return false;
  }



}
