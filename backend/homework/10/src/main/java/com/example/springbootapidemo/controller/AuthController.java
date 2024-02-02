package com.example.springbootapidemo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    /**
     * to test the login
     * @return : a response entity
     */
    @GetMapping ("/person/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("Test login Successful", HttpStatus.CREATED);
    }
}