package com.example.capstone1.Servic;

import com.example.capstone1.Model.OrderTracking;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
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


    //***************** register User To Prime ***************************************
    public boolean registerUserToPrime(int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId && users.get(i).getIsPrime().equals(false)){
                users.get(i).setIsPrime(true);
                return true;
            }
        }
        return false;
    }


}


