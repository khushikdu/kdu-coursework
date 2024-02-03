package com.example.springjdbc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    public ResponseEntity<String> testConnection() {
        return new ResponseEntity<>("Connection established successfully!", HttpStatus.OK);
    }
}
