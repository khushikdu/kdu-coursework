package com.example.springjdbc.exceptions;

public class CustomException extends IndexOutOfBoundsException{
    public CustomException(String msg){
        super(msg);
    }
}
