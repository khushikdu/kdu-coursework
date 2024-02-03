package com.example.springjdbc.controller;

import com.example.springjdbc.dto.UserDTO;
import com.example.springjdbc.entities.Users;
import com.example.springjdbc.exceptions.CustomException;
import com.example.springjdbc.logging.Logging;
import com.example.springjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    Logging.LoggerType loggerTypeError = Logging.LoggerType.ERROR;
    public final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * to call post mapping api for adding the user
     * @param userDTO: dto object fdr user
     * @return : response string
     */
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        userService.addService(userDTO);
        return new ResponseEntity<>("Added Succesfully", HttpStatus.OK);
    }

    /**
     * to call get mapping api for getting all the user
     * @return : list of users
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Users>> getAllUsers(){
        try {
            List<Users> usersList=userService.getUsers();
            if(usersList==null) {
                throw new CustomException("Record not found");
            }
            return new ResponseEntity<>(usersList,HttpStatus.OK);
        }catch (CustomException e){
            Logging.printLogger("Exception handled by custom handler :"+e.getMessage(),loggerTypeError);
        }
        return null;
    }
}
