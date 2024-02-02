package com.example.springbootapidemo.controller;

import com.example.springbootapidemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final Logger logging = LoggerFactory.getLogger(UserController.class);
    List<User> userList = new ArrayList<>();

    /**
     * to add user to the list
     * @param user : User object
     * @return : response entity
     */
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        logging.info("Adding new user to the list");
        userList.add(user);
        return ResponseEntity.ok("Added successfully");
    }

    /**
     * to get the list of all the users
     * @return : list of users
     */
    @GetMapping
    public List<User> getAllUsers(){
        logging.info("Returning the list of all the users");
        return userList;
    }

    /**
     * to get the user detail by name
     * @param name: name of the user
     * @return : user details
     */
    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        Optional<User> user=userList.stream()
                .filter(user1 -> user1.getName().equals(name))
                .findFirst();
        return user.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
