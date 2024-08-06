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
    //********** Display Trending Products *********************
    public List<Product> getTrendingProducts(){
        return products.stream().sorted((p1, p2) -> {
            int salesComparison = p2.getSales().compareTo(p1.getSales());
            if (salesComparison != 0) {
                return salesComparison;
            }
            int ratingComparison = p2.getRating().compareTo(p1.getRating());
            if (ratingComparison != 0) {
                return ratingComparison;
            }
            return p2.getViews().compareTo(p1.getViews());
        })
        .limit(10)
            .collect(Collectors.toList());
    }

}
