package com.example.capstone1.Servic;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ArrayList<Product> products=new ArrayList<>();
    public ArrayList<Product> getProduct(){
        return products ;
    }

    public void addProduct(Product product){
            products.add(product);
           product.setProductStatus("New");
    }
    public boolean updateProduct(int id,Product product){
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId()==id){
                products.set(i,product);
                return true;
            }

        }
        return false;
    }
    public boolean deleteProduct(int id){
            for(int i=0;i<products.size();i++){
            if(products.get(i).getId()==id){
                products.remove(i);
                return true;
            }
        }
        return false;
    }
//**************** range sales ***********************
    public ArrayList<Product> rangeSales(int main,int max){
        ArrayList<Product>products1=new ArrayList<>();
        for(int i=0;i<products.size();i++){
            if(products.get(i).getSales()>=main && products.get(i).getSales()<=max){
                products1.add(products.get(i));
            }
        }
        return products1;
    }


}
