package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.Users;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private final List<Users> usersList;

    public UserDao() {
        this.usersList=new ArrayList<>();
    }
    public void addUser(Users users){
        usersList.add(users);
    }

    public List<Users> getAllUsers(){
        return usersList;
    }
}
