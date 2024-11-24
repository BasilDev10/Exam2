package com.example.exam2.Service;

import com.example.exam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers(){
        return users;
    }

    public User getByUserId(String id){

        for (User user : users){
            if(user.getId().equalsIgnoreCase(id)){
              return user;
            }
        }
        return null;
    }
    public ArrayList<User> getUserByBalance(int balance){
        ArrayList<User> usersFound = new ArrayList<>();

        for (User user : users){
            if(user.getBalance() >= balance){
                usersFound.add(user);
            }
        }

        return usersFound;
    }
    public ArrayList<User> getUserByAge(int age){
        ArrayList<User> usersFound = new ArrayList<>();

        for (User user : users){
            if(user.getAge() >= age){
                usersFound.add(user);
            }
        }

        return usersFound;
    }

    public void userAdd(User user){

        users.add(user);
    }

    public boolean userUpdate(String id, User user){

        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equalsIgnoreCase(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean userDelete(String id){

        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equalsIgnoreCase(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }
}
