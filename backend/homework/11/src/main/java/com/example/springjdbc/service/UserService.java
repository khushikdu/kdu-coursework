package com.example.springjdbc.service;

import com.example.springjdbc.dto.UserDTO;
import com.example.springjdbc.entities.Users;
import com.example.springjdbc.respository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;
    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Transactional
    public void addService(UserDTO userDTO){
        userDAO.add(userDTO);
    }
    public List<Users> getUsers(){
        return userDAO.getUsers();
    }
}
